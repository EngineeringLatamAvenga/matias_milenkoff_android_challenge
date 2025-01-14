package com.mtmilenkoff.domain.usecases

import androidx.paging.PagingData
import com.mtmilenkoff.domain.models.Location
import kotlinx.coroutines.flow.Flow

interface GetFavoriteLocations {
    operator fun invoke(filter: String): Flow<PagingData<Location>>
}
