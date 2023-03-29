package com.wantech.mfarm.di

import com.wantech.mfarm.auth.data.repositoryImpl.AuthRepositoryImpl
import com.wantech.mfarm.auth.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAuthRepository():AuthRepository =AuthRepositoryImpl()
}