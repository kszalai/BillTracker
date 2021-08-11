package com.kszalai.billtracker.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val farOutLight = Color(0xff81c784)
val dueThisWeekLight = Color(0xfffff176)
val pastDueLight = Color(0xffe57373)
val farOutDark = Color(0xff003300)
val dueThisWeekDark = Color(0xffbc5100)
val pastDueDark = Color(0xff7f0000)

val Colors.farOut : Color
    @Composable get() = if (isLight) farOutLight else farOutDark

val Colors.dueThisWeek : Color
    @Composable get() = if (isLight) dueThisWeekLight else dueThisWeekDark

val Colors.pastDue : Color
    @Composable get() = if (isLight) pastDueLight else pastDueDark

data class BillTrackerColors(
    val farOut: Color,
    val dueThisWeek: Color,
    val pastDue: Color
)