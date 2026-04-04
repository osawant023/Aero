package com.app.aero.presentation.feature_feed_details

import com.app.aero.domain.model.DtoStockDetails

data class FeedDetailState(
    val isLoading: Boolean = false,
    val data: DtoStockDetails? = null
)