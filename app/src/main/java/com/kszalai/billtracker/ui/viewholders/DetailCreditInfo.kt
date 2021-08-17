package com.kszalai.billtracker.ui.viewholders

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kszalai.billtracker.helpers.formatToCurrency
import com.kszalai.billtracker.helpers.formatToPercentage
import com.kszalai.billtracker.models.CreditCardLimit
import com.kszalai.billtracker.models.SampleBillObjectList
import com.kszalai.billtracker.theme.BillTrackerTheme
import com.kszalai.billtracker.theme.billTextColor

@Composable
fun DetailCreditInfo(data: CreditCardLimit) {
    BillTrackerTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp)
        ) {
            Row {
                Text(
                    text = "Credit Info",
                    fontSize = 20.sp,
                    color = MaterialTheme.colors.billTextColor
                )
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Row {
                    Text(
                        text = "Credit Limit:",
                        fontSize = 16.sp,
                        color = MaterialTheme.colors.billTextColor
                    )
                    Text(
                        text = data.creditLimit.formatToCurrency(),
                        fontStyle = FontStyle.Italic,
                        fontSize = 16.sp,
                        color = MaterialTheme.colors.billTextColor,
                        modifier = Modifier.padding(start = 3.dp)
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = "APR:",
                        fontSize = 16.sp,
                        color = MaterialTheme.colors.billTextColor
                    )
                    Text(
                        text = data.apr.formatToPercentage(),
                        fontStyle = FontStyle.Italic,
                        fontSize = 16.sp,
                        color = MaterialTheme.colors.billTextColor,
                        modifier = Modifier.padding(start = 3.dp)
                    )
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
fun PreviewDetailCreditInfo() {
    DetailCreditInfo(SampleBillObjectList.data[0].details as CreditCardLimit)
}