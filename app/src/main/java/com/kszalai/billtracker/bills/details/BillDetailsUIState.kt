package com.kszalai.billtracker.bills.details

import com.kszalai.billtracker.bills.common.models.BillObject

data class BillDetailsUIState(
    val loading: Boolean = false,
    val selectedBill: BillObject = BillObject.Generic()
)
