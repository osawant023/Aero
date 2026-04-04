package com.app.aero.core.navigation


data class TopLevelRoute<T : Any>(val name: String, val route: T, val icon: Int)
