package com.mtmilenkoff.locationapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mtmilenkoff.domain.models.Location
import com.mtmilenkoff.locationapp.MainViewModel
import com.mtmilenkoff.locationapp.MainViewModel.UIEvent

@Composable
fun LocationsScreen(
    viewModel: MainViewModel
) {
    Scaffold {
        LocationsScreenContent(modifier = Modifier.padding(it), viewModel = viewModel)
    }
}

@Composable
fun LocationsScreenContent(modifier: Modifier = Modifier, viewModel: MainViewModel) {
    Column(modifier = modifier) {
        LocationsList(
            locations = viewModel.uiState.locations,
            favoriteLocations = viewModel.uiState.favoriteLocations,
            onLocationClick = { viewModel.onUiEvent(UIEvent.OnSelectLocation(it)) },
            onFavoriteClick = { viewModel.onUiEvent(UIEvent.OnFavoriteLocation(it)) }
        )
    }
}
