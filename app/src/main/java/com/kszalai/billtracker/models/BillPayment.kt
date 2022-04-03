package com.kszalai.billtracker.models

import com.kszalai.billtracker.common.extensions.formatToCurrency

data class BillPayment(
    val paymentDate: String,
    val _amount: Double
) {
    val amount = _amount.formatToCurrency()
}