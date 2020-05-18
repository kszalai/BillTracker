package com.kszalai.billtracker.DI.Modules

import com.kszalai.billtracker.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Scope

@Retention(AnnotationRetention.RUNTIME)
@Scope
annotation class ActivityScope {}

@Suppress("unused")
@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    @ActivityScope
    abstract fun contributeMainActivity(): MainActivity
}