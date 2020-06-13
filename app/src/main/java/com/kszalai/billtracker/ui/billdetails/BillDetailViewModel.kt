package com.kszalai.billtracker.ui.billdetails

import androidx.lifecycle.ViewModel
import com.kszalai.billtracker.repo.BillRepo
import javax.inject.Inject

class BillDetailViewModel @Inject constructor(val billRepo: BillRepo) : ViewModel() {

}
