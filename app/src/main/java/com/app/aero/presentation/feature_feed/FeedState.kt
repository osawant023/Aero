package com.app.aero.presentation.feature_feed

import com.app.aero.domain.model.DtoStockDetails

data class FeedState(
    val isLoading: Boolean = false,
    val stocks: List<DtoStockDetails> = emptyList(),
    val query: String = ""
)