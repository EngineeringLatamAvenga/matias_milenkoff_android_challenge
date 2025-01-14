package com.mtmilenkoff.data.models

import com.google.gson.annotations.SerializedName
import com.mtmilenkoff.data.entities.CoordinatesEntity
import com.mtmilenkoff.data.entities.LocationsEntity
import com.mtmilenkoff.domain.models.Coordinates
import com.mtmilenkoff.domain.models.Location

data class LocationsDto(
    val country: String,
    val name: String,
    @SerializedName("_id")
    val id: Int,
    val coord: CoordinatesDto
)

data class CoordinatesDto(
    val lon: Double,
    val lat: Double
)

fun LocationsDto.toDomainModel() = Location(
    id = id,
    country = country,
    name = name,
    coord = Coordinates(coord.lon, coord.lat)
)

fun LocationsDto.toEntity() = LocationsEntity(
    id = id,
    country = country,
    name = name,
    coord = CoordinatesEntity(coord.lon, coord.lat)
)
