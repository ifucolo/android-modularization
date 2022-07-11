package com.example.mymoduleexample.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val green = Color(0xFF008577)
val darkGreen = Color(0xFF00574B)
val pink = Color(0xFFD81B60)

val slightWithe = Color(0xFFF1F3F6)
val slightGray = Color(0x9CF1F3F6)
val slightBlack = Color(0xFF1D1E20)

val cyan = Color(0xFFD1FFFD)
val black = Color(0xFF000000)

@Composable
fun tintColor(): Color = if (isSystemInDarkTheme())  slightWithe else black

@Composable
fun textColor(): Color = if (isSystemInDarkTheme())  black else slightWithe

@Composable
fun titleTextColor(): Color = if (isSystemInDarkTheme())  slightWithe else black