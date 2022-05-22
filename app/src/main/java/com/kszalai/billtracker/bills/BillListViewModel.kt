package com.kszalai.billtracker.bills

import androidx.lifecycle.ViewModel
import com.kszalai.billtracker.bills.common.models.BillObject
import com.kszalai.billtracker.bills.common.repo.BillRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class BillListViewModel @Inject constructor(
    private val billRepo: BillRepo
) : ViewModel() {
    val bills: Flow<List<BillObject>> = billRepo.bills
}
