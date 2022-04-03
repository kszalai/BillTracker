package com.kszalai.billtracker.bills.common.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kszalai.billtracker.models.BillObject
import com.kszalai.billtracker.bills.common.services.BillService
import javax.inject.Singleton

@Singleton
class BillRepo(private val billService: BillService) {

    private val _bills = MutableLiveData<List<BillObject>>()
    val bills: LiveData<List<BillObject>> = _bills

    init {
        _bills.postValue(billService.getBills())
    }

    fun setBills(bills: List<BillObject>) {
        _bills.postValue(bills)
    }

}