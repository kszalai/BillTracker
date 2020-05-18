package com.kszalai.billtracker.DI.Modules

import com.kszalai.billtracker.ui.billdetails.BillDetailFragment
import com.kszalai.billtracker.ui.bills.BillListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeBillListFragment(): BillListFragment

    @ContributesAndroidInjector
    abstract fun contributeBillDetailFragment(): BillDetailFragment
}