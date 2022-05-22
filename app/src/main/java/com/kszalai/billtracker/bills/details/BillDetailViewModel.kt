package com.kszalai.billtracker.bills.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kszalai.billtracker.bills.common.repo.BillRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BillDetailViewModel @Inject constructor(
    private val billRepo: BillRepo
) : ViewModel() {
    var uiState = mutableStateOf(BillDetailsUIState(loading = true))

    fun setBill(data: Int?) {
        viewModelScope.launch {
            data?.let {
                billRepo.bills.collectLatest { bills ->
                    bills.find { element -> element.id == data }?.let {
                        uiState.value = BillDetailsUIState(
                            selectedBill = it
                        )
                    }
                }
            }
        }
    }
}
