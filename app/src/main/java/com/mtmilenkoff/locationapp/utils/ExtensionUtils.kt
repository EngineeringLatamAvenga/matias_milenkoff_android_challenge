package com.mtmilenkoff.locationapp.utils

import com.mtmilenkoff.domain.models.Location

fun Location.getFullName() = "$name, $country"