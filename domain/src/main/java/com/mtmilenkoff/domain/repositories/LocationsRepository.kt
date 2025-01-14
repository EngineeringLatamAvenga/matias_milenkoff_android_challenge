package com.mtmilenkoff.domain.repositories

import com.mtmilenkoff.domain.models.Locations
import com.mtmilenkoff.domain.resource.DataResult
import kotlinx.coroutines.flow.Flow

interface LocationsRepository {
    fun getLocations() : Flow<DataResult<List<Locations>>>
}
