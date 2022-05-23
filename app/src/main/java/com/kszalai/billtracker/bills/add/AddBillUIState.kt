package com.kszalai.billtracker.bills.add

data class AddBillUIState(
    val billName: String = "",
    val apr: String = "",
    val billTypeExpanded: Boolean = false,
    val selectedBillType: String = "",
    val creditLimit: String = "",
    val pastDue: String = "",
    val comment: String = "",
    val link: String = "",
    val autoPay: Boolean = false,
    val autoPayDiscount: String = "",
    val initialLoanAmount: String = "",
    val currentLoanAmount: String = "",
    val subscriptionFrequencyExpanded: Boolean = false,
    val subscriptionFrequency: String = "",
    val subscriptionFrequencyNotes: String = "",
    val subscriptionAmount: String = ""
)
