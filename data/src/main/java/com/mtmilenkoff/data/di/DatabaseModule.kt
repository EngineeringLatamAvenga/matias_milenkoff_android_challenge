package com.mtmilenkoff.data.di

import android.app.Application
import androidx.room.Room
import com.mtmilenkoff.data.persistence.LocationsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application) =
        Room.databaseBuilder(
            app.applicationContext,
            LocationsDatabase::class.java,
            "main_database"
        ).build()

    @Provides
    @Singleton
    fun provideDao(mainDatabase: LocationsDatabase) = mainDatabase.dao
}
