package com.wantech.mfarm.auth.domain.repository

import com.wantech.mfarm.core.util.AuthResult
import com.wantech.mfarm.core.util.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun signInUserWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<Resource<AuthResult>>

    suspend fun createUserWithEmailAndPassword(
        email: String,
        password: String,
        userName: String
    ): Flow<Resource<AuthResult>>

    fun isCurrentUserExist(): Flow<Boolean>

    suspend fun logoutUser()

    suspend fun getUserId(): Flow<String>
}