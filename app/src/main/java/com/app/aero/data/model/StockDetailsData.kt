package com.app.aero.data.model

import com.app.aero.domain.model.DtoStats
import com.app.aero.domain.model.DtoStockDetails
import kotlinx.serialization.Serializable

@Serializable
data class StockDetailsData(
    val symbol: String,
    val exchange: String,
    val price: Double,
    val change: Double,
    val changePercent: Double,

    val companyName: String,
    val lastUpdated: String,
    val about: String,

    val ceo: String,
    val founded: Int,
    val headquarters: String,

    val stats: StatsData,
    val funds: Double
)

@Serializable
data class StatsData(
    val open: Double,
    val prevClose: Double,
    val high: Double,
    val low: Double,
    val volume: String,
    val avgPrice: Double
)

fun StockDetailsData.toDomain(): DtoStockDetails {
    return DtoStockDetails(
        symbol = symbol,
        exchange = exchange,
        price = "%.2f".format(price),
        change = "${if (change >= 0) "+" else ""}%.2f (%.2f%%)"
            .format(change, changePercent),
        isPositive = change >= 0,

        companyName = companyName,
        lastUpdated = lastUpdated,
        about = about,

        ceo = ceo,
        founded = founded.toString(),
        headquarters = headquarters,

        stats = DtoStats(
            open = "%.2f".format(stats.open),
            prevClose = "%.2f".format(stats.prevClose),
            high = "%.2f".format(stats.high),
            low = "%.2f".format(stats.low),
            volume = stats.volume,
            avgPrice = "%.2f".format(stats.avgPrice)
        ),

        funds = "₹%,.2f".format(funds)
    )
}