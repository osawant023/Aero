package com.app.aero.domain.model

data class DtoStockDetails(
    val symbol: String,
    val exchange: String,
    val price: String,
    val change: String,
    val isPositive: Boolean,

    val companyName: String,
    val lastUpdated: String,
    val about: String,

    val ceo: String,
    val founded: String,
    val headquarters: String,

    val stats: DtoStats,
    val funds: String
)

data class DtoStats(
    val open: String,
    val prevClose: String,
    val high: String,
    val low: String,
    val volume: String,
    val avgPrice: String
)