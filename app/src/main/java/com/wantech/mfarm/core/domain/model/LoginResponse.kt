package com.wantech.mfarm.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginResponse(
    val access:String,
    val refresh: String
) : Parcelable

