package com.app.aero.presentation.feature_feed

sealed class FeedIntent {
    object LoadData : FeedIntent()
    data class UpdateQuery(val query: String) : FeedIntent()
    data class NavigateToFeedDetails(val symbol: String) : FeedIntent()

    data object StartConnection: FeedIntent()
    data object StopConnection: FeedIntent()
}
