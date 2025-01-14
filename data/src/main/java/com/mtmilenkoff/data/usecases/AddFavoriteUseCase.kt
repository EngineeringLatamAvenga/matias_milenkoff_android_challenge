package com.mtmilenkoff.data.usecases

import com.mtmilenkoff.domain.repositories.LocationsRepository
import com.mtmilenkoff.domain.usecases.AddFavorite

class AddFavoriteUseCase(private val repo: LocationsRepository) : AddFavorite {
    override operator fun invoke(id: Int) {
        repo.addFavorite(id)
    }
}
