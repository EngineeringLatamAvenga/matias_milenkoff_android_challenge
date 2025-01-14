package com.mtmilenkoff.data.di

import com.mtmilenkoff.data.repositories.LocationsRepositoryImpl
import com.mtmilenkoff.domain.repositories.LocationsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {
    @Binds
    abstract fun repositoryProvider(repo: LocationsRepositoryImpl): LocationsRepository
}
