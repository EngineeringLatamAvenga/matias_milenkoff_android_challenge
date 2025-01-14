package com.mtmilenkoff.locationapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mtmilenkoff.domain.models.Location

@Composable
internal fun LocationsMap(modifier: Modifier = Modifier, location: Location?) {
    Column(modifier = modifier) {
        Text("Todo Map")
    }
}
