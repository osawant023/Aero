package com.app.aero.domain.repo

import com.app.aero.domain.model.DtoStock
import com.app.aero.domain.model.DtoStockDetails

interface FeedRepository {
    fun getStocks(): List<DtoStock>
    fun getStocksDetails(symbol: String): DtoStockDetails
}
