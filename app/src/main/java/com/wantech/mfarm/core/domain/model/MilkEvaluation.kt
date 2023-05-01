package com.wantech.mfarm.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class MilkEvaluation(
    val id: Int,
    val date: LocalDate,
    val fatContent: Double,
    val proteinContent: Double,
    val lactoseContent: Double,
    val solidsNotFat: Double,
    val totalSolids: Double,
    val somaticCellCount: Int,
    val temperature: Double,
    val comments: String
) : Parcelable
