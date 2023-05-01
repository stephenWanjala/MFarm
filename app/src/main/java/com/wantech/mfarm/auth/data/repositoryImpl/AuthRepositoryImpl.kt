package com.wantech.mfarm.auth.data.repositoryImpl

import com.wantech.mfarm.auth.data.network.AuthApi
import com.wantech.mfarm.auth.domain.repository.AuthRepository
import com.wantech.mfarm.auth.domain.repository.MilKEvaluation
import com.wantech.mfarm.core.domain.model.LoginRequest
import com.wantech.mfarm.core.domain.model.LoginResponse
import com.wantech.mfarm.core.domain.model.RegisterRequest
import com.wantech.mfarm.core.domain.model.RegisterResponse
import com.wantech.mfarm.core.util.Resource
import com.wantech.mfarm.core.util.UiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi
) : AuthRepository {
    override suspend fun signInUserWithEmailAndPassword(loginRequest: LoginRequest): Flow<Resource<LoginResponse>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = api.login(loginRequest)
                emit(Resource.Success(data = response))
            } catch (e: Exception) {
                emit(
                    Resource.Error(
                        uiText = UiText.DynamicString(
                            e.message ?: "An unexpected error occurred"
                        )
                    )
                )
            }
        }.catch { error ->
            emit(
                Resource.Error(
                    uiText = UiText.DynamicString(
                        error.message ?: "Unknown Error Happened"
                    )
                )
            )
        }
    }

    override suspend fun createUserWithEmailAndPassword(registerRequest: RegisterRequest): Flow<Resource<RegisterResponse>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = api.register(registerRequest)
                emit(Resource.Success(data = response))
            } catch (e: Exception) {
                emit(
                    Resource.Error(
                        uiText = UiText.DynamicString(
                            e.message ?: "Unknown Error Happened"
                        )
                    )
                )
            }
        }.catch { error ->
            emit(
                Resource.Error(
                    uiText = UiText.DynamicString(
                        error.message ?: "Unknown Error Happened"
                    )
                )
            )
        }
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

    override suspend fun getEvalutaions(authToken: String): Flow<Resource<List<MilKEvaluation>>> {
        TODO("Not yet implemented")
    }

}