package com.kszalai.billtracker.ui.viewholders

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kszalai.billtracker.helpers.formatToCurrency
import com.kszalai.billtracker.models.BillPayment
import com.kszalai.billtracker.models.SampleBillObjectList
import com.kszalai.billtracker.theme.BillTrackerTheme
import com.kszalai.billtracker.theme.billTextColor

@Composable
fun DetailHistoryItem(data: BillPayment) {
    BillTrackerTheme {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp)
        ) {
            Column(modifier = Modifier.weight(weight = 1f)) {
                Text(
                    text = data.paymentDate,
                    color = MaterialTheme.colors.billTextColor
                )
            }
            Column(
                modifier = Modifier.weight(weight = 1f),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = data.amount.formatToCurrency(),
                    color = MaterialTheme.colors.billTextColor
                )
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
fun PreviewDetailHistoryItem() {
    DetailHistoryItem(SampleBillObjectList.data[0].nextPayment)
}