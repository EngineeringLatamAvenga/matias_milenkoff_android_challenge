package com.mtmilenkoff.domain.usecases

import com.mtmilenkoff.domain.resource.DataResult
import kotlinx.coroutines.flow.Flow

interface UpdateLocations {
    operator fun invoke(): Flow<DataResult<Unit>>
}
