package com.kszalai.billtracker.bills.common.services

import com.kszalai.billtracker.bills.common.models.*

class BillService {

    fun getBills(): List<BillObject> {
        val test = ArrayList<BillObject>()
        test.add(
            CreditCard(
                id = 1,
                billName = "Discover",
                nextPayment = BillPayment(_amount = 129.70, paymentDate = "09/20/2021"),
                pastDue = 0.0,
                pinned = false,
                details = CreditCardLimit(3000.0, 15.69),
                paymentHistory = listOf(
                    BillPayment(
                        paymentDate = "03/20/22",
                        _amount = 200.0
                    ),
                    BillPayment(
                        paymentDate = "02/20/22",
                        _amount = 150.0
                    )
                )
            )
        )
        test.add(
            Mortgage(
                id = 2,
                billName = "Rocket Mortgage",
                nextPayment = BillPayment(_amount = 600.0, paymentDate = "08/20/2021"),
                pastDue = 0.0,
                lastPayment = BillPayment(_amount = 600.0, paymentDate = "07/20/2021"),
                initialBalance = 1000000.0,
                balance = 700000.0,
                pinned = false,
                paymentHistory = listOf(
                    BillPayment(
                        paymentDate = "04/01/22",
                        _amount = 1000.0
                    ),
                    BillPayment(
                        paymentDate = "03/01/22",
                        _amount = 1000.0
                    ),
                    BillPayment(
                        paymentDate = "02/01/22",
                        _amount = 1000.0
                    ),
                    BillPayment(
                        paymentDate = "01/01/22",
                        _amount = 1000.0
                    )
                )
            )
        )
        test.add(
            AutoLoan(
                id = 3,
                billName = "Ford EcoSport",
                nextPayment = BillPayment(_amount = 350.0, paymentDate = "07/28/2021"),
                pastDue = 250.0,
                initialBalance = 35000.0,
                balance = 25000.0,
                paymentHistory = listOf(
                    BillPayment(
                        paymentDate = "03/15/22",
                        _amount = 300.0
                    ),
                    BillPayment(
                        paymentDate = "02/15/22",
                        _amount = 300.0
                    )
                )
            )
        )

        return test.toList()
    }

}