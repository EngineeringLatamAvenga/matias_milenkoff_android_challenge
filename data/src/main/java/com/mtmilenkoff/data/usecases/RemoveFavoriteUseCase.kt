package com.mtmilenkoff.data.usecases

import com.mtmilenkoff.domain.repositories.LocationsRepository
import com.mtmilenkoff.domain.usecases.RemoveFavorite

class RemoveFavoriteUseCase(private val repo: LocationsRepository) : RemoveFavorite {
    override fun invoke(id: Int) {
        repo.deleteFavorite(id)
    }
}