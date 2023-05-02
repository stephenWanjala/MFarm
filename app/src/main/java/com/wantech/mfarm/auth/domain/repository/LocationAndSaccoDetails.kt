package com.wantech.mfarm.auth.domain.repository

import com.wantech.mfarm.core.domain.model.Sacco
import com.wantech.mfarm.core.util.Resource
import kotlinx.coroutines.flow.Flow

interface LocationAndSaccoDetails {
    suspend fun getSaccoByLocation(location: String):Flow<Resource<List<Sacco>>>

    suspend fun getSaccoById(id: String):Flow<Resource<Sacco>>

}