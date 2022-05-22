package com.kszalai.billtracker.bills.details

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kszalai.billtracker.R
import com.kszalai.billtracker.bills.common.models.*
import com.kszalai.billtracker.common.extensions.formatToCurrency
import com.kszalai.billtracker.common.extensions.formatToPercentage
import com.kszalai.billtracker.common.extensions.getIcon
import com.kszalai.billtracker.common.theme.BillTrackerColors
import com.kszalai.billtracker.common.theme.BillTrackerTheme

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
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            if (data.loading) {
                Text("Loading...")
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = data.selectedBill.getIcon()),
                            contentDescription = "Bill Type Icon",
                            tint = BillTrackerColors.TextColor
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = data.selectedBill.billName,
                            fontSize = 32.sp
                        )
                    }
                    if (data.selectedBill.autoPay) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_autopay),
                            contentDescription = "Auto Pay enabled icon",
                            tint = BillTrackerColors.TextColor,
                            modifier = Modifier.height(30.dp)
                        )
                    }
                }
                BillTypeDetails(data = data.selectedBill)
                BillPayment(data = data.selectedBill)
                PastDue(pastDue = data.selectedBill.pastDue)
                Spacer(modifier = Modifier.height(8.dp))
                BillHistoryDetail(
                    payments = data.selectedBill.paymentHistory
                )
            }
        }
        FloatingActionButton(
            onClick = onAddBillDetailsClick,
            modifier = Modifier
                .padding(all = 16.dp)
                .align(Alignment.BottomEnd)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_add_24px),
                contentDescription = "Add details about this bill"
            )
        }
    }
}

@Composable
private fun BillPayment(data: BillObject) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = 16.dp,
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
                    text = data.calculatePayment(),
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
                    text = data.nextPayment.paymentDate,
                    fontSize = 20.sp,
                    fontStyle = FontStyle.Italic
                )
            }
            if (data.pastDue != 0.0) {
                Text(
                    text = "Original Amount: ${data.nextPayment.amount}",
                    fontSize = 13.sp
                )
            }
        }
    }
}

@Composable
private fun PastDue(pastDue: Double) {
    if (pastDue != 0.0) {
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            backgroundColor = BillTrackerColors.Error,
            contentColor = BillTrackerColors.OnError,
            elevation = 4.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(
                    horizontal = 15.dp,
                    vertical = 8.dp
                )
            ) {
                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.warning),
                        contentDescription = "Past Due Warning Icon",
                        tint = BillTrackerColors.OnError
                    )
                    Text(
                        text = "Past Due! ${pastDue.formatToCurrency()}",
                        fontSize = 20.sp,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun BillTypeDetails(data: BillObject) {
    Column {
        Spacer(modifier = Modifier.height(8.dp))
        when (data) {
            is BillObject.AutoLoan -> BillBalance(data = data.details)
            is BillObject.CreditCard -> DetailCreditInfo(data = data.details)
            is BillObject.Mortgage -> BillBalance(data = data.details)
            is BillObject.Subscription -> SubscriptionDetails(data = data.details)
        }
        Spacer(modifier = Modifier.height(8.dp))
        if (data.autoPay) {
            Row {
                Text(text = "Auto Pay Enabled")
                if (data.autoPayDiscount != 0.0) {
                    Text(text = " - Discount ${data.autoPayDiscount.formatToCurrency()}")
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun BillBalance(data: BillObject.BillBalance) {
    if (data._initialBalance != 0.0) {
        Text(
            text = "Initial Balance: ${data.initialBalance}",
            fontSize = 16.sp
        )
    }
    if (data._balance != 0.0) {
        Text(
            text = "Balance: ${data.balance}",
            fontSize = 16.sp
        )
    }
}

@Composable
private fun SubscriptionDetails(data: BillObject.Subscription.SubscriptionDetails) {
    Column {
        Text(
            text = "Subscription Details",
            fontSize = 20.sp
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Amount: ${data.amount}",
                fontSize = 16.sp,
                modifier = Modifier.weight(1f)
            )
            if (data.frequency != BillObject.Subscription.SubscriptionDetails.SubscriptionFrequency.UNSPECIFIED) {
                Text(
                    text = when (data.frequency) {
                        BillObject.Subscription.SubscriptionDetails.SubscriptionFrequency.WEEKLY -> "Weekly"
                        BillObject.Subscription.SubscriptionDetails.SubscriptionFrequency.MONTHLY -> "Monthly"
                        BillObject.Subscription.SubscriptionDetails.SubscriptionFrequency.YEARLY -> "Yearly"
                        else -> ""
                    },
                    fontSize = 16.sp,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.End
                )
            }
        }
    }
}

@Composable
private fun BillHistoryDetail(payments: List<BillObject.BillPayment>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 8.dp,
                bottom = 16.dp
            ),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                )
        ) {
            Text(
                text = "Payment History",
                fontSize = 24.sp,
                color = BillTrackerColors.TextColor
            )
            Spacer(modifier = Modifier.height(8.dp))
            if (payments.isNotEmpty()) {
                Row {
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
            } else {
                Text(
                    text = "No Payment History",
                    fontSize = 16.sp,
                    fontStyle = FontStyle.Italic
                )
            }
        }
    }
}

@Composable
private fun DetailCreditInfo(data: BillObject.CreditCard.CreditCardLimit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row {
            Text(
                text = "Credit Info",
                fontSize = 20.sp
            )
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Row {
                Text(
                    text = "Credit Limit:",
                    fontSize = 16.sp
                )
                Text(
                    text = data.limit.formatToCurrency(),
                    fontStyle = FontStyle.Italic,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 3.dp)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "APR:",
                    fontSize = 16.sp
                )
                Text(
                    text = data.apr.formatToPercentage(),
                    fontStyle = FontStyle.Italic,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 3.dp)
                )
            }
        }
    }
}

@Composable
private fun DetailHistoryItem(data: BillObject.BillPayment) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp)
    ) {
        Column(modifier = Modifier.weight(weight = 1f)) {
            Text(text = data.paymentDate)
        }
        Column(
            modifier = Modifier.weight(weight = 1f),
            horizontalAlignment = Alignment.End
        ) {
            Text(text = data.amount)
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
    BillTrackerTheme {
        BillDetails(
            onAddBillDetailsClick = { },
            data = BillDetailsUIState(
                selectedBill = SampleBillObjectList.data[0]
            )
        )
    }
}
