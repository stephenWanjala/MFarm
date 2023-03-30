package com.wantech.mfarm.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RegisterRequest(
    val name: String,
    val email: String,
    val phone: String,
    val address: String,
    val sacco: Sacco,
    val password: String
) : Parcelable
