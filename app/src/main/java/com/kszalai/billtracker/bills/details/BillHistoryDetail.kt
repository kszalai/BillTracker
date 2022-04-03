package com.kszalai.billtracker.bills.details

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kszalai.billtracker.R
import com.kszalai.billtracker.common.extensions.formatToCurrency
import com.kszalai.billtracker.common.extensions.formatToPercentage
import com.kszalai.billtracker.common.extensions.getIcon
import com.kszalai.billtracker.common.theme.BillTrackerColors
import com.kszalai.billtracker.common.theme.BillTrackerTheme
import com.kszalai.billtracker.bills.common.models.*

@Composable
fun BillDetails(
    viewModel: BillDetailViewModel = viewModel()
) {
    val uiState by viewModel.uiState
    BillDetails(
        onAddBillDetailsClick = {},
        data = uiState
    )
}

@Composable
private fun BillDetails(
    onAddBillDetailsClick: () -> Unit,
    data: BillDetailsUIState
) {
    BillTrackerTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    vertical = 16.dp,
                    horizontal = 20.dp
                )
        ) {
            Column {
                if (data.loading) {
                    Text("Loading...")
                } else {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = data.selectedBill.billType.getIcon()),
                            contentDescription = "Bill Type Icon",
                            tint = BillTrackerColors.TextColor
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = data.selectedBill.billName,
                            fontSize = 32.sp,
                            color = BillTrackerColors.TextColor
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(
                            modifier = Modifier.padding(
                                horizontal = 15.dp,
                                vertical = 8.dp
                            )
                        ) {
                            Row {
                                Text(
                                    text = stringResource(id = R.string.amountDue),
                                    fontSize = 20.sp
                                )
                                Spacer(modifier = Modifier.width(3.dp))
                                Text(
                                    text = data.selectedBill.nextPayment.amount,
                                    fontSize = 20.sp,
                                    fontStyle = FontStyle.Italic
                                )
                            }
                            Row {
                                Text(
                                    text = stringResource(id = R.string.dueOn),
                                    fontSize = 20.sp
                                )
                                Spacer(modifier = Modifier.width(3.dp))
                                Text(
                                    text = data.selectedBill.nextPayment.paymentDate,
                                    fontSize = 20.sp,
                                    fontStyle = FontStyle.Italic
                                )
                            }
                        }
                    }
                    data.selectedBill.lastPayment?.let {
                        Spacer(modifier = Modifier.height(8.dp))
                        Card(modifier = Modifier.fillMaxWidth()) {
                            Column(
                                modifier = Modifier.padding(
                                    horizontal = 15.dp,
                                    vertical = 8.dp
                                )
                            ) {
                                Text(
                                    text = stringResource(id = R.string.lastPayment),
                                    fontStyle = FontStyle.Italic
                                )
                                Row {
                                    Text(
                                        text = stringResource(id = R.string.amountDue),
                                        fontSize = 20.sp
                                    )
                                    Spacer(modifier = Modifier.width(3.dp))
                                    Text(text = it.amount)
                                }
                                Row {
                                    Text(
                                        text = stringResource(id = R.string.dueOn),
                                        fontSize = 20.sp
                                    )
                                    Spacer(modifier = Modifier.width(3.dp))
                                    Text(text = it.paymentDate)
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    BillHistoryDetail(
                        creditCardLimit = data.selectedBill.details as CreditCardLimit,
                        payments = data.selectedBill.paymentHistory
                    )
                }
            }
            FloatingActionButton(
                onClick = onAddBillDetailsClick,
                modifier = Modifier.align(Alignment.BottomEnd)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add_24px),
                    contentDescription = "Add details about this bill"
                )
            }
        }
    }
}

@Composable
private fun BillHistoryDetail(
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
                color = BillTrackerColors.TextColor
            )
            Row(modifier = Modifier.padding(top = 8.dp)) {
                Column(modifier = Modifier.weight(weight = 1f)) {
                    Text(
                        text = "Payment Date",
                        fontSize = 16.sp,
                        color = BillTrackerColors.TextColor
                    )
                }
                Column(
                    modifier = Modifier.weight(weight = 1f),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = "Amount",
                        fontSize = 16.sp,
                        color = BillTrackerColors.TextColor
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

@Composable
private fun DetailCreditInfo(data: CreditCardLimit) {
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
                    color = BillTrackerColors.TextColor
                )
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Row {
                    Text(
                        text = "Credit Limit:",
                        fontSize = 16.sp,
                        color = BillTrackerColors.TextColor
                    )
                    Text(
                        text = data.creditLimit.formatToCurrency(),
                        fontStyle = FontStyle.Italic,
                        fontSize = 16.sp,
                        color = BillTrackerColors.TextColor,
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
                        color = BillTrackerColors.TextColor
                    )
                    Text(
                        text = data.apr.formatToPercentage(),
                        fontStyle = FontStyle.Italic,
                        fontSize = 16.sp,
                        color = BillTrackerColors.TextColor,
                        modifier = Modifier.padding(start = 3.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun DetailHistoryItem(data: BillPayment) {
    BillTrackerTheme {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp)
        ) {
            Column(modifier = Modifier.weight(weight = 1f)) {
                Text(
                    text = data.paymentDate,
                    color = BillTrackerColors.TextColor
                )
            }
            Column(
                modifier = Modifier.weight(weight = 1f),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = data.amount,
                    color = BillTrackerColors.TextColor
                )
            }
        }
    }
}

@Preview(name = "Bill Detail - Light Mode")
@Preview(
    name = "Bill Detail - Dark Mode",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun PreviewBillDetails() {
    BillDetails(
        onAddBillDetailsClick = { },
        data = BillDetailsUIState(
            selectedBill = SampleBillObjectList.data[0]
        )
    )
}


@Preview(name = "Light Mode")
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun PreviewBillHistoryDetail() {
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
private fun PreviewBillHistoryDetailWithoutCredit() {
    BillHistoryDetail(
        payments = listOf(
            BillPayment("07/02/20", 350.0),
            BillPayment("06/20/20", 350.0)
        )
    )
}