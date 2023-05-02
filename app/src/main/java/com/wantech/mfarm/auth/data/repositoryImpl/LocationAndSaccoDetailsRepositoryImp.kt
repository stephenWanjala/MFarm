package com.wantech.mfarm.auth.data.repositoryImpl

import com.wantech.mfarm.auth.data.network.AuthApi
import com.wantech.mfarm.auth.domain.repository.LocationAndSaccoDetails
import com.wantech.mfarm.core.domain.model.Sacco
import com.wantech.mfarm.core.util.Resource
import com.wantech.mfarm.core.util.UiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocationAndSaccoDetailsRepositoryImp @Inject constructor(
    private val api: AuthApi
) : LocationAndSaccoDetails {
    override suspend fun getSaccoByLocation(location: String): Flow<Resource<List<Sacco>>> {
        return flow {
            try {
                emit(Resource.Loading())
                val result = api.getSaccoSInLocation(location)
                emit(Resource.Success(data = result))
                println("The Result is  $result")
            } catch (e: Exception) {
                emit(
                    Resource.Error(
                        uiText = UiText.DynamicString(
                            value = e.message ?: "Unknown Error Happened"
                        )
                    )
                )
            }
        }.catch { throwable ->
            emit(
                Resource.Error(
                    uiText = UiText.DynamicString(
                        throwable.message ?: "Unknown Error Happened"
                    )
                )
            )

        }
    }

    override suspend fun getSaccoById(id: String): Flow<Resource<Sacco>> {
        TODO("Not yet implemented")
    }

}