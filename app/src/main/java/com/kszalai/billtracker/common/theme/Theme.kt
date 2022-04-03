package com.kszalai.billtracker.common.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val lightColorPalette = lightColors(
    primary = BillTrackerColors.Primary,
    primaryVariant = BillTrackerColors.PrimaryDark,
    secondary = BillTrackerColors.Accent,
    secondaryVariant = BillTrackerColors.AccentDark,
    surface = Color.LightGray,
    onPrimary = BillTrackerColors.textColorLight,
    onSurface = BillTrackerColors.textColorLight,
    onBackground = BillTrackerColors.textColorLight
)

private val darkColorPalette = darkColors(
    primary = BillTrackerColors.Primary,
    primaryVariant = BillTrackerColors.PrimaryLight,
    secondary = BillTrackerColors.Accent,
    secondaryVariant = BillTrackerColors.AccentLight,
    surface = Color.DarkGray,
    onPrimary = BillTrackerColors.textColorDark,
    onSurface = BillTrackerColors.textColorDark,
    onBackground = BillTrackerColors.textColorDark
)

@Composable
fun BillTrackerTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (isSystemInDarkTheme()) lightColorPalette else darkColorPalette,
        typography = MaterialTheme.typography,
        shapes = MaterialTheme.shapes,
        content = content
    )
}