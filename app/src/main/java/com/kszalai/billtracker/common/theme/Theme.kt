package com.kszalai.billtracker.common.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val lightColorPalette = lightColors(
    primary = BillTrackerColors.Primary,
    primaryVariant = BillTrackerColors.PrimaryVariantLight,
    secondary = BillTrackerColors.Secondary,
    secondaryVariant = BillTrackerColors.SecondaryVariantLight,
    background = BillTrackerColors.BackgroundLight,
    error = BillTrackerColors.ErrorLight,
    surface = BillTrackerColors.SurfaceLight,
    onPrimary = BillTrackerColors.OnPrimary,
    onSurface = BillTrackerColors.OnSurfaceLight,
    onBackground = BillTrackerColors.OnBackgroundLight,
    onError = BillTrackerColors.OnErrorLight
)

private val darkColorPalette = darkColors(
    primary = BillTrackerColors.Primary,
    primaryVariant = BillTrackerColors.PrimaryVariantDark,
    secondary = BillTrackerColors.Secondary,
    secondaryVariant = BillTrackerColors.SecondaryVariantDark,
    background = BillTrackerColors.BackgroundDark,
    error = BillTrackerColors.ErrorDark,
    surface = BillTrackerColors.SurfaceDark,
    onPrimary = BillTrackerColors.OnPrimary,
    onSurface = BillTrackerColors.OnSurfaceDark,
    onBackground = BillTrackerColors.OnBackgroundDark,
    onError = BillTrackerColors.OnErrorDark
)

@Composable
fun BillTrackerTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (isSystemInDarkTheme()) darkColorPalette else lightColorPalette,
        typography = MaterialTheme.typography,
        shapes = MaterialTheme.shapes,
        content = content
    )
}