package com.alextos.plankingtimer.presentation.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val TimerBarColor = Color(0xFF4BE952)
val TimerBackgroundColor = Color(0xFF272A2C)
val AccentColor = Color(0xFF03A9F4)

val LightSurface1 = Color(0xFFE7E7E7)
val LightSurface2 = Color(0xFFFFFFFF)
val DarkSurface1 = Color(0xFF171717)
val DarkSurface2 = Color(0xFF272727)

val darkColorScheme = darkColorScheme(
    surface = DarkSurface1,
    onSurface = Color.White
)

val lightColorScheme = lightColorScheme(
    surface = LightSurface1,
    onSurface = Color.Black
)
