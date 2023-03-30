package com.wantech.mfarm.onboarding.data.repositoryImpl

import com.wantech.mfarm.core.data.MFarmPreferences
import com.wantech.mfarm.onboarding.domain.model.repository.UserDataRepository
import kotlinx.coroutines.flow.Flow

class UserdataRepositoryImpl(private val mFarmPreferences: MFarmPreferences) :UserDataRepository{
    override val userOnBoarded: Flow<Boolean>
        get() = mFarmPreferences.isOnBoarded

    override suspend fun onBoard(isOnBoarded: Boolean) {
        mFarmPreferences.saveOnBoard(onBoard = isOnBoarded)
    }
}