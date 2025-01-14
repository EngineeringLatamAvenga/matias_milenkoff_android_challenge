package com.mtmilenkoff.data.repositories

import com.mtmilenkoff.data.api.LocationsApi
import com.mtmilenkoff.data.entities.FavoriteLocationEntity
import com.mtmilenkoff.data.entities.mapToDomainModel
import com.mtmilenkoff.data.models.toEntity
import com.mtmilenkoff.data.persistence.LocationsDao
import com.mtmilenkoff.domain.models.Location
import com.mtmilenkoff.domain.repositories.LocationsRepository
import com.mtmilenkoff.domain.resource.DataResult
import com.mtmilenkoff.domain.resource.DataResult.Failed
import com.mtmilenkoff.domain.resource.DataResult.Loading
import com.mtmilenkoff.domain.resource.DataResult.Success
import com.mtmilenkoff.domain.resource.ErrorModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.Response

class LocationsRepositoryImpl (
    private val api: LocationsApi,
    private val locationsDao: LocationsDao
) : LocationsRepository {

    override fun updateLocations(): Flow<DataResult<Unit>> = flow {
        emit(Loading)
        when (val result = api.getLocations().makeCall()) {
            is Success -> {
                result.data?.let { data ->
                    locationsDao.deleteAndInsert(data.map { it.toEntity() }.sortedBy { it.name })
                } ?: run { emit(Failed(ErrorModel(404, "No data"))) }
                emit(Success(Unit))
            }

            is Failed -> {
                emit(result)
            }

            is Loading -> {
                emit(result)
            }
        }
    }

    override fun getLocations(filter: String): List<Location> =
        locationsDao.observeLocations(filter).map { it.mapToDomainModel() }

    override fun getFavoriteLocations(filter: String): Flow<List<Location>> =
        locationsDao.observeFavoriteLocations(filter).map { locations ->
            locations.map { it.mapToDomainModel() }
        }

    override fun addFavorite(id: Int) {
        locationsDao.addFavorite(
            FavoriteLocationEntity(id)
        )
    }

    override fun deleteFavorite(id: Int) {
        locationsDao.deleteFavorite(id)
    }
}

fun <T : Any> Response<T>.makeCall(): DataResult<T> {
    val body = this.body()
    return if (this.isSuccessful.not() || body == null) {
        Failed(ErrorModel(this.code(), this.message()))
    } else {
        Success(body)
    }
}
