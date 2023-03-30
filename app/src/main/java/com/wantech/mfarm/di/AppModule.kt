package com.wantech.mfarm.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.wantech.mfarm.auth.data.repositoryImpl.AuthRepositoryImpl
import com.wantech.mfarm.auth.domain.repository.AuthRepository
import com.wantech.mfarm.core.data.MFarmPreferences
import com.wantech.mfarm.onboarding.data.repositoryImpl.UserdataRepositoryImpl
import com.wantech.mfarm.onboarding.domain.model.repository.UserDataRepository
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
    fun provideAuthRepository(): AuthRepository = AuthRepositoryImpl()

    @Provides
    @Singleton
    fun provideMFarmPreferences(dataStore: DataStore<Preferences>): MFarmPreferences =
        MFarmPreferences(dataStore)

    @Provides
    @Singleton
    fun provideUserDataRepository(mFarmPreferences: MFarmPreferences): UserDataRepository =
        UserdataRepositoryImpl(mFarmPreferences = mFarmPreferences)
}