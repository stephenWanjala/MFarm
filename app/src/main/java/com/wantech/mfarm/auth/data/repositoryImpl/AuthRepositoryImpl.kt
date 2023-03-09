package com.wantech.mfarm.auth.data.repositoryImpl

import com.wantech.mfarm.auth.domain.repository.AuthRepository
import com.wantech.mfarm.core.util.AuthResult
import com.wantech.mfarm.core.util.Resource
import kotlinx.coroutines.flow.Flow

class AuthRepositoryImpl : AuthRepository {
    override suspend fun signInUserWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<Resource<AuthResult>> {
        TODO("Not yet implemented")
    }

    override suspend fun createUserWithEmailAndPassword(
        email: String,
        password: String,
        userName: String
    ): Flow<Resource<AuthResult>> {
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