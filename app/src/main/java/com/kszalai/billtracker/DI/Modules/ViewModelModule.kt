package com.kszalai.billtracker.DI.Modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kszalai.billtracker.DI.BillTrackerViewModelFactory
import com.kszalai.billtracker.ui.billdetails.BillDetailViewModel
import com.kszalai.billtracker.ui.bills.BillListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(BillListViewModel::class)
    abstract fun bindBillListViewModel(billListViewModel: BillListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BillDetailViewModel::class)
    abstract fun bindBillDetailViewModel(billDetailViewModel: BillDetailViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: BillTrackerViewModelFactory): ViewModelProvider.Factory
}