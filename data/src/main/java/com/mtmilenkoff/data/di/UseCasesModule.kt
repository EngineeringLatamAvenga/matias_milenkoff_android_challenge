package com.mtmilenkoff.data.di

import com.mtmilenkoff.data.usecases.GetFavoriteLocationsUseCase
import com.mtmilenkoff.data.usecases.GetLocationsUseCase
import com.mtmilenkoff.domain.usecases.UpdateLocations
import com.mtmilenkoff.data.usecases.UpdateLocationsUseCase
import com.mtmilenkoff.domain.usecases.GetFavoriteLocations
import com.mtmilenkoff.domain.usecases.GetLocations
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCasesModule {
    @Binds
    abstract fun provideGetFavoriteLocations(case: GetFavoriteLocationsUseCase): GetFavoriteLocations

    @Binds
    abstract fun provideGetLocations(case: GetLocationsUseCase): GetLocations

    @Binds
    abstract fun provideUpdateLocations(case: UpdateLocationsUseCase): UpdateLocations
}
