package com.yuvasai.baseproject.di.modules.builders

import com.yuvasai.baseproject.ui.MainActivity
import com.yuvasai.baseproject.ui.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun bindMainActivity(): MainActivity
}