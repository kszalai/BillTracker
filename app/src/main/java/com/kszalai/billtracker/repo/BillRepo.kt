package com.kszalai.billtracker.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kszalai.billtracker.models.BillObject
import com.kszalai.billtracker.services.BillService
import javax.inject.Singleton

@Singleton
class BillRepo(private val billService: BillService) {

    private val _bills = MutableLiveData<ArrayList<Any>>()
    val bills: LiveData<ArrayList<Any>> = _bills

    private val _selectedBill = MutableLiveData<BillObject>()
    val selectedBill: LiveData<BillObject> = _selectedBill

    init {
        _bills.postValue(billService.getBills())
    }

    fun setBills(bills: ArrayList<Any>) {
        _bills.postValue(bills)
    }

    fun setSelectedBill(bill: BillObject) {
        _selectedBill.postValue(bill)
    }

}