package com.kszalai.billtracker.ui.billdetails

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kszalai.billtracker.models.BillPayment
import com.kszalai.billtracker.models.CreditCardLimit
import com.kszalai.billtracker.theme.BillTrackerTheme
import com.kszalai.billtracker.theme.billTextColor
import com.kszalai.billtracker.ui.viewholders.DetailCreditInfo
import com.kszalai.billtracker.ui.viewholders.DetailHistoryItem

@Composable
fun BillHistoryDetail(
    payments: List<BillPayment>,
    creditCardLimit: CreditCardLimit? = null
) {
    BillTrackerTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            creditCardLimit?.let {
                DetailCreditInfo(data = it)
            }
            Text(
                text = "Payment History",
                fontSize = 24.sp,
                color = MaterialTheme.colors.billTextColor
            )
            Row(modifier = Modifier.padding(top = 8.dp)) {
                Column(modifier = Modifier.weight(weight = 1f)) {
                    Text(
                        text = "Payment Date",
                        fontSize = 16.sp,
                        color = MaterialTheme.colors.billTextColor
                    )
                }
                Column(
                    modifier = Modifier.weight(weight = 1f),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = "Amount",
                        fontSize = 16.sp,
                        color = MaterialTheme.colors.billTextColor
                    )
                }
            }
            Row {
               LazyColumn {
                   items(payments) { info ->
                        DetailHistoryItem(data = info)
                   }
               }
            }
        }
    }
}

@Preview(name = "Light Mode")
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PreviewBillHistoryDetail() {
    BillHistoryDetail(
        payments = listOf(
            BillPayment("07/02/20", 350.0),
            BillPayment("06/20/20", 350.0)
        ),
        creditCardLimit = CreditCardLimit(creditLimit = 3000.0, apr = 15.96)
    )
}

@Preview(name = "Light Mode without Credit Limit")
@Preview(
    name = "Dark Mode without Credit Limit",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PreviewBillHistoryDetailWithoutCredit() {
    BillHistoryDetail(
        payments = listOf(
            BillPayment("07/02/20", 350.0),
            BillPayment("06/20/20", 350.0)
        )
    )
}