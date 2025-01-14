package com.mtmilenkoff.locationapp

import androidx.paging.PagingSource
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mtmilenkoff.data.entities.CoordinatesEntity
import com.mtmilenkoff.data.entities.LocationsEntity
import com.mtmilenkoff.data.persistence.LocationsDao
import com.mtmilenkoff.data.persistence.LocationsDatabase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LocationsDaoTest {

    private lateinit var database: LocationsDatabase
    private lateinit var locationsDao: LocationsDao

    val baseList = listOf(
        LocationsEntity(
            id = 1,
            country = "US",
            name = "Alabama",
            coord = CoordinatesEntity(lat = 0.0, lon = 0.0)
        ),
        LocationsEntity(
            id = 2,
            country = "US",
            name = "Albuquerque",
            coord = CoordinatesEntity(lat = 0.0, lon = 0.0)
        ),
        LocationsEntity(
            id = 3,
            country = "US",
            name = "Anaheim",
            coord = CoordinatesEntity(lat = 0.0, lon = 0.0)
        ),
        LocationsEntity(
            id = 4,
            country = "US",
            name = "Arizona",
            coord = CoordinatesEntity(lat = 0.0, lon = 0.0)
        ),
        LocationsEntity(
            id = 5,
            country = "AU",
            name = "Sydney",
            coord = CoordinatesEntity(lat = 0.0, lon = 0.0)
        )
    )

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            LocationsDatabase::class.java
        ).allowMainThreadQueries().build()
        locationsDao = database.dao
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertLocation_insertsDataCorrectly() = runBlocking {
        val location = baseList[0]

        locationsDao.insertLocation(location)

        val retrieved = locationsDao.getLocation(1)
        assertEquals(location, retrieved)
    }

    @Test
    fun insertLocationsList_insertsMultipleItems() = runBlocking {
        val locations = baseList

        locationsDao.insertLocationsList(locations)

        val retrieved1 = locationsDao.getLocation(1)
        val retrieved2 = locationsDao.getLocation(2)

        assertEquals(locations[0], retrieved1)
        assertEquals(locations[1], retrieved2)
    }

    @Test
    fun deleteAll_clearsAllData() = runBlocking {
        val locations = baseList

        locationsDao.insertLocationsList(locations)
        locationsDao.deleteAll()

        val retrieved = locationsDao.getLocation(1)
        assertNull(retrieved)
    }

    @Test
    fun deleteAndInsert_replacesData() = runBlocking {
        val initialLocations = baseList

        val newLocations = listOf(
            LocationsEntity(
                id = 10,
                country = "CA",
                name = "Toronto",
                coord = CoordinatesEntity(lat = 0.0, lon = 0.0)
            ),
            LocationsEntity(
                id = 11,
                country = "CA",
                name = "Ottawa",
                coord = CoordinatesEntity(lat = 0.0, lon = 0.0)
            )
        )

        locationsDao.insertLocationsList(initialLocations)
        locationsDao.deleteAndInsert(newLocations)

        assertEquals(newLocations[0], locationsDao.getLocation(10))
        assertEquals(newLocations[1], locationsDao.getLocation(11))
    }

    @Test
    fun getLocations_filtersAndOrdersCorrectly() = runBlocking {
        val locations = baseList

        locationsDao.insertLocationsList(locations)

        val pagingSource1 = locationsDao.getLocations("A")
        val pagingSource2 = locationsDao.getLocations("Al")
        val pagingSource3 = locationsDao.getLocations("Alb")

        val expected1 = listOf(
            LocationsEntity(
                id = 1,
                country = "US",
                name = "Alabama",
                coord = CoordinatesEntity(lat = 0.0, lon = 0.0)
            ),
            LocationsEntity(
                id = 2,
                country = "US",
                name = "Albuquerque",
                coord = CoordinatesEntity(lat = 0.0, lon = 0.0)
            ),
            LocationsEntity(
                id = 3,
                country = "US",
                name = "Anaheim",
                coord = CoordinatesEntity(lat = 0.0, lon = 0.0)
            ),
            LocationsEntity(
                id = 4,
                country = "US",
                name = "Arizona",
                coord = CoordinatesEntity(lat = 0.0, lon = 0.0)
            )
        )

        val expected2 = listOf(
            LocationsEntity(
                id = 1,
                country = "US",
                name = "Alabama",
                coord = CoordinatesEntity(lat = 0.0, lon = 0.0)
            ),
            LocationsEntity(
                id = 2,
                country = "US",
                name = "Albuquerque",
                coord = CoordinatesEntity(lat = 0.0, lon = 0.0)
            ),
        )

        val expected3 = listOf(
            LocationsEntity(
                id = 2,
                country = "US",
                name = "Albuquerque",
                coord = CoordinatesEntity(lat = 0.0, lon = 0.0)
            )
        )


        val result1 = pagingSource1.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 10,
                placeholdersEnabled = false
            )
        )
        val result2 = pagingSource2.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 10,
                placeholdersEnabled = false
            )
        )
        val result3 = pagingSource3.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 10,
                placeholdersEnabled = false
            )
        )
        assertEquals(expected1, (result1 as PagingSource.LoadResult.Page).data.map { it.location })
        assertEquals(expected2, (result2 as PagingSource.LoadResult.Page).data.map { it.location })
        assertEquals(expected3, (result3 as PagingSource.LoadResult.Page).data.map { it.location })
    }
}
