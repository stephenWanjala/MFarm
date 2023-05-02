package com.wantech.mfarm.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MilKEvaluation(
    val butter_fat: Double,
    val id: Int,
    val protein_content: Double,
    val the_milk: Int
) : Parcelable