package com.kszalai.billtracker.di

import com.kszalai.billtracker.repo.BillRepo
import com.kszalai.billtracker.services.BillService
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
        return BillRepo(BillService())
    }

}