package com.mtmilenkoff.domain.repositories

import com.mtmilenkoff.domain.models.Location
import com.mtmilenkoff.domain.resource.DataResult
import kotlinx.coroutines.flow.Flow

interface LocationsRepository {
    fun updateLocations() : Flow<DataResult<Unit>>

    fun getLocations() : Flow<List<Location>>

    fun getFavoriteLocations() : Flow<List<Location>>

    fun addFavorite(id: Int)

    fun deleteFavorite(id: Int)
}
