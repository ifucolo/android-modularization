package com.example.mymoduleexample.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val LightColorPalette = lightColors(
    primary = darkGreen,
    primaryVariant = green,
    background = slightGray,
    onSurface = black,
    surface = slightGray
)

private val DarkColorPalette = darkColors(
    primary = green,
    primaryVariant = darkGreen,
    background = slightBlack,
    onSurface = slightWithe,
    surface = slightGray
)

@Composable
fun AppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}