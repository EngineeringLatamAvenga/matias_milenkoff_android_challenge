package com.mtmilenkoff.domain.usecases

interface RemoveFavorite {
    operator fun invoke(id: Int)
}