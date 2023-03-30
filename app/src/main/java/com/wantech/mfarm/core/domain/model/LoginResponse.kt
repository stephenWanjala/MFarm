package com.wantech.mfarm.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginResponse(
    val accessToken:String,
    val refreshToken: String
) : Parcelable

