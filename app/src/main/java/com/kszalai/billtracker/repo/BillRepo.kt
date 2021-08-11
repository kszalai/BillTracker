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

    init {
        _bills.postValue(billService.getBills())
    }

    fun setBills(bills: ArrayList<Any>) {
        _bills.postValue(bills)
    }

}