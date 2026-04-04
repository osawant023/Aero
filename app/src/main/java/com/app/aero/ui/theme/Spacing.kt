package com.app.aero.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

data class Spacing(
    val xs: Int = 4,
    val sm: Int = 8,
    val md: Int = 12,
    val lg: Int = 16,
    val xl: Int = 20,
    val xxl: Int = 24,
    val xxxl: Int = 32
)

val LocalSpacing = staticCompositionLocalOf { Spacing() }