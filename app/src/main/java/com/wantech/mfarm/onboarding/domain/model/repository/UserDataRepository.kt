package com.wantech.mfarm.onboarding.domain.model.repository

import com.wantech.mfarm.core.domain.model.LoginResponse
import kotlinx.coroutines.flow.Flow


interface UserDataRepository {
    val userOnBoarded: Flow<Boolean>
    suspend fun onBoard(isOnBoarded: Boolean)

    suspend fun saveAuthenticatedStatus(loginResponse: LoginResponse)
    suspend fun getAuthenticatedStatus(): Flow<LoginResponse>

}