package com.mtmilenkoff.locationapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mtmilenkoff.domain.models.Location
import com.mtmilenkoff.domain.usecases.GetFavoriteLocations
import com.mtmilenkoff.domain.usecases.GetLocations
import com.mtmilenkoff.domain.usecases.UpdateLocations
import com.mtmilenkoff.locationapp.MainViewModel.UIEvent.OnFavoriteLocation
import com.mtmilenkoff.locationapp.MainViewModel.UIEvent.OnSelectLocation
import com.mtmilenkoff.locationapp.MainViewModel.UIEvent.OnUpdateLocations
import com.mtmilenkoff.locationapp.utils.executeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val updateLocationUseCase: UpdateLocations,
    private val getFavoriteLocationsUseCase: GetFavoriteLocations,
    private val getLocationsUseCase: GetLocations
) : ViewModel() {

    var uiState by mutableStateOf(UIState())
    private set

    data class UIState(
        val isLoading: Boolean = true,
        val locations: List<Location> = emptyList(),
        val favoriteLocations: List<Location> = emptyList()
    )

    sealed class UIEvent {
        data object OnUpdateLocations : UIEvent()
        data class OnSelectLocation(val location: Location) : UIEvent()
        data class OnFavoriteLocation(val location: Location) : UIEvent()
    }

    fun onUiEvent(event: UIEvent) {
        when (event) {
            OnUpdateLocations -> updateLocations()
            is OnFavoriteLocation -> TODO()
            is OnSelectLocation -> TODO()
        }
    }

    private fun updateLocations() {
        if (uiState.favoriteLocations.isNotEmpty()) return

        viewModelScope.launch(Dispatchers.IO) {
            executeUseCase(
                useCase = updateLocationUseCase(),
                onSuccess = {
                    getLocations()
                    getFavoriteLocations()
                    uiState = uiState.copy(isLoading = false)
                },
                onLoading = {
                    uiState = uiState.copy(isLoading = true)
                },
                onError = {
                    uiState = uiState.copy(isLoading = false)
                }
            )
        }
    }

    private fun getLocations() {
        viewModelScope.launch(Dispatchers.IO) {
            getLocationsUseCase().collect {
                uiState = uiState.copy(locations = it)
            }
        }
    }

    private fun getFavoriteLocations() {
        viewModelScope.launch(Dispatchers.IO) {
            getFavoriteLocationsUseCase().collect {
                uiState = uiState.copy(favoriteLocations = it)
            }
        }
    }

    private fun selectLocation(location: Location) {
        // todo
    }

    private fun favoriteLocation(location: Location) {

    }
}
