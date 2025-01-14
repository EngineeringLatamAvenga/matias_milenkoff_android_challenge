package com.mtmilenkoff.data.usecases

import com.mtmilenkoff.domain.repositories.LocationsRepository
import com.mtmilenkoff.domain.usecases.GetFavoriteLocations

class GetFavoriteLocationsUseCase(
    private val repository: LocationsRepository
) : GetFavoriteLocations {
    override operator fun invoke() = repository.getFavoriteLocations()
}
