package com.wantech.mfarm.onboarding.data.repositoryImpl

import com.wantech.mfarm.core.data.MFarmPreferences
import com.wantech.mfarm.core.domain.model.LoginResponse
import com.wantech.mfarm.onboarding.domain.model.repository.UserDataRepository
import kotlinx.coroutines.flow.Flow

class UserdataRepositoryImpl(private val mFarmPreferences: MFarmPreferences) :UserDataRepository{
    override val userOnBoarded: Flow<Boolean>
        get() = mFarmPreferences.isOnBoarded

    override suspend fun onBoard(isOnBoarded: Boolean) {
        mFarmPreferences.saveOnBoard(onBoard = isOnBoarded)
    }

    override suspend fun saveAuthenticatedStatus(loginResponse: LoginResponse) {
        mFarmPreferences.saveAuthenticatedStatus(loginResponse = loginResponse)
    }

    override suspend fun getAuthenticatedStatus(): Flow<LoginResponse?> {
        TODO("Not yet implemented")
    }
}