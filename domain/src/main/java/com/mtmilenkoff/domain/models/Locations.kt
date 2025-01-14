package com.mtmilenkoff.domain.models

data class Locations(
    val id: Int,
    val country: String,
    val name: String,
    val coord: Coordinates
)

data class Coordinates(
    val lon: Double,
    val lat: Double
)
