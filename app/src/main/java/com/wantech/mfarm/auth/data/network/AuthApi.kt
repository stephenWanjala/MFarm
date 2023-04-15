package com.wantech.mfarm.auth.data.network

import com.wantech.mfarm.core.domain.model.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {
    @POST("api/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): LoginResponse

    @POST("api/register")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ): RegisterResponse

    @GET("api/refresh")
    suspend fun refreshToken(@Body refreshToken: String): LoginResponse

    @GET("api/logout")
    suspend fun logout(@Body refreshToken: String): LoginResponse

    @GET("api/sacco")
    suspend fun getSaccoByLocation(@Body location: String): Sacco


}