package com.yuvasai.baseproject.di.modules

import android.content.Context
import com.yuvasai.baseproject.base.ApplicationClass
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: ApplicationClass) {

    @Singleton
    @Provides
    fun provideApp(): ApplicationClass = context

    @Singleton
    @Provides
    fun provideContext(): Context = context

}