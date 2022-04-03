package com.kszalai.billtracker.common.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object BillTrackerColors {
    val Primary = Color(0xff008577)
    val PrimaryDark = Color(0xff00574B)
    val PrimaryLight = Color(0xff4cb5a6)
    val Accent = Color(0xffD81B60)
    val AccentDark = Color(0xffa00037)
    val AccentLight = Color(0xffff5c8d)
    val TextColor: Color
        @Composable get() = if (isSystemInDarkTheme()) textColorDark else textColorLight
    val textColorLight = Color(0xff333333)
    val textColorDark = Color(0xffffffff)
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
        textColor: Color = BillTrackerColors.TextColor,
        disabledTextColor: Color = BillTrackerColors.TextColor.copy(alpha = 0.4f),
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