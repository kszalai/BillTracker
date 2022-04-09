package com.kszalai.billtracker.bills.add

data class AddBillUIState(
    var billName: String = "",
    var apr: String = "",
    var billTypeExpanded: Boolean = false,
    var selectedBillType: String = "",
    var creditLimit: String = ""
)
