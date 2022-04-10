package com.kszalai.billtracker.bills.common.models

import com.kszalai.billtracker.common.extensions.formatToCurrency
import java.io.Serializable

open class BillObject(
    open val id: Int = -1,
    open val billName: String = "",
    open var nextPayment: BillPayment = BillPayment(
        paymentDate = "",
        _amount = 0.0
    ),
    open var pastDue: Double = 0.0,
    open var lastPayment: BillPayment? = null,
    open var balance: Double? = null,
    open var comments: String = "",
    open var fees: List<BillFee> = emptyList(),
    open val billType: BillType = BillType.Unspecified,
    open val pinned: Boolean = false,
    open val link: String = "",
    open val paymentHistory: List<BillPayment> = emptyList()
) : Serializable { }

data class BillFee(
    var amount: Double,
    var reason: String
)

data class BillPayment(
    val paymentDate: String,
    val _amount: Double
) {
    val amount = _amount.formatToCurrency()
}

enum class BillType {
    CreditCard,
    Utility,
    Mortgage,
    Subscription,
    Auto,
    Unspecified
}

object SampleBillObjectList {
    val data = arrayListOf(
        CreditCard(
            id = 1,
            billName = "Discover",
            nextPayment = BillPayment(_amount = 129.70, paymentDate = "09/30/2021"),
            pastDue = 0.0,
            lastPayment = null,
            balance = null,
            comments = "",
            fees = emptyList(),
            billType = BillType.CreditCard,
            link = "",
            pinned = false,
            details = CreditCardLimit(3000.0, 15.69)
        ),
        Mortgage(
            id = 2,
            billName = "Rocket Mortgage",
            nextPayment = BillPayment(_amount = 600.0, paymentDate = "08/20/2021"),
            pastDue = 0.0,
            lastPayment = BillPayment(_amount = 600.0, paymentDate = "07/20/2021"),
            balance = 100000.0,
            comments = "",
            fees = emptyList(),
            billType = BillType.Mortgage,
            link = "",
            pinned = false
        ),
        AutoLoan(
            id = 3,
            billName = "Ford EcoSport",
            nextPayment = BillPayment(_amount = 350.0, paymentDate = "07/28/2021"),
            pastDue = 0.0,
            lastPayment = null,
            balance = 25000.0,
            comments = "",
            fees = emptyList(),
            billType = BillType.Auto,
            link = "",
            pinned = false
        )
    )
}