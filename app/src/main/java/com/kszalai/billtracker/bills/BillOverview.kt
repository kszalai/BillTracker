package com.kszalai.billtracker.bills

import android.content.res.Configuration
import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import com.kszalai.billtracker.R
import com.kszalai.billtracker.common.extensions.determineComposableColorFromDate
import com.kszalai.billtracker.common.extensions.formatToCurrency
import com.kszalai.billtracker.common.extensions.getIcon
import com.kszalai.billtracker.common.theme.BillTrackerColors
import com.kszalai.billtracker.common.theme.BillTrackerTheme
import com.kszalai.billtracker.bills.common.models.BillObject
import com.kszalai.billtracker.bills.common.models.SampleBillObjectList

@ExperimentalMaterialApi
@Composable
fun BillOverview(
    data: BillObject,
    onNavigate: (Int, Bundle) -> Unit
) {
    BillTrackerTheme {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 8.dp),
            backgroundColor = data.nextPayment.paymentDate.determineComposableColorFromDate(),
            onClick = {
                val bundle = bundleOf("selectedBill" to data)
                onNavigate(R.id.action_billListFragment_to_billDetailFragment, bundle)
            }
        ) {
            Column(modifier = Modifier.padding(all = 20.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = data.billType.getIcon()),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = data.billName,
                        fontSize = 20.sp,
                        color = BillTrackerColors.TextColor,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = data.nextPayment.amount,
                        fontSize = 12.sp,
                        fontStyle = FontStyle.Italic,
                        color = BillTrackerColors.TextColor
                    )
                    Text(
                        text = " - Due On ${data.nextPayment.paymentDate}",
                        fontSize = 12.sp,
                        color = BillTrackerColors.TextColor
                    )
                }
                data.balance?.let {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Balance:",
                            fontSize = 12.sp,
                            fontStyle = FontStyle.Italic,
                            color = BillTrackerColors.TextColor
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = data.balance.formatToCurrency(),
                            fontSize = 12.sp,
                            color = BillTrackerColors.TextColor
                        )
                    }
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark Mode"
)
@Composable
fun PreviewBillOverview() {
    BillOverview(
        data = SampleBillObjectList.data[0],
        onNavigate = { _, _ -> }
    )
}