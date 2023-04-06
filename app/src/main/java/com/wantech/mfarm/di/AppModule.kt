package com.wantech.mfarm.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.wantech.mfarm.auth.data.repositoryImpl.AuthRepositoryImpl
import com.wantech.mfarm.auth.domain.repository.AuthRepository
import com.wantech.mfarm.core.data.MFarmPreferences
import com.wantech.mfarm.core.data.MFarmPreferences.Companion.MFARMPREFERENCES
import com.wantech.mfarm.onboarding.data.repositoryImpl.UserdataRepositoryImpl
import com.wantech.mfarm.onboarding.domain.model.repository.UserDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideDatastore(@ApplicationContext context: Context): DataStore<Preferences> =
        PreferenceDataStoreFactory.create(
            produceFile = {
                context.preferencesDataStoreFile(MFARMPREFERENCES)
            }
        )

    @Provides
    @Singleton
    fun provideMFarmPreferences(dataStore: DataStore<Preferences>): MFarmPreferences =
        MFarmPreferences(dataStore)

    @Provides
    @Singleton
    fun provideUserDataRepository(mFarmPreferences: MFarmPreferences): UserDataRepository =
        UserdataRepositoryImpl(mFarmPreferences = mFarmPreferences)
}