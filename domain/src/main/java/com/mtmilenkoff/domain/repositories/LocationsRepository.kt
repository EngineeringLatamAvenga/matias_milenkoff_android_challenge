package com.mtmilenkoff.domain.repositories

import com.mtmilenkoff.domain.models.Location
import com.mtmilenkoff.domain.resource.DataResult
import kotlinx.coroutines.flow.Flow

interface LocationsRepository {
    fun updateLocations() : Flow<DataResult<Unit>>

    fun getLocations(filter: String) : List<Location>

    fun getFavoriteLocations(filter: String) : Flow<List<Location>>

    fun addFavorite(id: Int)

    fun deleteFavorite(id: Int)
}
