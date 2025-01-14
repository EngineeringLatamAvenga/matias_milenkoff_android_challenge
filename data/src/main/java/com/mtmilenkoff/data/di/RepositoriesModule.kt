package com.mtmilenkoff.data.di

import com.mtmilenkoff.data.api.LocationsApi
import com.mtmilenkoff.data.persistence.LocationsDao
import com.mtmilenkoff.data.repositories.LocationsRepositoryImpl
import com.mtmilenkoff.domain.repositories.LocationsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    @Provides
    @Singleton
    fun repositoryProvider(api: LocationsApi, dao: LocationsDao): LocationsRepository {
        return LocationsRepositoryImpl(api, dao)
    }
}
