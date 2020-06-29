package com.kszalai.billtracker.ui.billdetails

import androidx.lifecycle.ViewModel
import com.kszalai.billtracker.helpers.formatToCurrency
import com.kszalai.billtracker.models.BillObject
import com.kszalai.billtracker.repo.BillRepo
import javax.inject.Inject

class BillDetailViewModel @Inject constructor(val billRepo: BillRepo) : ViewModel() {
    var selectedBill: BillObject? = null
    var billName: String? = null
    var amountDue: String = "$0.00"
    var dueDate: String? = null
    var lastPaidAmount: String = "$0.00"
    var lastPaidDate: String? = null

    fun setBill(data: BillObject) {
        selectedBill = data
        billName = data.billName
        amountDue = data.amount.formatToCurrency()
        dueDate = data.dueDate
        lastPaidAmount = "Testing"
        lastPaidDate = data.datePaid
    }

}
