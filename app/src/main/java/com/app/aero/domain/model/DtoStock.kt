package com.app.aero.domain.model

data class DtoStock(
    val symbol: String,
    val exchange: String,
    val price: String,
    val change: String,
    val isPositive: Boolean
)