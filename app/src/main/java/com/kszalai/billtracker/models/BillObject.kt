package com.kszalai.billtracker.models

data class BillObject(val billName: String,
                      var amount: Double,
                      var dueDate: String,
                      var pastDue: Double,
                      var amountPaid: Double?,
                      var datePaid: String?,
                      var balance: Double?,
                      var comments: String,
                      var fees: Array<BillFee>,
                      val billType: BillType,
                      val pinned: Boolean,
                      val link: String,
                      val details: Any? = null) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BillObject

        if (billName != other.billName) return false
        if (amount != other.amount) return false
        if (dueDate != other.dueDate) return false
        if (pastDue != other.pastDue) return false
        if (amountPaid != other.amountPaid) return false
        if (datePaid != other.datePaid) return false
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
        result = 31 * result + amount.hashCode()
        result = 31 * result + dueDate.hashCode()
        result = 31 * result + pastDue.hashCode()
        result = 31 * result + (amountPaid?.hashCode() ?: 0)
        result = 31 * result + (datePaid?.hashCode() ?: 0)
        result = 31 * result + (balance?.hashCode() ?: 0)
        result = 31 * result + comments.hashCode()
        result = 31 * result + fees.contentHashCode()
        result = 31 * result + billType.hashCode()
        result = 31 * result + pinned.hashCode()
        result = 31 * result + link.hashCode()
        result = 31 * result + details.hashCode()
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