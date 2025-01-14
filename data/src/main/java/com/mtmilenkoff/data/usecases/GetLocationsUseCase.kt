package com.mtmilenkoff.data.usecases

import com.mtmilenkoff.domain.repositories.LocationsRepository
import com.mtmilenkoff.domain.usecases.GetLocations

class GetLocationsUseCase(private val repository: LocationsRepository): GetLocations {
    override operator fun invoke() = repository.getLocations()
}
