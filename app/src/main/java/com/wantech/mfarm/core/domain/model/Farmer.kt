package com.wantech.mfarm.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Farmer(
    val address: String,
    val email: String,
    val id: String,
    val name: String,
    val phone: String,
    val sacco: Int,
) : Parcelable