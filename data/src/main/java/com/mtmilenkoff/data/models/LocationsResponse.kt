package com.mtmilenkoff.data.models

import com.google.gson.annotations.SerializedName

data class LocationsDto(
    @SerializedName("_id")
    val id: Int,
    val country: String,
    val name: String,
    val coord: CoordinatesDto
)

data class CoordinatesDto(
    val lon: Double,
    val lat: Double
)
