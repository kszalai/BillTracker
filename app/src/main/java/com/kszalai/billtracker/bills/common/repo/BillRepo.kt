package com.kszalai.billtracker.bills.common.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kszalai.billtracker.bills.common.dataSource.BillDataSource
import com.kszalai.billtracker.bills.common.models.BillObject
import javax.inject.Singleton

@Singleton
class BillRepo(private val billDataSource: BillDataSource) {

    private val _bills = MutableLiveData<List<BillObject>>()
    val bills: LiveData<List<BillObject>> = _bills

    init {
        _bills.postValue(billDataSource.getBills())
    }

    fun setBills(bills: List<BillObject>) {
        _bills.postValue(bills)
    }

}