package com.kszalai.billtracker.ui.bills

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kszalai.billtracker.models.BillObject
import com.kszalai.billtracker.models.BillType
import com.kszalai.billtracker.repo.BillRepo
import javax.inject.Inject
import kotlin.collections.ArrayList

class BillListViewModel @Inject constructor(val billRepo: BillRepo) : ViewModel() {
    val bills: LiveData<ArrayList<Any>> = billRepo.bills

}
