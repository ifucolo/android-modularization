package com.example.mymoduleexample.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.mymoduleexample.R

val ProximaNova = FontFamily(
    Font(R.font.proxima_nova_reg, FontWeight.Normal),
    Font(R.font.proxima_nova_bold, FontWeight.Bold)
)

val Typography = Typography(
    h1  = TextStyle(
        fontFamily = ProximaNova,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        lineHeight = 20.sp,
        color = Color.Black
    ),
    h3 = TextStyle(
        fontFamily = ProximaNova,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 20.sp,
        color = Color.Black
    ),
    subtitle1 = TextStyle(
        fontFamily = ProximaNova,
        fontWeight = FontWeight.Light,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        color = Color.Black
    ),
    body1 = TextStyle(
        fontFamily = ProximaNova,
        fontWeight = FontWeight.Light,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        color = Color.Black
    ),
    button = TextStyle(
        fontFamily = ProximaNova,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp,
        color = Color.Black
    )
)