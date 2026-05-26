package com.student.form.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// ── Custom Palette ──────────────────────────────────────────────────────────
val DeepIndigo     = Color(0xFF1A1A4E)   // header / primary dark
val ElectricViolet = Color(0xFF6C3FE8)   // accent / buttons
val SoftLavender   = Color(0xFFEDE8FF)   // surface / card background
val CrimsonRed     = Color(0xFFE84040)   // error / required indicator
val MintGreen      = Color(0xFF2DCC8F)   // success / switch active track
val LightGray      = Color(0xFFF4F4F8)   // page background
val TextPrimary    = Color(0xFF1A1A2E)
val TextSecondary  = Color(0xFF6B6B8A)
val White          = Color(0xFFFFFFFF)

private val AppColorScheme = lightColorScheme(
    primary          = ElectricViolet,
    onPrimary        = White,
    primaryContainer = SoftLavender,
    onPrimaryContainer = DeepIndigo,
    secondary        = MintGreen,
    onSecondary      = White,
    background       = LightGray,
    onBackground     = TextPrimary,
    surface          = White,
    onSurface        = TextPrimary,
    error            = CrimsonRed,
    onError          = White,
)

@Composable
fun StudentFormTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = AppColorScheme,
        content     = content
    )
}
