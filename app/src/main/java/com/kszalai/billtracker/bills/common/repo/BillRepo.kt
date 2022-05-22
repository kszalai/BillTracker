package com.kszalai.billtracker.bills.common.repo

import com.kszalai.billtracker.bills.common.dataSource.BillDataSource
import com.kszalai.billtracker.bills.common.models.BillObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Singleton

@Singleton
class BillRepo(private val billDataSource: BillDataSource) {

    val bills: Flow<List<BillObject>> = flow {
        emit(billDataSource.getBills())
    }

    suspend fun addBill(billObject: BillObject) {
        billDataSource.addBill(billObject)
    }
}