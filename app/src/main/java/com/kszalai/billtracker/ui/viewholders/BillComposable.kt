package com.kszalai.billtracker.ui.viewholders

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Colors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kszalai.billtracker.helpers.formatToCurrency
import com.kszalai.billtracker.helpers.getIcon
import com.kszalai.billtracker.models.BillObject
import com.kszalai.billtracker.models.BillPayment
import com.kszalai.billtracker.models.BillType
import com.kszalai.billtracker.models.CreditCardLimit

@Composable
fun BillComposable(
    data: BillObject
) {
    Card(
        backgroundColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .padding(
                    all = 20.dp
                )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = data.billType.getIcon()),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = data.billName,
                    fontSize = 20.sp
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = data.nextPayment.amount.formatToCurrency(),
                    fontSize = 12.sp,
                    fontStyle = FontStyle.Italic
                )
                Text(
                    text = " - Due On ${data.nextPayment.paymentDate}",
                    fontSize = 12.sp
                )
            }
            data.balance?.let {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Balance:",
                        fontSize = 12.sp,
                        fontStyle = FontStyle.Italic
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = data.balance.formatToCurrency(),
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewBillComposable() {
    BillComposable(
        data = BillObject(
            billName = "Discover",
            nextPayment = BillPayment(
                amount = 129.70,
                paymentDate = "06/06/2020"
            ),
            pastDue = 0.0,
            lastPayment = null,
            balance = null,
            comments = "",
            fees = emptyArray(),
            billType = BillType.CreditCard,
            link = "",
            pinned = false,
            details = CreditCardLimit(
                creditLimit = 3000.0,
                apr = 15.69
            )
        )
    )
}