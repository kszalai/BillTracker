package com.kszalai.billtracker.bills.common.services

import com.kszalai.billtracker.bills.common.models.*

class BillService {

    fun getBills(): List<BillObject> {
        return ArrayList<BillObject>().apply {
            add(
                CreditCard(
                    id = 1,
                    billName = "Discover",
                    nextPayment = BillObject.BillPayment(
                        _amount = 129.70,
                        paymentDate = "05/10/2022"
                    ),
                    pastDue = 0.0,
                    pinned = false,
                    details = CreditCard.CreditCardLimit(
                        limit = 3000.0,
                        apr = 15.69
                    ),
                    paymentHistory = listOf(
                        BillObject.BillPayment(
                            paymentDate = "03/20/22",
                            _amount = 200.0
                        ),
                        BillObject.BillPayment(
                            paymentDate = "02/20/22",
                            _amount = 150.0
                        )
                    )
                )
            )
            add(
                Mortgage(
                    id = 2,
                    billName = "Rocket Mortgage",
                    nextPayment = BillObject.BillPayment(
                        _amount = 600.0,
                        paymentDate = "05/20/2022"
                    ),
                    pastDue = 0.0,
                    pinned = false,
                    paymentHistory = listOf(
                        BillObject.BillPayment(
                            paymentDate = "04/01/22",
                            _amount = 1000.0
                        ),
                        BillObject.BillPayment(
                            paymentDate = "03/01/22",
                            _amount = 1000.0
                        ),
                        BillObject.BillPayment(
                            paymentDate = "02/01/22",
                            _amount = 1000.0
                        ),
                        BillObject.BillPayment(
                            paymentDate = "01/01/22",
                            _amount = 1000.0
                        )
                    ),
                    details = BillObject.BillBalance(
                        _balance = 700000.0,
                        _initialBalance = 1000000.0
                    )
                )
            )
            add(
                AutoLoan(
                    id = 3,
                    billName = "Ford EcoSport",
                    nextPayment = BillObject.BillPayment(
                        _amount = 350.0,
                        paymentDate = "07/28/2022"
                    ),
                    pastDue = 250.0,
                    paymentHistory = listOf(
                        BillObject.BillPayment(
                            paymentDate = "03/15/22",
                            _amount = 300.0
                        ),
                        BillObject.BillPayment(
                            paymentDate = "02/15/22",
                            _amount = 300.0
                        )
                    ),
                    details = BillObject.BillBalance(
                        _balance = 25000.0,
                        _initialBalance = 35000.0
                    )
                )
            )
            add(
                Subscription(
                    id = 4,
                    billName = "Spotify",
                    nextPayment = BillObject.BillPayment(
                        _amount = 9.99,
                        paymentDate = "05/25/2022"
                    ),
                    details = Subscription.SubscriptionDetails(
                        _amount = 9.99,
                        frequency = Subscription.SubscriptionDetails.SubscriptionFrequency.MONTHLY,
                        frequencyNotes = "Every 25th of the month"
                    )
                )
            )
        }.toList()
    }

}