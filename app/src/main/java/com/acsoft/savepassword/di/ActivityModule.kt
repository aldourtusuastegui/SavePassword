package com.acsoft.savepassword.di

import com.acsoft.savepassword.data.local.LocalAccountDataSource
import com.acsoft.savepassword.repository.AccountRepository
import com.acsoft.savepassword.repository.AccountRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityModule {

    @Binds
    abstract fun accountRepositoryImpl(accountRepositoryImpl : AccountRepositoryImpl) : AccountRepository

/*
    @Binds
    abstract fun accountLocalDataSourceImpl(accountLocalAccountDataSource: LocalAccountDataSource) : LocalAccountDataSource
*/

}