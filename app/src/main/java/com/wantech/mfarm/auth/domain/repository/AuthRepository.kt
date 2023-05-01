package com.wantech.mfarm.auth.domain.repository

import com.wantech.mfarm.core.domain.model.LoginRequest
import com.wantech.mfarm.core.domain.model.LoginResponse
import com.wantech.mfarm.core.domain.model.RegisterRequest
import com.wantech.mfarm.core.domain.model.RegisterResponse
import com.wantech.mfarm.core.util.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun signInUserWithEmailAndPassword(
        loginRequest: LoginRequest
    ): Flow<Resource<LoginResponse>>

    suspend fun createUserWithEmailAndPassword(
        registerRequest: RegisterRequest
    ): Flow<Resource<RegisterResponse>>

    fun isCurrentUserExist(): Flow<Boolean>

    suspend fun logoutUser()

    suspend fun getUserId(): Flow<String>

    suspend fun getEvalutaions(authToken:String):Flow<Resource<List<MilKEvaluation>>>


}