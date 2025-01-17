package com.mtmilenkoff.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.mtmilenkoff.domain.models.Coordinates
import com.mtmilenkoff.domain.models.Location

@Entity(
    indices = [
        Index(value = ["name"]),
        Index(value = ["id"])
    ]
)
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

fun LocationsEntity.mapToDomainModel(isFavorite: Boolean) = Location(
    id = id,
    country = country,
    name = name,
    coord = Coordinates(coord.lon, coord.lat),
    isFavorite = isFavorite
)
