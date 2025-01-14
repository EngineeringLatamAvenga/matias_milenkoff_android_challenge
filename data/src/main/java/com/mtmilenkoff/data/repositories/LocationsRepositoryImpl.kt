package com.mtmilenkoff.data.repositories

import com.mtmilenkoff.data.api.LocationsApi
import com.mtmilenkoff.data.models.toDomainModel
import com.mtmilenkoff.domain.models.Locations
import com.mtmilenkoff.domain.repositories.LocationsRepository
import com.mtmilenkoff.domain.resource.DataResult
import com.mtmilenkoff.domain.resource.ErrorModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import retrofit2.Response

class LocationsRepositoryImpl(
    private val api: LocationsApi
) : LocationsRepository {
    override fun getLocations(): Flow<DataResult<List<Locations>>> {
        return executeCall(apiCall = api.getLocations()) { result ->
            result.map { it.toDomainModel() }
        }
    }
}

/**
 * Type T = DTOs
 * Type U = Models
 */
inline fun <T : Any, U : Any> executeCall(
    apiCall: Response<T>,
    crossinline mapper: (T) -> U
): Flow<DataResult<U>> {
    return flow {
        when (val dataResult = apiCall.makeCall()) {
            is DataResult.Success -> {
                emit(DataResult.Success(dataResult.data?.let { mapper(it) }))
            }

            is DataResult.Failed -> {
                emit(dataResult)
            }

            is DataResult.Loading -> {
                emit(DataResult.Loading)
            }

        }
    }.onStart { emit(DataResult.Loading) }
}

fun <T : Any> Response<T>.makeCall(): DataResult<T> {
    val body = this.body()
    return if (this.isSuccessful.not() || body == null) {
        DataResult.Failed(ErrorModel(this.code(), this.message()))
    } else {
        DataResult.Success(body)
    }
}
