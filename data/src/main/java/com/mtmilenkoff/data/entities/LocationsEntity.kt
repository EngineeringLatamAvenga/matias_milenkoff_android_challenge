package com.mtmilenkoff.data.entities

data class LocationsEntity(
    val id: Int,
    val country: String,
    val name: String,
    val coord: CoordinatesEntity
)

data class CoordinatesEntity(
    val lon: Double,
    val lat: Double
)
