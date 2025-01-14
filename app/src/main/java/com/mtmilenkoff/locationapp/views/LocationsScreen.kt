package com.mtmilenkoff.locationapp.views

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.mtmilenkoff.locationapp.MainViewModel
import com.mtmilenkoff.locationapp.MainViewModel.UIEvent
import com.mtmilenkoff.locationapp.utils.isScreenLandscape

@Composable
fun LocationsScreen(
    viewModel: MainViewModel
) {
    Scaffold {
        if (isScreenLandscape()) {
            CompleteContent(modifier = Modifier.padding(it), viewModel = viewModel)
        } else {
            IndividualScreenContent(modifier = Modifier.padding(it), viewModel = viewModel)
        }
    }
    BackHandler(enabled = viewModel.uiState.selectedLocation != null) {
        viewModel.onUiEvent(UIEvent.OnSelectLocation(null))
    }
}

@Composable
private fun CompleteContent(modifier: Modifier = Modifier, viewModel: MainViewModel) {
    val state = viewModel.uiState
    val filter by viewModel.filter.collectAsState()
    Row(modifier = modifier) {
        LocationsList(
            modifier = Modifier.weight(0.4f),
            pagedLocations = if (state.filterFavoritesSelected) viewModel.favoriteLocations else viewModel.locations,
            selectedLocationId = state.selectedLocation?.id,
            onLocationClick = { viewModel.onUiEvent(UIEvent.OnSelectLocation(it)) },
            onFavoriteClick = { viewModel.onUiEvent(UIEvent.OnFavoriteLocation(it)) },
            filterTyping = { viewModel.onUiEvent(UIEvent.OnFilterTyping(it)) },
            filterText = filter,
            onFilterByFavoriteClick = { viewModel.onUiEvent(UIEvent.OnFilterByFavoriteClick(it))},
            favoritesSelected = state.filterFavoritesSelected
        )
        LocationsMap(
            showTopBar = false,
            modifier = Modifier.weight(0.6f),
            location = state.selectedLocation,
            onMapBack = { viewModel.onUiEvent(UIEvent.OnSelectLocation(null)) }
        )
    }
}

@Composable
private fun IndividualScreenContent(modifier: Modifier = Modifier, viewModel: MainViewModel) {
    val state = viewModel.uiState
    val filter by viewModel.filter.collectAsState()
    if (state.selectedLocation != null) {
        LocationsMap(
            showTopBar = true,
            modifier = modifier,
            location = state.selectedLocation,
            onMapBack = { viewModel.onUiEvent(UIEvent.OnSelectLocation(null)) }
        )
    } else {
        Column(modifier = modifier) {
            LocationsList(
                pagedLocations = if (state.filterFavoritesSelected) viewModel.favoriteLocations else viewModel.locations,
                selectedLocationId = state.selectedLocation?.id,
                onLocationClick = { viewModel.onUiEvent(UIEvent.OnSelectLocation(it)) },
                onFavoriteClick = { viewModel.onUiEvent(UIEvent.OnFavoriteLocation(it)) },
                filterTyping = { viewModel.onUiEvent(UIEvent.OnFilterTyping(it)) },
                filterText = filter,
                onFilterByFavoriteClick = { viewModel.onUiEvent(UIEvent.OnFilterByFavoriteClick(it))},
                favoritesSelected = state.filterFavoritesSelected
            )
        }
    }
}
