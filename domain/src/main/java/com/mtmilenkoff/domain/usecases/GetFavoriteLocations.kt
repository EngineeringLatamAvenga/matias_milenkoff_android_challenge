package com.mtmilenkoff.domain.usecases

import com.mtmilenkoff.domain.models.Location
import kotlinx.coroutines.flow.Flow

interface GetFavoriteLocations {
    operator fun invoke(filter: String): Flow<List<Location>>
}
