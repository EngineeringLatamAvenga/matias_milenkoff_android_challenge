package com.mtmilenkoff.data.usecases

import com.mtmilenkoff.domain.repositories.LocationsRepository
import com.mtmilenkoff.domain.usecases.UpdateLocations

class UpdateLocationsUseCase(private val repository: LocationsRepository): UpdateLocations {
    override fun invoke() = repository.updateLocations()
}
