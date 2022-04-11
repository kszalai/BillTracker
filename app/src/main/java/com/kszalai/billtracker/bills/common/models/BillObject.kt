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
    open var comments: String = "",
    open var fees: List<BillFee> = emptyList(),
    open val pinned: Boolean = false,
    open val link: String = "",
    open val paymentHistory: List<BillPayment> = emptyList()
) : Serializable {
    fun calculatePayment(): String {
        return (nextPayment._amount + pastDue).formatToCurrency()
    }

    data class BillFee(
        var amount: Double,
        var reason: String
    )

    data class BillBalance(
        var _balance: Double = 0.0,
        val _initialBalance: Double = 0.0
    ) {
        val balance = _balance.formatToCurrency()
        val initialBalance = _initialBalance.formatToCurrency()
    }

    data class BillPayment(
        val paymentDate: String,
        val _amount: Double,
        val onTime: Boolean = true
    ) {
        val amount = _amount.formatToCurrency()
    }
}

object SampleBillObjectList {
    val data = arrayListOf(
        CreditCard(
            id = 1,
            billName = "Discover",
            nextPayment = BillObject.BillPayment(_amount = 129.70, paymentDate = "09/30/2021"),
            pastDue = 0.0,
            comments = "",
            fees = emptyList(),
            link = "",
            pinned = false,
            details = CreditCard.CreditCardLimit(3000.0, 15.69)
        ),
        Mortgage(
            id = 2,
            billName = "Rocket Mortgage",
            nextPayment = BillObject.BillPayment(_amount = 600.0, paymentDate = "08/20/2021"),
            pastDue = 0.0,
            comments = "",
            fees = emptyList(),
            link = "",
            pinned = false,
            details = BillObject.BillBalance(_balance = 100000.0)
        ),
        AutoLoan(
            id = 3,
            billName = "Ford EcoSport",
            nextPayment = BillObject.BillPayment(_amount = 350.0, paymentDate = "07/28/2021"),
            pastDue = 0.0,
            comments = "",
            fees = emptyList(),
            link = "",
            pinned = false,
            details = BillObject.BillBalance(_balance = 25000.0)
        )
    )
}