package com.hubinsord.loginsimulator.core.di

import com.hubinsord.loginsimulator.app.data.datasource.local.AccountLocalDataSource
import com.hubinsord.loginsimulator.app.data.repository.AccountRepositoryImpl
import com.hubinsord.loginsimulator.app.domain.AccountRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideLocalDataSource() = AccountLocalDataSource()

    @Singleton
    @Provides
    fun provideAccountRepositoryImpl(accountLocalDataSource: AccountLocalDataSource): AccountRepository =
        AccountRepositoryImpl(accountLocalDataSource)
}