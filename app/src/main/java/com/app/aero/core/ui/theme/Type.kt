package com.app.aero.core.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val AppTypography = Typography(

    displayLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 48.sp,
        letterSpacing = (-0.02).sp
    ),

    headlineMedium = TextStyle(
        fontSize = 24.sp
    ),

    bodyMedium = TextStyle(
        fontSize = 14.sp
    ),

    labelSmall = TextStyle(
        fontSize = 11.sp
    )
)

val typography = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp
)