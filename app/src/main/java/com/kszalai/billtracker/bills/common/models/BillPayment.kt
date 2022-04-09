package com.kszalai.billtracker.bills.common.models

import com.kszalai.billtracker.common.extensions.formatToCurrency

data class BillPayment(
    val paymentDate: String,
    val _amount: Double
) {
    val amount = _amount.formatToCurrency()
}