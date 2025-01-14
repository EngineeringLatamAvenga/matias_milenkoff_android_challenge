package com.mtmilenkoff.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mtmilenkoff.data.entities.FavoriteLocationEntity
import com.mtmilenkoff.data.entities.LocationsEntity

@Database(
    entities = [
        LocationsEntity::class,
        FavoriteLocationEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class LocationsDatabase : RoomDatabase() {
    abstract val dao: LocationsDao
}
