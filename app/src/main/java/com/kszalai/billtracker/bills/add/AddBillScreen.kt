package com.kszalai.billtracker.bills.add

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kszalai.billtracker.bills.common.models.BillObject
import com.kszalai.billtracker.common.theme.BillTrackerColors
import com.kszalai.billtracker.common.theme.BillTrackerTheme

@ExperimentalMaterialApi
@Composable
fun AddBillScreen(
    viewModel: AddBillViewModel = viewModel()
) {
    val uiState by viewModel.uiState
    AddBillScreen(
        onBillNameChange = viewModel::onBillNameChange,
        onAprChange = viewModel::onAprChange,
        onBillTypeExpandedChange = viewModel::onBillTypeExpandedChange,
        onBillTypeSelected = viewModel::onBillTypeSelected,
        onCreditLimitChange = viewModel::onCreditLimitChange,
        onAutoPayCheckChanged = viewModel::onAutoPayCheckChanged,
        onAutoPayDiscountChanged = viewModel::onAutoPayDiscountChanged,
        onLinkChange = viewModel::onLinkChanged,
        onCommentsChange = viewModel::onCommentChanged,
        onInitialLoanAmountChange = viewModel::onInitialLoanAmountChanged,
        onCurrentLoanAmountChange = viewModel::onCurrentLoanAmountChanged,
        onSubscriptionFrequencyExpandedChange = viewModel::onSubscriptionFrequencyExpandedChange,
        onSubscriptionFrequencySelected = viewModel::onSubscriptionFrequencySelected,
        onSubscriptionAmountChange = viewModel::onSubscriptionAmountChange,
        onAddBill = viewModel::onAddBill,
        data = uiState
    )
}

@ExperimentalMaterialApi
@Composable
private fun AddBillScreen(
    onBillNameChange: (String) -> Unit,
    onAprChange: (String) -> Unit,
    onBillTypeExpandedChange: (Boolean) -> Unit,
    onBillTypeSelected: (String) -> Unit,
    onCreditLimitChange: (String) -> Unit,
    onAutoPayCheckChanged: (Boolean) -> Unit,
    onAutoPayDiscountChanged: (String) -> Unit,
    onLinkChange: (String) -> Unit,
    onCommentsChange: (String) -> Unit,
    onInitialLoanAmountChange: (String) -> Unit,
    onCurrentLoanAmountChange: (String) -> Unit,
    onSubscriptionFrequencyExpandedChange: (Boolean) -> Unit,
    onSubscriptionFrequencySelected: (String) -> Unit,
    onSubscriptionAmountChange: (String) -> Unit,
    onAddBill: () -> Unit,
    data: AddBillUIState
) {
    val billTypes = BillObject.BillTypes.dropdownChoices()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                vertical = 20.dp,
                horizontal = 16.dp
            )
    ) {
        Column {
            Text(
                text = "Add A New Bill",
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = data.billName,
                onValueChange = onBillNameChange,
                label = {
                    Text(
                        text = "Bill Name",
                        color = BillTrackerColors.TextColor
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                colors = BillTrackerColors.billTrackerOutlinedTextFieldColors()
            )
            Spacer(modifier = Modifier.height(8.dp))
            ExposedDropdownMenuBox(
                expanded = data.billTypeExpanded,
                onExpandedChange = onBillTypeExpandedChange,
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = data.selectedBillType,
                    onValueChange = {},
                    label = {
                        Text(
                            text = "Bill Type",
                            color = BillTrackerColors.TextColor
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = BillTrackerColors.billTrackerOutlinedTextFieldColors()
                )
                ExposedDropdownMenu(
                    expanded = data.billTypeExpanded,
                    onDismissRequest = { onBillTypeExpandedChange(false) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    billTypes.forEach { billType ->
                        DropdownMenuItem(
                            onClick = { onBillTypeSelected(billType) },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = billType,
                                color = BillTrackerColors.TextColor
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = data.link,
                onValueChange = onLinkChange,
                label = {
                    Text(
                        text = "Link to payment portal",
                        color = BillTrackerColors.TextColor
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                colors = BillTrackerColors.billTrackerOutlinedTextFieldColors()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = data.comment,
                onValueChange = onCommentsChange,
                label = {
                    Text(
                        text = "Comments",
                        color = BillTrackerColors.TextColor
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                colors = BillTrackerColors.billTrackerOutlinedTextFieldColors()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = data.autoPay,
                    onCheckedChange = onAutoPayCheckChanged
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Auto Pay Enabled?"
                )
            }
            if (data.autoPay) {
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = data.autoPayDiscount,
                    onValueChange = onAutoPayDiscountChanged,
                    label = {
                        Text(
                            text = "Auto Pay Discount",
                            color = BillTrackerColors.TextColor
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = BillTrackerColors.billTrackerOutlinedTextFieldColors()
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            when (data.selectedBillType) {
                BillObject.BillTypes.CreditCard.dropdownChoice -> CreditCardDetails(
                    onAprChange = onAprChange,
                    onCreditLimitChange = onCreditLimitChange,
                    data = data
                )
                BillObject.BillTypes.Mortgage.dropdownChoice,
                BillObject.BillTypes.Loan.dropdownChoice,
                BillObject.BillTypes.AutoLoan.dropdownChoice,
                BillObject.BillTypes.StudentLoan.dropdownChoice -> LoanBalance(
                    onInitialLoanAmountChange = onInitialLoanAmountChange,
                    onCurrentLoanAmountChange = onCurrentLoanAmountChange,
                    data = data
                )
                BillObject.BillTypes.Subscription.dropdownChoice -> SubscriptionDetails(
                    onSubscriptionFrequencyExpandedChange = onSubscriptionFrequencyExpandedChange,
                    onSubscriptionFrequencySelected = onSubscriptionFrequencySelected,
                    onSubscriptionAmountChange = onSubscriptionAmountChange,
                    data = data
                )
            }

        }
        Button(
            onClick = onAddBill,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            Text(text = "Add Bill")
        }
    }
}

@Composable
private fun CreditCardDetails(
    onAprChange: (String) -> Unit,
    onCreditLimitChange: (String) -> Unit,
    data: AddBillUIState
) {
    OutlinedTextField(
        value = data.apr,
        onValueChange = onAprChange,
        label = {
            Text(
                text = "APR",
                color = BillTrackerColors.TextColor
            )
        },
        modifier = Modifier.fillMaxWidth(),
        colors = BillTrackerColors.billTrackerOutlinedTextFieldColors()
    )
    Spacer(modifier = Modifier.height(8.dp))
    OutlinedTextField(
        value = data.creditLimit,
        onValueChange = onCreditLimitChange,
        label = {
            Text(
                text = "Credit Limit",
                color = BillTrackerColors.TextColor
            )
        },
        modifier = Modifier.fillMaxWidth(),
        colors = BillTrackerColors.billTrackerOutlinedTextFieldColors()
    )
}

@Composable
private fun LoanBalance(
    onInitialLoanAmountChange: (String) -> Unit,
    onCurrentLoanAmountChange: (String) -> Unit,
    data: AddBillUIState
) {
    OutlinedTextField(
        value = data.initialLoanAmount,
        onValueChange = onInitialLoanAmountChange,
        label = {
            Text(
                text = "Initial Loan Amount",
                color = BillTrackerColors.TextColor
            )
        },
        modifier = Modifier.fillMaxWidth(),
        colors = BillTrackerColors.billTrackerOutlinedTextFieldColors()
    )
    Spacer(modifier = Modifier.height(8.dp))
    OutlinedTextField(
        value = data.currentLoanAmount,
        onValueChange = onCurrentLoanAmountChange,
        label = {
            Text(
                text = "Current Loan Amount",
                color = BillTrackerColors.TextColor
            )
        },
        modifier = Modifier.fillMaxWidth(),
        colors = BillTrackerColors.billTrackerOutlinedTextFieldColors()
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun SubscriptionDetails(
    onSubscriptionFrequencyExpandedChange: (Boolean) -> Unit,
    onSubscriptionFrequencySelected: (String) -> Unit,
    onSubscriptionAmountChange: (String) -> Unit,
    data: AddBillUIState
) {
    val subscriptionFrequencies = BillObject.Subscription.SubscriptionDetails.SubscriptionFrequency.toDropdownChoices()
    ExposedDropdownMenuBox(
        expanded = data.subscriptionFrequencyExpanded,
        onExpandedChange = onSubscriptionFrequencyExpandedChange,
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = data.subscriptionFrequency,
            onValueChange = {},
            label = {
                Text(
                    text = "Subscription Frequency",
                    color = BillTrackerColors.TextColor
                )
            },
            modifier = Modifier.fillMaxWidth(),
            colors = BillTrackerColors.billTrackerOutlinedTextFieldColors()
        )
        ExposedDropdownMenu(
            expanded = data.subscriptionFrequencyExpanded,
            onDismissRequest = { onSubscriptionFrequencyExpandedChange(false) },
            modifier = Modifier.fillMaxWidth()
        ) {
            subscriptionFrequencies.forEach { frequency ->
                DropdownMenuItem(
                    onClick = { onSubscriptionFrequencySelected(frequency) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = frequency,
                        color = BillTrackerColors.TextColor
                    )
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
    OutlinedTextField(
        value = data.subscriptionAmount,
        onValueChange = onSubscriptionAmountChange,
        label = {
            Text(
                text = "Subscription Amount",
                color = BillTrackerColors.TextColor
            )
        },
        modifier = Modifier.fillMaxWidth(),
        colors = BillTrackerColors.billTrackerOutlinedTextFieldColors()
    )
    Spacer(modifier = Modifier.height(8.dp))
}

@ExperimentalMaterialApi
@Preview(name = "Light Mode")
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PreviewAddBillScreen() {
    BillTrackerTheme {
        AddBillScreen(
            onAddBill = { },
            onAprChange = { },
            onBillNameChange = { },
            onBillTypeExpandedChange = { },
            onBillTypeSelected = { },
            onCreditLimitChange = { },
            onAutoPayCheckChanged = { },
            onAutoPayDiscountChanged = { },
            onLinkChange = { },
            onCommentsChange = { },
            onInitialLoanAmountChange = { },
            onCurrentLoanAmountChange = { },
            onSubscriptionFrequencyExpandedChange = { },
            onSubscriptionFrequencySelected = { },
            onSubscriptionAmountChange = { },
            data = AddBillUIState()
        )
    }
}