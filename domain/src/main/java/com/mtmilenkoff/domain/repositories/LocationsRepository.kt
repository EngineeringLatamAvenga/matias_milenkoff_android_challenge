package com.mtmilenkoff.domain.repositories

import androidx.paging.PagingData
import com.mtmilenkoff.domain.models.Location
import com.mtmilenkoff.domain.resource.DataResult
import kotlinx.coroutines.flow.Flow

interface LocationsRepository {
    fun updateLocations() : Flow<DataResult<Unit>>

    fun getLocations(filter: String) : Flow<PagingData<Location>>

    fun getFavoriteLocations(filter: String) : Flow<PagingData<Location>>

    fun changeFavorite(id: Int)
}
