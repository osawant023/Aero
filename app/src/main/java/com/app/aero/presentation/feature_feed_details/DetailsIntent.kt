package com.app.aero.presentation.feature_feed_details

sealed class FeedDetailIntent {
    data class Load(val symbol: String) : FeedDetailIntent()
    data object NavigateUp : FeedDetailIntent()

    data object StartConnection: FeedDetailIntent()
    data object StopConnection: FeedDetailIntent()
}
