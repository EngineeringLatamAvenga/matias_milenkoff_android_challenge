package com.mtmilenkoff.data.di

import com.mtmilenkoff.data.usecases.AddFavoriteUseCase
import com.mtmilenkoff.data.usecases.GetFavoriteLocationsUseCase
import com.mtmilenkoff.data.usecases.GetLocationsUseCase
import com.mtmilenkoff.data.usecases.RemoveFavoriteUseCase
import com.mtmilenkoff.data.usecases.UpdateLocationsUseCase
import com.mtmilenkoff.domain.repositories.LocationsRepository
import com.mtmilenkoff.domain.usecases.AddFavorite
import com.mtmilenkoff.domain.usecases.GetFavoriteLocations
import com.mtmilenkoff.domain.usecases.GetLocations
import com.mtmilenkoff.domain.usecases.RemoveFavorite
import com.mtmilenkoff.domain.usecases.UpdateLocations
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    @Provides
    fun provideGetFavoriteLocations(repository: LocationsRepository): GetFavoriteLocations {
        return  GetFavoriteLocationsUseCase(repository)
    }

    @Provides
    fun provideGetLocations(repository: LocationsRepository): GetLocations {
        return GetLocationsUseCase(repository)
    }

    @Provides
    fun provideUpdateLocations(repository: LocationsRepository): UpdateLocations {
        return UpdateLocationsUseCase(repository)
    }

    @Provides
    fun provideAddFavorite(repository: LocationsRepository): AddFavorite {
        return AddFavoriteUseCase(repository)
    }

    @Provides
    fun provideRemoveFavorite(repository: LocationsRepository): RemoveFavorite {
        return RemoveFavoriteUseCase(repository)
    }
}
