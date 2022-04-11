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
    override var comments: String = "",
    override var fees: List<BillFee> = emptyList(),
    override val pinned: Boolean = false,
    override val link: String = "",
    override val paymentHistory: List<BillPayment> = emptyList(),
    val details: BillBalance = BillBalance()
) : BillObject(
    id,
    billName,
    nextPayment,
    pastDue,
    comments,
    fees,
    pinned,
    link,
    paymentHistory
), Serializable
