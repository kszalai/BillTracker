package com.kszalai.billtracker.bills.common.models

import java.io.Serializable

data class AutoLoan(
    override val id: Int = -1,
    override val billName: String = "",
    override var nextPayment: BillPayment = BillPayment(
        paymentDate = "",
        _amount = 0.0
    ),
    override var pastDue: Double = 0.0,
    override var lastPayment: BillPayment? = null,
    override var balance: Double? = null,
    override var comments: String = "",
    override var fees: List<BillFee> = emptyList(),
    override val billType: BillType = BillType.Auto,
    override val pinned: Boolean = false,
    override val link: String = "",
    override val paymentHistory: List<BillPayment> = emptyList()
) : BillObject(
    id,
    billName,
    nextPayment,
    pastDue,
    lastPayment,
    balance,
    comments,
    fees,
    billType,
    pinned,
    link,
    paymentHistory
), Serializable
