package com.mtmilenkoff.data.api

import com.mtmilenkoff.data.models.LocationsDto
import retrofit2.Response
import retrofit2.http.GET

interface LocationsApi {
    @GET("cities.json")
    suspend fun getLocations(): Response<List<LocationsDto>>
}
