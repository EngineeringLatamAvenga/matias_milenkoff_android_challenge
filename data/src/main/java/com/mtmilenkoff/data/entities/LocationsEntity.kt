package com.mtmilenkoff.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mtmilenkoff.domain.models.Coordinates
import com.mtmilenkoff.domain.models.Locations

@Entity
data class LocationsEntity(
    @PrimaryKey
    val id: Int,
    val country: String,
    val name: String,
    @Embedded
    val coord: CoordinatesEntity
)

@Entity
data class CoordinatesEntity(
    val lon: Double,
    val lat: Double
)

fun LocationsEntity.mapToDomainModel() = Locations(
    id = id,
    country = country,
    name = name,
    coord = Coordinates(coord.lon, coord.lat)
)
