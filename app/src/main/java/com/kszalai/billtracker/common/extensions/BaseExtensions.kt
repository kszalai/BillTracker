package com.kszalai.billtracker.common.extensions

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.kszalai.billtracker.R
import com.kszalai.billtracker.common.theme.BillTrackerColors
import com.kszalai.billtracker.bills.common.models.BillType
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

fun Double?.formatToCurrency(): String {
    if (this == null) {
        return "$0.00"
    }
    val formatter = NumberFormat.getCurrencyInstance()
    return "${formatter.format(this)}"
}

fun Double?.formatToPercentage(): String {
    return this?.let {
        "${"%.2f".format(this)}%"
    } ?: run {
        "0.0%"
    }
}

fun String.toast(context: Context, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, this, duration).show()
}

fun String.determineColorFromDate(): Int {
    val sdf = SimpleDateFormat("MM/dd/yyyy")
    val theDate = sdf.parse(this)
    val now = Date()
    val diff = TimeUnit.DAYS.convert(theDate.time - now.time, TimeUnit.MILLISECONDS)

    return when {
        (diff < 0) -> R.color.backgroundPastDue
        (diff <= 7) -> R.color.backgroundDueThisWeek
        else -> R.color.backgroundFarOut
    }
}

@Composable
fun String.determineComposableColorFromDate(): Color {
    val sdf = SimpleDateFormat("MM/dd/yyyy")
    val theDate = sdf.parse(this)
    val now = Date()
    val diff = TimeUnit.DAYS.convert(theDate.time - now.time, TimeUnit.MILLISECONDS)

    return when {
        (diff < 0) -> BillTrackerColors.PastDue
        (diff <= 7) -> BillTrackerColors.DueThisWeek
        else -> BillTrackerColors.FarOut
    }
}

fun BillType.getIcon(): Int {
    return when (this) {
        BillType.CreditCard -> R.drawable.credit_card_icon
        BillType.Mortgage -> R.drawable.mortgage_icon
        BillType.Subscription -> R.drawable.subscription_icon
        BillType.Utility -> R.drawable.bill_icon
        BillType.Auto -> R.drawable.auto_icon
        BillType.Unspecified -> R.drawable.bill_icon
    }
}