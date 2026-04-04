package com.app.aero.core.ui.theme


import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = AppDarkColors.Primary,
    onPrimary = AppDarkColors.onPrimary,
    onPrimaryContainer = AppDarkColors.onPrimary,

    secondary = AppDarkColors.Secondary,
    onSecondary = AppDarkColors.onSecondary,
    onSecondaryContainer = AppDarkColors.onSecondary,

    tertiary = AppDarkColors.Tertiary,
    onTertiary = AppDarkColors.onTertiary,

    background = AppDarkColors.Background,
    surface = AppDarkColors.Surface,
    onSurface = AppDarkColors.onSurface,

    surfaceTint = AppDarkColors.Primary
)

private val LightColorScheme = lightColorScheme(
    primary = AppColors.Primary,
    onPrimary = AppColors.onPrimary,
    onPrimaryContainer = AppColors.onPrimary,

    secondary = AppColors.Secondary,
    onSecondary = AppColors.onSecondary,
    onSecondaryContainer = AppColors.onSecondary,

    tertiary = AppColors.Tertiary,
    onTertiary = AppColors.onTertiary,

    background = AppColors.Background,
    surface = AppColors.Surface,
    onSurface = AppColors.onSurface,

    surfaceTint = AppDarkColors.Primary
)

@Composable
fun AeroTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    CompositionLocalProvider(
        LocalSpacing provides Spacing()
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = AppTypography,
            shapes = AppShapes,
            content = content
        )
    }
}