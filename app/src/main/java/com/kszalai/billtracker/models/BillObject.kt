package com.kszalai.billtracker.models

import java.io.Serializable

data class BillObject(val billName: String,
                      var nextPayment: BillPayment,
                      var pastDue: Double,
                      var lastPayment: BillPayment?,
                      var balance: Double?,
                      var comments: String,
                      var fees: Array<BillFee>,
                      val billType: BillType,
                      val pinned: Boolean,
                      val link: String,
                      val details: Any? = null) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BillObject

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

data class BillFee(var amount: Double,
                   var reason: String)

enum class BillType {
    CreditCard,
    Utility,
    Mortgage,
    Subscription,
    Auto
}

object SampleBillObjectList {
    val data = arrayListOf(
        BillObject(
            billName = "Discover",
            nextPayment = BillPayment(amount = 129.70, paymentDate = "09/30/2021"),
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
            billName = "Rocket Mortgage",
            nextPayment = BillPayment(amount = 600.0, paymentDate = "08/20/2021"),
            pastDue = 0.0,
            lastPayment = BillPayment(amount = 600.0, paymentDate = "07/20/2021"),
            balance = 100000.0,
            comments = "",
            fees = emptyArray(),
            billType = BillType.Mortgage,
            link = "",
            pinned = false
        ),
        BillObject(
            billName = "Ford EcoSport",
            nextPayment = BillPayment(amount = 350.0, paymentDate = "07/28/2021"),
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