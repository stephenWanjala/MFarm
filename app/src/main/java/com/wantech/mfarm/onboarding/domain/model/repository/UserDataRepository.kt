package com.wantech.mfarm.onboarding.domain.model.repository

import kotlinx.coroutines.flow.Flow


interface UserDataRepository {
    val userOnBoarded: Flow<Boolean>
    suspend fun onBoard(isOnBoarded: Boolean)
}