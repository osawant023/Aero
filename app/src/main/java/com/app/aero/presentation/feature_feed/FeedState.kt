package com.app.aero.presentation.feature_feed

import com.app.aero.domain.model.DtoStockDetails
import com.app.aero.presentation.feature_feed.component.SortChipData
import com.app.aero.presentation.feature_feed.component.arrSortCategories

data class FeedState(
    val isLoading: Boolean = false,
    val stocks: List<DtoStockDetails> = emptyList(),
    val query: String = "",
    val selectedSortData : SortChipData ?= null,
    val arrSortCategory : List<SortChipData> = arrSortCategories
)