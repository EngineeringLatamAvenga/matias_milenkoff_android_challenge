package com.mtmilenkoff.data.models

import com.google.gson.annotations.SerializedName
import com.mtmilenkoff.domain.models.Coordinates
import com.mtmilenkoff.domain.models.Locations

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

fun LocationsDto.toDomainModel() = Locations(
    id = id,
    country = country,
    name = name,
    coord = coord.toDomainModel()
)


fun CoordinatesDto.toDomainModel() = Coordinates(
    lon = lon,
    lat = lat
)
