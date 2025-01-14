package com.mtmilenkoff.domain.usecases

import com.mtmilenkoff.domain.models.Locations
import kotlinx.coroutines.flow.Flow

interface GetLocations {
    fun invoke(): Flow<List<Locations>>
}
