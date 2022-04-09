package com.kszalai.billtracker.bills.common.models

import java.io.Serializable

data class BillObject(
    val id: Int = -1,
    val billName: String = "",
    var nextPayment: BillPayment = BillPayment(
        paymentDate = "",
        _amount = 0.0
    ),
    var pastDue: Double = 0.0,
    var lastPayment: BillPayment? = null,
    var balance: Double? = null,
    var comments: String = "",
    var fees: Array<BillFee> = emptyArray(),
    val billType: BillType = BillType.Unspecified,
    val pinned: Boolean = false,
    val link: String = "",
    val paymentHistory: List<BillPayment> = emptyList(),
    val details: Any? = null
) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BillObject

        if (id != other.id) return false
        if (billName != other.billName) return false
        if (nextPayment != other.nextPayment) return false
        if (pastDue != other.pastDue) return false
        if (lastPayment != other.lastPayment) return false
        if (balance != other.balance) return false
        if (comments != other.comments) return false
        if (!fees.contentEquals(other.fees)) return false
        if (billType != other.billType) return false
        if (pinned != other.pinned) return false
        if (link != other.link) return false
        if (details != other.details) return false

        return true
    }

    override fun hashCode(): Int {
        var result = billName.hashCode()
        result = 31 * result + id.hashCode()
        result = 31 * result + nextPayment.hashCode()
        result = 31 * result + pastDue.hashCode()
        result = 31 * result + (lastPayment?.hashCode() ?: 0)
        result = 31 * result + (balance?.hashCode() ?: 0)
        result = 31 * result + comments.hashCode()
        result = 31 * result + fees.contentHashCode()
        result = 31 * result + billType.hashCode()
        result = 31 * result + pinned.hashCode()
        result = 31 * result + link.hashCode()
        result = 31 * result + (details?.hashCode() ?: 0)
        return result
    }
}

data class BillFee(
    var amount: Double,
    var reason: String
)

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
        BillObject(
            id = 1,
            billName = "Discover",
            nextPayment = BillPayment(_amount = 129.70, paymentDate = "09/30/2021"),
            pastDue = 0.0,
            lastPayment = null,
            balance = null,
            comments = "",
            fees = emptyArray(),
            billType = BillType.CreditCard,
            link = "",
            pinned = false,
            details = CreditCardLimit(3000.0, 15.69)
        ),
        BillObject(
            id = 2,
            billName = "Rocket Mortgage",
            nextPayment = BillPayment(_amount = 600.0, paymentDate = "08/20/2021"),
            pastDue = 0.0,
            lastPayment = BillPayment(_amount = 600.0, paymentDate = "07/20/2021"),
            balance = 100000.0,
            comments = "",
            fees = emptyArray(),
            billType = BillType.Mortgage,
            link = "",
            pinned = false
        ),
        BillObject(
            id = 3,
            billName = "Ford EcoSport",
            nextPayment = BillPayment(_amount = 350.0, paymentDate = "07/28/2021"),
            pastDue = 0.0,
            lastPayment = null,
            balance = 25000.0,
            comments = "",
            fees = emptyArray(),
            billType = BillType.Auto,
            link = "",
            pinned = false
        )
    )
}