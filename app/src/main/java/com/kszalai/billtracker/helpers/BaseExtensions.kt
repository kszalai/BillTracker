package com.kszalai.billtracker.helpers

import android.content.Context
import android.widget.Toast
import java.text.NumberFormat

fun Double?.formatToCurrency(): String {
    if (this == null) {
        return "$0.00"
    }
    val formatter = NumberFormat.getCurrencyInstance()
    return "${formatter.format(this)}"
}

fun String.toast(context: Context, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, this, duration).show()
}