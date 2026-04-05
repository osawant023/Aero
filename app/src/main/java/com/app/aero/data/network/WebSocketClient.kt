package com.app.aero.data.network

import com.app.aero.data.model.StockDetailsData
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.DefaultClientWebSocketSession
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.websocket.Frame
import io.ktor.websocket.close
import io.ktor.websocket.readText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlin.random.Random


class WebSocketClient(private val client: HttpClient) {

    val initialList = Json.decodeFromString<List<StockDetailsData>>(mockJson)
    private val _prices = MutableStateFlow(initialList)
    val prices: StateFlow<List<StockDetailsData>> = _prices

    private var session: DefaultClientWebSocketSession? = null

    fun connect() {
        CoroutineScope(Dispatchers.IO).launch {

            try {
                client.webSocket(
                    urlString = "wss://ws.postman-echo.com/raw"
                ) {
                    WebSocketController.updateSocketConnectin(true)
                    session = this
                    val simulateJob = launch { simulateMarket() }
                    try {
                        for (frame in incoming) {
                            if (frame is Frame.Text) {
                                val json = frame.readText()
                                val data = Json.decodeFromString<List<StockDetailsData>>(json)
                                _prices.value = data
                            }
                        }
                    }finally {
                        simulateJob.cancel()
                        session = null
                        WebSocketController.updateSocketConnectin(false)
                    }
                }

            } catch (e: Exception) {
                WebSocketController.updateSocketConnectin(false)
                reconnect()
            }
        }

    }

    private fun reconnect() {
        CoroutineScope(Dispatchers.IO).launch {
            delay(3000)
            connect()
        }
    }

    suspend fun send(message: String) {
        session?.send(Frame.Text(message))
    }

    fun disconnect() {
        CoroutineScope(Dispatchers.IO).launch {
            WebSocketController.updateSocketConnectin(false)
            session?.close()
        }
    }

    private suspend fun simulateMarket() {
        while (true) {
            delay(2000)
            val current = _prices.value
            val updatedList = current.map { stock ->
                val delta = Random.nextDouble(-2.0, 2.0)

                val newPrice = (stock.price + delta).coerceAtLeast(1.0)
                val change = newPrice - stock.stats.prevClose
                val percent = (change / stock.stats.prevClose) * 100

                stock.copy(
                    price = newPrice,
                    change = change,
                    changePercent = percent
                )
            }
            val message = Json.encodeToString(updatedList)
            if (session?.isActive == true){
                send(message)
            }
        }
    }
}