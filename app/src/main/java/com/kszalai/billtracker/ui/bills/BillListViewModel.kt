package com.kszalai.billtracker.ui.bills

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kszalai.billtracker.models.BillObject
import com.kszalai.billtracker.models.BillType
import javax.inject.Inject
import kotlin.collections.ArrayList

class BillListViewModel @Inject constructor() : ViewModel() {
    private val _bills = MutableLiveData<ArrayList<Any>>()
    val bills: LiveData<ArrayList<Any>> = _bills

    init {
        val test = ArrayList<Any>()
        test.add(BillObject(
            billName = "Discover",
            amount = 129.70,
            dueDate = "06/06/20",
            pastDue = 0.0,
            amountPaid = 0.0,
            datePaid = null,
            balance = null,
            comments = "",
            fees = emptyArray(),
            billType = BillType.CreditCard
        ))
        test.add(BillObject(
            billName = "Rocket Mortgage",
            amount = 600.00,
            dueDate = "06/20/20",
            pastDue = 0.0,
            amountPaid = 0.0,
            datePaid = null,
            balance = 100000.0,
            comments = "",
            fees = emptyArray(),
            billType = BillType.Mortgage
        ))
        test.add(BillObject(
            billName = "Ford EcoSport",
            amount = 350.00,
            dueDate = "06/28/20",
            pastDue = 0.0,
            amountPaid = 0.0,
            datePaid = null,
            balance = 25000.0,
            comments = "",
            fees = emptyArray(),
            billType = BillType.Auto
        ))
        _bills.value = test
    }


}
