package com.mtmilenkoff.data.usecases

import com.mtmilenkoff.domain.repositories.LocationsRepository
import com.mtmilenkoff.domain.usecases.ChangeFavorite

class ChangeFavoriteUseCase(private val repo: LocationsRepository) : ChangeFavorite {
    override operator fun invoke(id: Int) {
        repo.changeFavorite(id)
    }
}
