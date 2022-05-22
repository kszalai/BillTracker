package com.kszalai.billtracker.bills.add

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kszalai.billtracker.bills.common.models.BillObject
import com.kszalai.billtracker.bills.common.repo.BillRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddBillViewModel @Inject constructor(
    val billRepo: BillRepo
) : ViewModel() {

    var uiState = mutableStateOf(AddBillUIState())
        private set

    fun onBillNameChange(billName: String) {
        uiState.value = uiState.value.copy(
            billName = billName
        )
    }

    fun onAprChange(apr: String) {
        uiState.value = uiState.value.copy(
            apr = apr
        )
    }

    fun onBillTypeExpandedChange(expanded: Boolean) {
        uiState.value = uiState.value.copy(
            billTypeExpanded = expanded
        )
    }

    fun onBillTypeSelected(selectedBillType: String) {
        uiState.value = uiState.value.copy(
            selectedBillType = selectedBillType,
            billTypeExpanded = false
        )
    }

    fun onCreditLimitChange(creditLimit: String) {
        uiState.value = uiState.value.copy(
            creditLimit = creditLimit
        )
    }

    fun onAddBill() {
        viewModelScope.launch {
            billRepo.addBill(
                BillObject.Builder()
                    .fromUIState(uiState.value)
                    .build()
            )
        }
    }
}