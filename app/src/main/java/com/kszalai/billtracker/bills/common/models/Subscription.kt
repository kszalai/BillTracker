package com.kszalai.billtracker.bills.common.models

import java.io.Serializable

data class Subscription(
    override val id: Int = -1,
    override val billName: String = "",
    override var nextPayment: BillPayment = BillPayment(
        paymentDate = "",
        _amount = 0.0
    ),
    override var pastDue: Double = 0.0,
    override var lastPayment: BillPayment? = null,
    override val initialBalance: Double? = null,
    override var balance: Double? = null,
    override var comments: String = "",
    override var fees: List<BillFee> = emptyList(),
    override val billType: BillType = BillType.Subscription,
    override val pinned: Boolean = false,
    override val link: String = "",
    override val paymentHistory: List<BillPayment> = emptyList()
) : BillObject(
    id,
    billName,
    nextPayment,
    pastDue,
    lastPayment,
    initialBalance,
    balance,
    comments,
    fees,
    billType,
    pinned,
    link,
    paymentHistory
), Serializable
