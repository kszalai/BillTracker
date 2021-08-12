package com.kszalai.billtracker.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val lightColorPalette = lightColors(
    primary = colorPrimary,
    primaryVariant = colorPrimaryDark,
    secondary = colorAccent,
    secondaryVariant = colorAccentDark,
    surface = Color.LightGray,
    onPrimary = textColorLight,
    onSurface = textColorLight,
    onBackground = textColorLight
)

private val darkColorPalette = darkColors(
    primary = colorPrimary,
    primaryVariant = colorPrimaryLight,
    secondary = colorAccent,
    secondaryVariant = colorAccentLight,
    surface = Color.DarkGray,
    onPrimary = textColorDark,
    onSurface = textColorDark,
    onBackground = textColorDark
)

@Composable
fun BillTrackerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (darkTheme) lightColorPalette else darkColorPalette,
        typography = MaterialTheme.typography,
        shapes = MaterialTheme.shapes,
        content = content
    )
}