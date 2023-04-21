package com.wantech.mfarm.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.google.android.gms.location.LocationServices
import com.wantech.mfarm.auth.data.network.AuthApi
import com.wantech.mfarm.auth.data.repositoryImpl.AuthRepositoryImpl
import com.wantech.mfarm.auth.data.repositoryImpl.LocationAndSaccoDetailsRepositoryImp
import com.wantech.mfarm.auth.domain.repository.AuthRepository
import com.wantech.mfarm.auth.domain.repository.LocationAndSaccoDetails
import com.wantech.mfarm.core.data.MFarmPreferences
import com.wantech.mfarm.core.data.MFarmPreferences.Companion.MFARMPREFERENCES
import com.wantech.mfarm.onboarding.data.repositoryImpl.UserdataRepositoryImpl
import com.wantech.mfarm.onboarding.domain.model.repository.UserDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAuthRepository(api: AuthApi): AuthRepository = AuthRepositoryImpl(api = api)

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


    @Provides
    @Singleton
    fun provideFusedNetworkProvider(@ApplicationContext context: Context) =
        LocationServices.getFusedLocationProviderClient(context)


    private const val BaseUrl = "http://172.16.13.115:8080/"

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BaseUrl)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)


    @Singleton
    @Provides
    fun provideLocationAndSaccoRepository(api: AuthApi): LocationAndSaccoDetails =
        LocationAndSaccoDetailsRepositoryImp(api = api)


}