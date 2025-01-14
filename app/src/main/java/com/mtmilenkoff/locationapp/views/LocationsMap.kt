package com.mtmilenkoff.locationapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.mtmilenkoff.domain.models.Location
import com.mtmilenkoff.locationapp.R
import com.mtmilenkoff.locationapp.utils.getFullName
import com.mtmilenkoff.locationapp.utils.toLatLng

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun LocationsMap(
    showTopBar: Boolean,
    modifier: Modifier = Modifier,
    location: Location?,
    onMapBack: () -> Unit
) {

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
    Column(modifier) {
        if (showTopBar) {
            CenterAlignedTopAppBar(
                title = { Text(location?.getFullName() ?: "Location map") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                actions = {
                    IconButton(
                        onClick = onMapBack
                    ) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(R.drawable.ic_back_arrow),
                            contentDescription = "Clear filter"
                        )
                    }
                }
            )
        }

        GoogleMap(
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
}

const val INITIAL_ZOOM = 5f
const val SELECT_ZOOM = 10f