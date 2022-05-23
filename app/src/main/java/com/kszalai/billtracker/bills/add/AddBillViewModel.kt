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

    fun onAutoPayCheckChanged(autoPay: Boolean) {
        uiState.value = uiState.value.copy(
            autoPay = autoPay
        )
    }

    fun onAutoPayDiscountChanged(discount: String) {
        uiState.value = uiState.value.copy(
            autoPayDiscount = discount
        )
    }

    fun onLinkChanged(link: String) {
        uiState.value = uiState.value.copy(
            link = link
        )
    }

    fun onCommentChanged(comment: String) {
        uiState.value = uiState.value.copy(
            comment = comment
        )
    }

    fun onInitialLoanAmountChanged(amount: String) {
        uiState.value = uiState.value.copy(
            initialLoanAmount = amount
        )
    }

    fun onCurrentLoanAmountChanged(amount: String) {
        uiState.value = uiState.value.copy(
            currentLoanAmount = amount
        )
    }

    fun onSubscriptionFrequencyExpandedChange(expanded: Boolean) {
        uiState.value = uiState.value.copy(
            subscriptionFrequencyExpanded = expanded
        )
    }

    fun onSubscriptionFrequencySelected(selectedFrequencyType: String) {
        uiState.value = uiState.value.copy(
            subscriptionFrequency = selectedFrequencyType,
            subscriptionFrequencyExpanded = false
        )
    }

    fun onSubscriptionAmountChange(amount: String) {
        uiState.value = uiState.value.copy(
            subscriptionAmount = amount
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