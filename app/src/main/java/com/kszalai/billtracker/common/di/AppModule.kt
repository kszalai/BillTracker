package com.kszalai.billtracker.common.di

import com.kszalai.billtracker.bills.common.repo.BillRepo
import com.kszalai.billtracker.bills.common.dataSource.BillDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module()
class AppModule {

    @Provides
    @Singleton
    fun provideBillRepo(): BillRepo {
        return BillRepo(BillDataSource())
    }

}