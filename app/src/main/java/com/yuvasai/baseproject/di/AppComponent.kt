package com.yuvasai.baseproject.di

import android.app.Application
import com.yuvasai.baseproject.base.ApplicationClass
import com.yuvasai.baseproject.di.modules.ApiModule
import com.yuvasai.baseproject.di.modules.AppModule
import com.yuvasai.baseproject.di.modules.RepositoryModule
import com.yuvasai.baseproject.di.modules.UiCommonModule
import com.yuvasai.baseproject.di.modules.builders.ActivityBuilder
import com.yuvasai.baseproject.di.modules.builders.FragmentBuilder
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuilder::class,
        FragmentBuilder::class,
        AppModule::class,
        ApiModule::class,
        RepositoryModule::class,
        UiCommonModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        fun build(): AppComponent
        fun appModule(module: AppModule): Builder

        @BindsInstance
        fun application(application: Application): Builder
    }


    fun inject(app: ApplicationClass)
}