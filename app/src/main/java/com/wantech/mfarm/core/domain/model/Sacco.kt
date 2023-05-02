package com.wantech.mfarm.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Sacco(
    val name: String,
    val phone: String?,
    val email: String,
    val location: String
) : Parcelable
