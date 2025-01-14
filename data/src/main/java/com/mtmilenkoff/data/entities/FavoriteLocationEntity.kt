package com.mtmilenkoff.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteLocationEntity(
    @PrimaryKey
    val id: Int
)