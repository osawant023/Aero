package com.app.aero.domain.repo

import com.app.aero.domain.model.DtoStockDetails
import kotlinx.coroutines.flow.Flow

interface FeedRepository {

    fun observeStocks(): Flow<List<DtoStockDetails>>

    fun observeStock(symbol: String): Flow<DtoStockDetails>

    fun start()

    fun stop()

}
