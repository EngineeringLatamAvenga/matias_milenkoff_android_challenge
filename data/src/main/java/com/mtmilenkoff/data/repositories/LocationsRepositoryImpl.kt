package com.mtmilenkoff.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.mtmilenkoff.data.api.LocationsApi
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
        try {
            when (val result = api.getLocations().makeCall()) {
                is Success -> {
                    result.data?.let { data ->
                        locationsDao.deleteAndInsert(
                            data.map { it.toEntity() }
                        )
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
        } catch (e: Exception) {
            emit(Failed(ErrorModel(500, e.message ?: "Unknown error")))
        }
    }

    override fun getLocations(filter: String): Flow<PagingData<Location>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { locationsDao.getLocations(filter)}
        ).flow.map { data -> data.map { it.location.mapToDomainModel(it.favorite != null) } }
    }


    override fun getFavoriteLocations(filter: String): Flow<PagingData<Location>> = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE,
            enablePlaceholders = true
        ),
        pagingSourceFactory = { locationsDao.getPaginatedFavorites(filter)}
    ).flow.map { data -> data.map { it.mapToDomainModel(true) } }

    override fun changeFavorite(id: Int) {
        locationsDao.changeFavorite(id)
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

const val PAGE_SIZE = 25
