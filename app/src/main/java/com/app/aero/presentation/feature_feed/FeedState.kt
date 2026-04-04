package com.app.aero.presentation.feature_feed

import com.app.aero.domain.model.DtoStock

data class FeedState(
    val isLoading: Boolean = false,
    val stocks: List<DtoStock> = emptyList(),
    val query: String = ""
)