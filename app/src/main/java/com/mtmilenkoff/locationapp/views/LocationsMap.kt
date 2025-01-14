package com.mtmilenkoff.locationapp.views

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.mtmilenkoff.domain.models.Location
import com.mtmilenkoff.locationapp.utils.toLatLng

@Composable
internal fun LocationsMap(modifier: Modifier = Modifier, location: Location?) {

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            location?.coord?.toLatLng() ?: LatLng(0.0, 0.0),
             INITIAL_ZOOM
        )
    }
    val markerState = rememberMarkerState()

    LaunchedEffect(location) {
        location?.coord?.toLatLng()?.let { latLng ->
            // Update the camera position
            cameraPositionState.animate(CameraUpdateFactory.newLatLngZoom(latLng, SELECT_ZOOM))

            // Update the marker position
            markerState.position = latLng
        }
    }

    GoogleMap(
        modifier = modifier,
        cameraPositionState = cameraPositionState
    ) {
        location?.let {
            Marker(
                state = markerState,
                title = "",
                snippet = "",
            )
        }
    }
}

const val INITIAL_ZOOM = 5f
const val SELECT_ZOOM = 10f