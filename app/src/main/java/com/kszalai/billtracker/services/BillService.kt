package com.kszalai.billtracker.services

import com.kszalai.billtracker.models.BillObject
import com.kszalai.billtracker.models.BillPayment
import com.kszalai.billtracker.models.BillType
import com.kszalai.billtracker.models.CreditCardLimit

class BillService {

    fun getBills(): List<BillObject> {
        val test = ArrayList<BillObject>()
        test.add(
            BillObject(
                billName = "Discover",
                nextPayment = BillPayment(amount = 129.70, paymentDate = "09/20/2021"),
                pastDue = 0.0,
                lastPayment = null,
                balance = null,
                comments = "",
                fees = emptyArray(),
                billType = BillType.CreditCard,
                link = "",
                pinned = false,
                details = CreditCardLimit(3000.0, 15.69)
            )
        )
        test.add(
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
            )
        )
        test.add(
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

        return test.toList()
    }

}