package com.kszalai.billtracker.ui.billdetails

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kszalai.billtracker.helpers.formatToCurrency
import com.kszalai.billtracker.models.*
import com.kszalai.billtracker.repo.BillRepo
import javax.inject.Inject

class BillDetailViewModel @Inject constructor(val billRepo: BillRepo) : ViewModel() {
    var selectedBill: BillObject? = null
    var billName: String? = null
    var amountDue: String = "$0.00"
    var dueDate: String? = null
    var lastPaidAmount: String = "$0.00"
    var lastPaidDate: String? = null

    private val _detailItems = MutableLiveData<ArrayList<Any>>()
    val detailItems: LiveData<ArrayList<Any>> = _detailItems

    private val _lastPaidVisibility = MutableLiveData<Int>(View.GONE)
    val lastPaidVisibility: LiveData<Int> = _lastPaidVisibility

    fun setBill(data: BillObject) {
        selectedBill = data
        billName = data.billName
        amountDue = data.nextPayment.amount.formatToCurrency()
        dueDate = data.nextPayment.paymentDate
        data.lastPayment?.let {
            lastPaidAmount = it.amount.formatToCurrency()
            lastPaidDate = it.paymentDate
            _lastPaidVisibility.postValue(View.VISIBLE)
        }

        val paymentHistoryItems = arrayListOf<Any>()

        selectedBill?.details?.let {
            when (it) {
                is CreditCardLimit -> {
                    paymentHistoryItems.add(it)
                }
                else -> {
                    throw Exception("Unknown bill details")
                }
            }
        }

        paymentHistoryItems.add(BillDetailRecyclerViewItems.PaymentHeader)

        paymentHistoryItems.add(BillPayment("07/02/20", 350.0))
        paymentHistoryItems.add(BillPayment("06/20/20", 350.0))

        _detailItems.postValue(paymentHistoryItems)
    }

}
