package com.kszalai.billtracker.bills

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kszalai.billtracker.models.BillObject
import com.kszalai.billtracker.bills.common.repo.BillRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BillListViewModel @Inject constructor(
    val billRepo: BillRepo
) : ViewModel() {
    val bills: LiveData<List<BillObject>> = billRepo.bills
}
