package com.kszalai.billtracker.DI.Modules

import com.kszalai.billtracker.repo.BillRepo
import com.kszalai.billtracker.services.BillService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ ViewModelModule::class ])
class AppModule {

    @Provides
    @Singleton
    fun provideBillRepo(): BillRepo {
        return BillRepo(BillService())
    }

}