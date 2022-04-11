package com.kszalai.billtracker.bills.common.models

import com.kszalai.billtracker.common.extensions.formatToCurrency
import java.io.Serializable

data class Subscription(
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
    val details: SubscriptionDetails = SubscriptionDetails()
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
), Serializable {
    data class SubscriptionDetails(
        private val _amount: Double = 0.0,
        val frequency: SubscriptionFrequency = SubscriptionFrequency.UNSPECIFIED,
        val frequencyNotes: String = ""
    ) {
        val amount = _amount.formatToCurrency()

        enum class SubscriptionFrequency {
            WEEKLY,
            MONTHLY,
            YEARLY,
            UNSPECIFIED
        }
    }
}
