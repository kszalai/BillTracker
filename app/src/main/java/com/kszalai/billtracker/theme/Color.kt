package com.kszalai.billtracker.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val colorPrimary = Color(0xff008577)
val colorPrimaryDark = Color(0xff00574B)
val colorPrimaryLight = Color(0xff4cb5a6)
val colorAccent = Color(0xffD81B60)
val colorAccentDark = Color(0xffa00037)
val colorAccentLight = Color(0xffff5c8d)
val textColorLight = Color(0xff333333)
val textColorDark = Color(0xffffffff)
val farOutLight = Color(0xff81c784)
val dueThisWeekLight = Color(0xfffff176)
val pastDueLight = Color(0xffe57373)
val farOutDark = Color(0xff003300)
val dueThisWeekDark = Color(0xffbc5100)
val pastDueDark = Color(0xff7f0000)

val Colors.billTextColor : Color
    @Composable get() = if (isLight) textColorDark else textColorLight

val Colors.farOut : Color
    @Composable get() = if (isLight) farOutDark else farOutLight

val Colors.dueThisWeek : Color
    @Composable get() = if (isLight) dueThisWeekDark else dueThisWeekLight

val Colors.pastDue : Color
    @Composable get() = if (isLight) pastDueDark else pastDueLight