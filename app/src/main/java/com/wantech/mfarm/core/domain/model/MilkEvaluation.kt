package com.wantech.mfarm.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MilkEvaluation(
    val butter_fat: Double,
    val id: Int,
    val protein_content: Double,
    val the_milk: TheMilk
) : Parcelable