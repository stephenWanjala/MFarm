package com.wantech.mfarm.auth.data.repositoryImpl

import com.wantech.mfarm.auth.domain.repository.AuthRepository
import com.wantech.mfarm.core.domain.model.LoginRequest
import com.wantech.mfarm.core.domain.model.LoginResponse
import com.wantech.mfarm.core.domain.model.RegisterRequest
import com.wantech.mfarm.core.domain.model.RegisterResponse
import com.wantech.mfarm.core.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor() : AuthRepository {
    override suspend fun signInUserWithEmailAndPassword(loginRequest: LoginRequest): Flow<Resource<LoginResponse>> {
        TODO("Not yet implemented")
    }

    override suspend fun createUserWithEmailAndPassword(registerRequest: RegisterRequest): Flow<Resource<RegisterResponse>> {
        TODO("Not yet implemented")
    }

    override fun isCurrentUserExist(): Flow<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun logoutUser() {
        TODO("Not yet implemented")
    }

    override suspend fun getUserId(): Flow<String> {
        TODO("Not yet implemented")
    }

}