package com.app.aero.presentation.feature_feed

import com.app.aero.presentation.feature_feed.component.SortChipData

sealed class FeedIntent {
    object LoadData : FeedIntent()
    data class UpdateQuery(val query: String) : FeedIntent()
    data class NavigateToFeedDetails(val symbol: String) : FeedIntent()

    data object StartConnection: FeedIntent()
    data object StopConnection: FeedIntent()
    data object ClearSort: FeedIntent()

    data class UpdateSort(val sortData : SortChipData): FeedIntent()
}
