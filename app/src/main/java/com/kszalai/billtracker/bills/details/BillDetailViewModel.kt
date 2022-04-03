package com.kszalai.billtracker.bills.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.kszalai.billtracker.bills.common.repo.BillRepo
import com.kszalai.billtracker.bills.common.models.BillObject
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BillDetailViewModel @Inject constructor(val billRepo: BillRepo) : ViewModel() {
    var uiState = mutableStateOf(BillDetailsUIState(loading = true))

    fun setBill(data: BillObject) {
        uiState.value = BillDetailsUIState(
            selectedBill = data
        )
    }
}
