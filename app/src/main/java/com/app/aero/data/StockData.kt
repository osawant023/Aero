package com.app.aero.data

import com.app.aero.domain.model.DtoStock
import kotlinx.serialization.Serializable

@Serializable
data class StockData(
    val symbol: String,
    val exchange: String,
    val price: Double,
    val change: Double,
    val changePercent: Double
)

fun StockData.toDomain(): DtoStock {
    return DtoStock(
        symbol = symbol,
        exchange = exchange,
        price = "%.2f".format(price),
        change = "${if (change >= 0) "+" else ""}%.2f (%.2f%%)"
            .format(change, changePercent),
        isPositive = change >= 0
    )
}