package com.mtmilenkoff.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

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
