package com.wantech.mfarm.auth.domain.repository

import android.os.Parcelable
import com.wantech.mfarm.core.domain.model.MilKEvaluation
import com.wantech.mfarm.core.domain.model.Sacco
import com.wantech.mfarm.core.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.parcelize.Parcelize

interface DataRepository {
    suspend fun getSaccoByLocation(location: String): Flow<Resource<Sacco>>

    suspend fun getSaccoById(id: String): Flow<Resource<Sacco>>

    suspend fun farmerMilkEvaluationRecord(farmerId: String): Flow<Resource<List<MilKEvaluation>>>
}

