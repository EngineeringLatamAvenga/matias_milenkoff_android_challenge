package com.mtmilenkoff.domain.usecases

import com.mtmilenkoff.domain.models.Location
import kotlinx.coroutines.flow.Flow

interface GetLocations {
    operator fun invoke(filter: String): List<Location>
}
