package com.kszalai.billtracker.bills.common.services

import com.kszalai.billtracker.bills.common.models.BillObject
import com.kszalai.billtracker.bills.common.models.BillPayment
import com.kszalai.billtracker.bills.common.models.BillType
import com.kszalai.billtracker.bills.common.models.CreditCardLimit

class BillService {

    fun getBills(): List<BillObject> {
        val test = ArrayList<BillObject>()
        test.add(
            BillObject(
                id = 1,
                billName = "Discover",
                nextPayment = BillPayment(_amount = 129.70, paymentDate = "09/20/2021"),
                pastDue = 0.0,
                lastPayment = null,
                balance = null,
                comments = "",
                fees = emptyArray(),
                billType = BillType.CreditCard,
                link = "",
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
                pinned = false,
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