package com.mtmilenkoff.data.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mtmilenkoff.data.entities.LocationsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLocation(postEntity: LocationsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLocationsList(postEntity: List<LocationsEntity>)

    @Query("SELECT * FROM LocationsEntity")
    fun observeLocations(): Flow<List<LocationsEntity>>

    @Query("SELECT * FROM LocationsEntity INNER JOIN FavoriteLocationEntity ON LocationsEntity.id = FavoriteLocationEntity.id")
    fun observeFavoriteLocations(): List<LocationsEntity>
}
