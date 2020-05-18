package com.kszalai.billtracker.DI.Components

import android.app.Application
import com.kszalai.billtracker.BillTrackerApplication
import com.kszalai.billtracker.DI.Modules.AppModule
import com.kszalai.billtracker.DI.Modules.MainActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ AndroidInjectionModule::class, MainActivityModule::class, AppModule::class ])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: BillTrackerApplication)
}