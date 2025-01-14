package com.mtmilenkoff.data.persistence

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.mtmilenkoff.data.entities.FavoriteLocationEntity
import com.mtmilenkoff.data.entities.LocationWithFavorite
import com.mtmilenkoff.data.entities.LocationsEntity

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

    @Query("SELECT * FROM LocationsEntity WHERE id = :id")
    fun getLocation(id: Int): LocationsEntity

    @Query(
        "SELECT * FROM LocationsEntity " +
                "LEFT JOIN FavoriteLocationEntity ON LocationsEntity.id = FavoriteLocationEntity.id " +
                "WHERE name LIKE :filter || '%' " +
                "ORDER BY name ASC, country ASC"
    )    fun getLocations(filter: String): PagingSource<Int, LocationWithFavorite>

    @Query("SELECT * FROM LocationsEntity " +
            "INNER JOIN FavoriteLocationEntity ON LocationsEntity.id = FavoriteLocationEntity.id " +
            "WHERE name LIKE :filter || '%' " +
            "ORDER BY name ASC, country ASC")
    fun getPaginatedFavorites(filter: String): PagingSource<Int, LocationsEntity>

    @Query("SELECT * FROM FavoriteLocationEntity")
    fun getFavoriteIds(): List<FavoriteLocationEntity>

    @Transaction
    fun changeFavorite(id: Int) {
        val location = getLocation(id)
        val added = addFavorite(FavoriteLocationEntity(location.id))
        if (added == -1L) deleteFavorite(location.id)
    }

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addFavorite(location: FavoriteLocationEntity): Long

    @Query("DELETE FROM FavoriteLocationEntity WHERE id = :id")
    fun deleteFavorite(id: Int)
}
