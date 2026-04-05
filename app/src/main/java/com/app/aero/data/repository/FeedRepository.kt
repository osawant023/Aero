package com.app.aero.data.repository

import com.app.aero.data.model.StockDetailsData
import com.app.aero.data.model.toDomain
import com.app.aero.data.network.WebSocketClient
import com.app.aero.domain.model.DtoStockDetails
import com.app.aero.domain.repo.FeedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map

class FeedRepositoryImpl(private val socketManager: WebSocketClient) : FeedRepository {
    val stocks: StateFlow<List<StockDetailsData>> = socketManager.prices

    override fun observeStocks(): Flow<List<DtoStockDetails>> {
        return stocks.map { list ->
            list.map { it.toDomain() }
        }
    }

    override fun observeStock(symbol: String): Flow<DtoStockDetails> {
        return stocks
            .map { list ->
                list.find { it.symbol == symbol }
            }.map { it?.toDomain() as DtoStockDetails }
    }

    override fun start() {
        socketManager.connect()
    }

    override fun stop() {
        socketManager.disconnect()
    }


}