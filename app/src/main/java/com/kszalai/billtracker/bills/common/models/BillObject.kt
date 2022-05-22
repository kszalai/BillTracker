package com.kszalai.billtracker.bills.common.models

import com.kszalai.billtracker.bills.add.AddBillUIState
import com.kszalai.billtracker.common.extensions.formatToCurrency
import java.io.Serializable

sealed class BillObject(
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
    open val paymentHistory: List<BillPayment> = emptyList(),
    open val autoPay: Boolean = false,
    open val autoPayDiscount: Double = 0.0
) : Serializable {
    fun calculatePayment(): String {
        return (nextPayment._amount + pastDue - autoPayDiscount).formatToCurrency()
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

    data class Builder(
        var billName: String? = null,
        var nextPayment: BillPayment? = null,
        var pastDue: Double? = null,
        var comments: String? = null,
        var fees: List<BillFee>? = null,
        var link: String? = null,
        var billBalance: BillBalance? = null,
        var creditCardLimit: CreditCard.CreditCardLimit? = null,
        var subscriptionDetails: Subscription.SubscriptionDetails? = null,
        var autoPay: Boolean? = null,
        var autoPayDiscount: Double? = null,
        var type: String? = null
    ) {
        fun billName(billName: String) = apply { this.billName = billName }
        fun nextPayment(nextPayment: BillPayment) = apply { this.nextPayment = nextPayment }
        fun pastDue(pastDue: Double) = apply { this.pastDue = pastDue }
        fun comments(comments: String) = apply { this.comments = comments }
        fun fees(fees: List<BillFee>) = apply { this.fees = fees }
        fun link(link: String) = apply { this.link = link }
        fun billBalance(billBalance: BillBalance) = apply { this.billBalance = billBalance }
        fun creditCardLimit(creditCardLimit: CreditCard.CreditCardLimit) = apply { this.creditCardLimit = creditCardLimit }
        fun subscriptionDetails(subscriptionDetails: Subscription.SubscriptionDetails) = apply { this.subscriptionDetails = subscriptionDetails }
        fun autoPay(autoPay: Boolean) = apply { this.autoPay = autoPay }
        fun autoPayDiscount(autoPayDiscount: Double) = apply { this.autoPayDiscount = autoPayDiscount }
        fun type(type: String) = apply { this.type = type }
        fun fromUIState(state: AddBillUIState) = apply {
            this.type = state.selectedBillType
            this.billName = state.billName
            if (state.creditLimit.isNotEmpty() && state.apr.isNotEmpty()) {
                this.creditCardLimit = CreditCard.CreditCardLimit(
                    limit = state.creditLimit.toDouble(),
                    apr = state.apr.toDouble()
                )
            }
            if (state.pastDue.isNotEmpty()) {
                this.pastDue = state.pastDue.toDouble()
            }
            if (state.comment.isNotEmpty()) {
                this.comments = state.comment
            }
            if (state.link.isNotEmpty()) {
                this.link = state.link
            }
        }
        fun build(): BillObject {
            requireNotNull(type)
            return when (type?.lowercase()) {
                "auto loan" -> AutoLoan(
                    billName = this.billName ?: "",
                    nextPayment = this.nextPayment ?: BillPayment(
                        paymentDate = "",
                        _amount = 0.0
                    ),
                    comments = this.comments ?: "",
                    fees = this.fees ?: emptyList(),
                    link = this.link ?: "",
                    autoPay = this.autoPay ?: false,
                    autoPayDiscount = this.autoPayDiscount ?: 0.0,
                    details = this.billBalance ?: BillBalance()
                )
                "credit card" -> CreditCard(
                    billName = this.billName ?: "",
                    nextPayment = this.nextPayment ?: BillPayment(
                        paymentDate = "",
                        _amount = 0.0
                    ),
                    comments = this.comments ?: "",
                    fees = this.fees ?: emptyList(),
                    link = this.link ?: "",
                    autoPay = this.autoPay ?: false,
                    autoPayDiscount = this.autoPayDiscount ?: 0.0,
                    details = this.creditCardLimit ?: CreditCard.CreditCardLimit(
                        limit = 0.0,
                        apr = 0.0
                    )
                )
                "mortgage" -> Mortgage(
                    billName = this.billName ?: "",
                    nextPayment = this.nextPayment ?: BillPayment(
                        paymentDate = "",
                        _amount = 0.0
                    ),
                    comments = this.comments ?: "",
                    fees = this.fees ?: emptyList(),
                    link = this.link ?: "",
                    autoPay = this.autoPay ?: false,
                    autoPayDiscount = this.autoPayDiscount ?: 0.0,
                    details = this.billBalance ?: BillBalance()
                )
                "subscription" -> Subscription(
                    billName = this.billName ?: "",
                    nextPayment = this.nextPayment ?: BillPayment(
                        paymentDate = "",
                        _amount = 0.0
                    ),
                    comments = this.comments ?: "",
                    fees = this.fees ?: emptyList(),
                    link = this.link ?: "",
                    autoPay = this.autoPay ?: false,
                    autoPayDiscount = this.autoPayDiscount ?: 0.0,
                    details = this.subscriptionDetails ?: Subscription.SubscriptionDetails()
                )
                "utility" -> Utility(
                    billName = this.billName ?: "",
                    nextPayment = this.nextPayment ?: BillPayment(
                        paymentDate = "",
                        _amount = 0.0
                    ),
                    comments = this.comments ?: "",
                    fees = this.fees ?: emptyList(),
                    link = this.link ?: "",
                    autoPay = this.autoPay ?: false,
                    autoPayDiscount = this.autoPayDiscount ?: 0.0
                )
                else -> Generic(
                    billName = this.billName ?: "",
                    nextPayment = this.nextPayment ?: BillPayment(
                        paymentDate = "",
                        _amount = 0.0
                    ),
                    comments = this.comments ?: "",
                    fees = this.fees ?: emptyList(),
                    link = this.link ?: "",
                    autoPay = this.autoPay ?: false,
                    autoPayDiscount = this.autoPayDiscount ?: 0.0
                )
            }
        }
    }

    data class Generic(
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
        override val autoPay: Boolean = false,
        override val autoPayDiscount: Double = 0.0
    ) : BillObject(
        id,
        billName,
        nextPayment,
        pastDue,
        comments,
        fees,
        pinned,
        link,
        paymentHistory,
        autoPay,
        autoPayDiscount
    )

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
        override val autoPay: Boolean = false,
        override val autoPayDiscount: Double = 0.0,
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
        paymentHistory,
        autoPay,
        autoPayDiscount
    ), Serializable

    data class CreditCard(
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
        override val autoPay: Boolean = false,
        override val autoPayDiscount: Double = 0.0,
        val details: CreditCardLimit = CreditCardLimit(
            limit = 0.0,
            apr = 0.0
        )
    ) : BillObject(
        id,
        billName,
        nextPayment,
        pastDue,
        comments,
        fees,
        pinned,
        link,
        paymentHistory,
        autoPay,
        autoPayDiscount
    ), Serializable {
        data class CreditCardLimit(
            val limit: Double,
            val apr: Double
        )
    }

    data class Mortgage(
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
        override val autoPay: Boolean = false,
        override val autoPayDiscount: Double = 0.0,
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
        paymentHistory,
        autoPay,
        autoPayDiscount
    ), Serializable

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
        override val autoPay: Boolean = false,
        override val autoPayDiscount: Double = 0.0,
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
        paymentHistory,
        autoPay,
        autoPayDiscount
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

    data class Utility(
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
        override val autoPay: Boolean = false,
        override val autoPayDiscount: Double = 0.0
    ) : BillObject(
        id,
        billName,
        nextPayment,
        pastDue,
        comments,
        fees,
        pinned,
        link,
        paymentHistory,
        autoPay,
        autoPayDiscount
    ), Serializable
}

object SampleBillObjectList {
    val data = arrayListOf(
        BillObject.CreditCard(
            id = 1,
            billName = "Discover",
            nextPayment = BillObject.BillPayment(_amount = 129.70, paymentDate = "09/30/2021"),
            pastDue = 0.0,
            comments = "",
            fees = emptyList(),
            link = "",
            pinned = false,
            details = BillObject.CreditCard.CreditCardLimit(3000.0, 15.69)
        ),
        BillObject.Mortgage(
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
        BillObject.AutoLoan(
            id = 3,
            billName = "Ford EcoSport",
            nextPayment = BillObject.BillPayment(_amount = 350.0, paymentDate = "07/28/2021"),
            pastDue = 0.0,
            comments = "",
            fees = emptyList(),
            link = "",
            pinned = false,
            details = BillObject.BillBalance(_balance = 25000.0)
        ),
        BillObject.Utility(
            id = 4,
            billName = "Comcast Internet",
            nextPayment = BillObject.BillPayment(_amount = 49.99, paymentDate = "06/11/2022"),
            pastDue = 0.0,
            comments = "",
            fees = emptyList(),
            link = "",
            pinned = false,
            autoPay = true,
            autoPayDiscount = 5.0
        )
    )
}