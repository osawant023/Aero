package com.app.aero.data.network

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object WebSocketController {
    private val _socketConnection : MutableStateFlow<Boolean> = MutableStateFlow(false)
    val socketConnection : StateFlow<Boolean> = _socketConnection

    fun updateSocketConnectin(boolean: Boolean){
        _socketConnection.value = boolean
    }
}
