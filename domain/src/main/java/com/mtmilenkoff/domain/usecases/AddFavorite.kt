package com.mtmilenkoff.domain.usecases

interface AddFavorite {
    operator fun invoke(id: Int)
}