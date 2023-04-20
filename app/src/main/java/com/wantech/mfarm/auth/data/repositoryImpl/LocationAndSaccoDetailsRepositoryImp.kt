package com.wantech.mfarm.auth.data.repositoryImpl

import com.wantech.mfarm.auth.data.network.AuthApi
import com.wantech.mfarm.auth.domain.repository.LocationAndSaccoDetails
import com.wantech.mfarm.core.domain.model.Sacco
import com.wantech.mfarm.core.util.Resource
import com.wantech.mfarm.core.util.UiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocationAndSaccoDetailsRepositoryImp @Inject constructor(
    private val api: AuthApi
) : LocationAndSaccoDetails {
    override suspend fun getSaccoByLocation(location: String): Flow<Resource<List<Sacco>>> {
        return flow {
            try {
                when (val resultResource = api.getSaccoSInLocation(location = location)) {
                    is Resource.Error -> emit(
                        Resource.Error(
                            uiText = resultResource.uiText ?: UiText.unknownError()
                        )
                    )

                    is Resource.Loading -> emit(Resource.Loading())
                    is Resource.Success -> {
                        resultResource.data?.let { resultFlow ->
                            resultFlow.collectLatest { list ->
                                emit(Resource.Success(data = list))
                            }

                        }
                    }
                }

            } catch (e: Exception) {
                emit(
                    Resource.Error(
                        uiText = UiText.DynamicString(
                            e.message ?: "Unknown Error Happened"
                        )
                    )
                )
            }
        }
    }

    override suspend fun getSaccoById(id: String): Flow<Resource<Sacco>> {
        TODO("Not yet implemented")
    }

}