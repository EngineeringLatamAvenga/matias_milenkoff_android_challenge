package com.mtmilenkoff.locationapp.utils

import com.google.android.gms.maps.model.LatLng
import com.mtmilenkoff.domain.models.Coordinates
import com.mtmilenkoff.domain.models.Location

fun Location.getFullName() = "$name, $country"
fun Coordinates.toLatLng() = LatLng(lat, lon)
