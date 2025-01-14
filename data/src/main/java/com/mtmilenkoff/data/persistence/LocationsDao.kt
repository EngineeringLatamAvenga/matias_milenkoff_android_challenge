package com.mtmilenkoff.data.persistence

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.mtmilenkoff.data.entities.FavoriteLocationEntity
import com.mtmilenkoff.data.entities.LocationsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLocation(postEntity: LocationsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLocationsList(postEntity: List<LocationsEntity>)

    @Query("DELETE FROM LocationsEntity")
    fun deleteAll()

    @Transaction
    fun deleteAndInsert(postEntity: List<LocationsEntity>) {
        deleteAll()
        insertLocationsList(postEntity)
    }

    @Query("SELECT * FROM LocationsEntity WHERE name LIKE :filter || '%' ORDER BY name ASC, country ASC")
    fun getLocations(filter: String): PagingSource<Int, LocationsEntity>

    @Query(
        "SELECT * FROM LocationsEntity " +
            "INNER JOIN FavoriteLocationEntity ON LocationsEntity.id = FavoriteLocationEntity.id " +
            "WHERE name LIKE :filter || '%' " +
            "ORDER BY name ASC, country ASC"
    )
    fun observeFavoriteLocations(filter: String): Flow<List<LocationsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavorite(location: FavoriteLocationEntity)

    @Query("DELETE FROM FavoriteLocationEntity WHERE id = :locationId")
    fun deleteFavorite(locationId: Int)
}
