package com.hubinsord.loginsimulator.app.di

import com.hubinsord.loginsimulator.core.data.repository.AccountRepositoryImpl
import com.hubinsord.loginsimulator.core.domain.AccountRepository
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
    fun provideAccountRepositoryImpl(): AccountRepository = AccountRepositoryImpl()

}