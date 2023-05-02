package com.wantech.mfarm.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TheMilk(
    val dateCollected: String,
    val farmer: Farmer,
    val id: Int,
    val quantity: Int,
    val status: Status
) : Parcelable