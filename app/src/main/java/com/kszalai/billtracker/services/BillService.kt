package com.kszalai.billtracker.services

import com.kszalai.billtracker.models.BillObject
import com.kszalai.billtracker.models.BillType

class BillService {

    fun getBills(): ArrayList<Any> {
        val test = ArrayList<Any>()
        test.add(
            BillObject(
                billName = "Discover",
                amount = 129.70,
                dueDate = "06/06/2020",
                pastDue = 0.0,
                amountPaid = 0.0,
                datePaid = null,
                balance = null,
                comments = "",
                fees = emptyArray(),
                billType = BillType.CreditCard,
                link = "",
                pinned = false
            )
        )
        test.add(
            BillObject(
                billName = "Rocket Mortgage",
                amount = 600.00,
                dueDate = "06/20/2020",
                pastDue = 0.0,
                amountPaid = 0.0,
                datePaid = null,
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
                amount = 350.00,
                dueDate = "06/28/2020",
                pastDue = 0.0,
                amountPaid = 0.0,
                datePaid = null,
                balance = 25000.0,
                comments = "",
                fees = emptyArray(),
                billType = BillType.Auto,
                link = "",
                pinned = false
            )
        )

        return test
    }

}