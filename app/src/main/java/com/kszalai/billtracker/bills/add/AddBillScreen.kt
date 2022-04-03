package com.kszalai.billtracker.bills.add

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kszalai.billtracker.common.theme.BillTrackerColors
import com.kszalai.billtracker.common.theme.BillTrackerTheme
import com.kszalai.billtracker.databinding.AddBillFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddBillFragment : Fragment() {
    private val viewModel: AddBillViewModel by viewModels()
    lateinit var binding: AddBillFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AddBillFragmentBinding.inflate(inflater)
        return binding.root
    }

    @OptIn(ExperimentalMaterialApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.root.setContent {
            AddBillScreen(viewModel)
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun AddBillScreen(viewModel: AddBillViewModel) {
    val uiState by viewModel.uiState
    AddBillScreen(
        onBillNameChange = viewModel::onBillNameChange,
        onAprChange = viewModel::onAprChange,
        onBillTypeExpandedChange = viewModel::onBillTypeExpandedChange,
        onBillTypeSelected = viewModel::onBillTypeSelected,
        onCreditLimitChange = viewModel::onCreditLimitChange,
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
    onAddBill: () -> Unit,
    data: AddBillUIState
) {
    val billTypes = listOf("Credit Card", "Student Loan", "Car Loan", "Mortgage")

    BillTrackerTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    vertical = 20.dp,
                    horizontal = 16.dp
                )
        ) {
            Column {
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
            Button(
                onClick = onAddBill,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {
                Row {
                    Text(
                        text = "Add Bill",
                        color = BillTrackerColors.TextColor
                    )
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Preview(name = "Light Mode")
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PreviewAddBillScreen() {
    AddBillScreen(
        onAddBill = { },
        onAprChange = { },
        onBillNameChange = { },
        onBillTypeExpandedChange = { },
        onBillTypeSelected = { },
        onCreditLimitChange = { },
        data = AddBillUIState()
    )
}