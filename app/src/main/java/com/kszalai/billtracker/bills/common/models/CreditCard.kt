package com.kszalai.billtracker.bills.common.models

import java.io.Serializable

data class CreditCard(
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
    override val billType: BillType = BillType.CreditCard,
    override val pinned: Boolean = false,
    override val link: String = "",
    override val paymentHistory: List<BillPayment> = emptyList(),
    val details: CreditCardLimit = CreditCardLimit(
        limit = 0.0,
        apr = 0.0
    )
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

data class CreditCardLimit(
    val limit: Double,
    val apr: Double
)