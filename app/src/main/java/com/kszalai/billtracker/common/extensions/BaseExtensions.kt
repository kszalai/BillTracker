package com.kszalai.billtracker.common.extensions

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.withStyle
import com.kszalai.billtracker.R
import com.kszalai.billtracker.bills.common.models.*
import com.kszalai.billtracker.common.theme.BillTrackerColors
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

fun BillObject.getIcon(): Int {
    return when (this) {
        is CreditCard -> R.drawable.credit_card_icon
        is Mortgage -> R.drawable.mortgage_icon
        is Subscription -> R.drawable.subscription_icon
        is Utility -> R.drawable.bill_icon
        is AutoLoan -> R.drawable.auto_icon
        else -> R.drawable.bill_icon
    }
}

@Composable
fun BillObject.buildPaymentString(): AnnotatedString {
    return buildAnnotatedString {
        withStyle(style = SpanStyle(fontStyle = FontStyle.Italic)) {
            append(this@buildPaymentString.nextPayment.amount)
        }
        append(" - Due On ${this@buildPaymentString.nextPayment.paymentDate}")
    }
}