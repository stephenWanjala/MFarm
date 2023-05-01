package com.wantech.mfarm.auth.data.network

import com.wantech.mfarm.core.domain.model.*
import com.wantech.mfarm.core.util.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthApi {
    @POST("api/login/")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): LoginResponse

    @POST("api/register")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ): RegisterResponse

    @GET("api/login/refresh")
    suspend fun refreshToken(@Body refreshToken: String): Resource<Flow<LoginResponse>>

    @GET("api/logout")
    suspend fun logout(@Path("refresh_token")refreshToken: String): LoginResponse

    @GET("api/saccos/{location}")
    suspend fun getSaccoSInLocation(@Path("location") location: String): List<Sacco>


    @GET("api/milk_evaluations")
    suspend fun getMilkEvaluations(
        @Header("Authorization") authToken: String
    ): List<MilkEvaluation>



}