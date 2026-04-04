package com.app.aero.core.navigation
import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object SplashScreen : Route
    @Serializable
    data object FeedList : Route

    @Serializable
    data object ComingSoon : Route

    @Serializable
    data class FeedDetails(val feedId:String) : Route

}