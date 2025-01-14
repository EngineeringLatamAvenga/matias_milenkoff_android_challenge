package com.mtmilenkoff.domain.repositories

import com.mtmilenkoff.domain.models.Locations
import com.mtmilenkoff.domain.resource.DataResult
import kotlinx.coroutines.flow.Flow

interface LocationsRepository {
    fun updateLocations() : Flow<DataResult<Unit>>

    fun getLocations() : Flow<List<Locations>>

    fun getFavoriteLocations() : Flow<List<Locations>>
}
