package com.yuvasai.baseproject.di.modules

import com.yuvasai.baseproject.repository.ILocalDataRepository
import com.yuvasai.baseproject.repository.IRepository
import com.yuvasai.baseproject.repository.LocalDataRepository
import com.yuvasai.baseproject.repository.Repository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindRepository(repository: Repository): IRepository

    @Singleton
    @Binds
    abstract fun bindLocalDataRepository(localDataRepository: LocalDataRepository): ILocalDataRepository
}