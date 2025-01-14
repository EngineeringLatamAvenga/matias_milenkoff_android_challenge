package com.mtmilenkoff.domain.models

data class Location(
    val id: Int,
    val country: String,
    val name: String,
    val coord: Coordinates
)

data class Coordinates(
    val lon: Double,
    val lat: Double
)