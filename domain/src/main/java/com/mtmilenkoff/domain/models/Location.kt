package com.mtmilenkoff.domain.models

data class Location(
    val id: Int,
    val country: String,
    val name: String,
    val coord: Coordinates,
    val isFavorite: Boolean
)

data class Coordinates(
    val lon: Double,
    val lat: Double
)
