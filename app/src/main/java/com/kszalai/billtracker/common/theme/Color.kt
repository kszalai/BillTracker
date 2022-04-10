package com.kszalai.billtracker.common.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object BillTrackerColors {
    val Primary = Color(0xff008577)
    val PrimaryVariant: Color
        @Composable get() = if (isSystemInDarkTheme()) PrimaryVariantDark else PrimaryVariantLight
    val PrimaryVariantLight = Color(0xff4cb5a6)
    val PrimaryVariantDark = Color(0xff00574B)
    val Secondary = Color(0xffD81B60)
    val SecondaryVariant: Color
        @Composable get() = if (isSystemInDarkTheme()) SecondaryVariantDark else SecondaryVariantLight
    val SecondaryVariantLight = Color(0xffff5c8d)
    val SecondaryVariantDark = Color(0xffa00037)
    val Background: Color
        @Composable get() = if (isSystemInDarkTheme()) BackgroundDark else BackgroundLight
    val BackgroundLight = Color(0xFFFFFFFF)
    val BackgroundDark = Color(0xFF121212)
    val Surface: Color
        @Composable get() = if (isSystemInDarkTheme()) SurfaceDark else SurfaceLight
    val SurfaceLight = Color(0xFFFFFFFF)
    val SurfaceDark = Color(0xFF121212)
    val Error: Color
        @Composable get() = if (isSystemInDarkTheme()) ErrorDark else ErrorLight
    val ErrorLight = Color(0xFFB00020)
    val ErrorDark = Color(0xFFCF6679)
    val OnPrimary = Color(0xFFFFFFFF)
    val OnSecondary = Color(0xFFFFFFFF)
    val OnBackground: Color
        @Composable get() = if (isSystemInDarkTheme()) OnBackgroundDark else OnBackgroundLight
    val OnBackgroundLight = Color(0xFF1C1B1A)
    val OnBackgroundDark = Color(0xFFFFFFFF)
    val OnSurface: Color
        @Composable get() = if (isSystemInDarkTheme()) OnSurfaceDark else OnSurfaceLight
    val OnSurfaceLight = Color(0xFF1C1B1A)
    val OnSurfaceDark = Color(0xFFFFFFFF)
    val OnError: Color
        @Composable get() = if (isSystemInDarkTheme()) OnErrorDark else OnErrorLight
    val OnErrorLight = Color(0xFFFFFFFF)
    val OnErrorDark = Color(0xFF000000)

    val TextColor: Color
        @Composable get() = if (isSystemInDarkTheme()) textColorDark else textColorLight
    val textColorLight = Color(0xFF1C1B1A)
    val textColorDark = Color(0xFFFFFFFF)
    val FarOut: Color
        @Composable get() = if (isSystemInDarkTheme()) FarOutDark else FarOutLight
    private val FarOutLight = Color(0xff81c784)
    private val FarOutDark = Color(0xff003300)
    val DueThisWeek: Color
        @Composable get() = if (isSystemInDarkTheme()) DueThisWeekDark else DueThisWeekLight
    private val DueThisWeekLight = Color(0xfffff176)
    private val DueThisWeekDark = Color(0xffbc5100)
    val PastDue: Color
        @Composable get() = if (isSystemInDarkTheme()) PastDueDark else PastDueLight
    private val PastDueLight = Color(0xffe57373)
    private val PastDueDark = Color(0xff7f0000)


    @Composable
    fun billTrackerOutlinedTextFieldColors(
        textColor: Color = TextColor,
        disabledTextColor: Color = TextColor.copy(alpha = 0.4f),
        cursorColor: Color = Color.Cyan,
        errorCursorColor: Color = Color.Red,
        unfocusedBorderColor: Color = if (isSystemInDarkTheme()) Color.White else Color.Black
    ) = TextFieldDefaults.outlinedTextFieldColors(
        textColor = textColor,
        disabledBorderColor = disabledTextColor,
        cursorColor = cursorColor,
        errorCursorColor = errorCursorColor,
        unfocusedBorderColor = unfocusedBorderColor
    )
}