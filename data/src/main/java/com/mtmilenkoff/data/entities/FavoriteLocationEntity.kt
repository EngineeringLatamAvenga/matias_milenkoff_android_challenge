package com.mtmilenkoff.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class FavoriteLocationEntity(
    @PrimaryKey
    val id: Int
)


data class LocationWithFavorite(
    @Embedded val location: LocationsEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        entity = FavoriteLocationEntity::class
    )
    val favorite: FavoriteLocationEntity?
)