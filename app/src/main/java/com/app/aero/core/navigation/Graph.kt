package com.app.aero.core.navigation

import kotlinx.serialization.Serializable

sealed interface Graph {

    @Serializable
    data object Aero : Graph
    @Serializable
    data object Market : Graph
    @Serializable
    data object Portfolio : Graph
    @Serializable
    data object Orders : Graph
    @Serializable
    data object User : Graph
}
