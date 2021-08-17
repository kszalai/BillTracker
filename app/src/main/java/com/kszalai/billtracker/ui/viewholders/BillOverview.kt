package com.kszalai.billtracker.ui.viewholders

import android.content.res.Configuration
import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
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
import com.kszalai.billtracker.helpers.determineComposableColorFromDate
import com.kszalai.billtracker.helpers.formatToCurrency
import com.kszalai.billtracker.helpers.getIcon
import com.kszalai.billtracker.models.*
import com.kszalai.billtracker.theme.BillTrackerTheme
import com.kszalai.billtracker.theme.billTextColor

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
                        color = MaterialTheme.colors.billTextColor,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = data.nextPayment.amount.formatToCurrency(),
                        fontSize = 12.sp,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colors.billTextColor
                    )
                    Text(
                        text = " - Due On ${data.nextPayment.paymentDate}",
                        fontSize = 12.sp,
                        color = MaterialTheme.colors.billTextColor
                    )
                }
                data.balance?.let {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Balance:",
                            fontSize = 12.sp,
                            fontStyle = FontStyle.Italic,
                            color = MaterialTheme.colors.billTextColor
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = data.balance.formatToCurrency(),
                            fontSize = 12.sp,
                            color = MaterialTheme.colors.billTextColor
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
        onNavigate = { dest, bundle -> }
    )
}