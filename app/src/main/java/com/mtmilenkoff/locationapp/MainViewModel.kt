package com.mtmilenkoff.locationapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mtmilenkoff.domain.models.Location
import com.mtmilenkoff.domain.usecases.ChangeFavorite
import com.mtmilenkoff.domain.usecases.GetFavoriteLocations
import com.mtmilenkoff.domain.usecases.GetLocations
import com.mtmilenkoff.domain.usecases.UpdateLocations
import com.mtmilenkoff.locationapp.MainViewModel.UIEvent.OnFavoriteLocation
import com.mtmilenkoff.locationapp.MainViewModel.UIEvent.OnFilterByFavoriteClick
import com.mtmilenkoff.locationapp.MainViewModel.UIEvent.OnFilterTyping
import com.mtmilenkoff.locationapp.MainViewModel.UIEvent.OnSelectLocation
import com.mtmilenkoff.locationapp.MainViewModel.UIEvent.OnUpdateLocations
import com.mtmilenkoff.locationapp.utils.executeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
@HiltViewModel
class MainViewModel @Inject constructor(
    private val updateLocationUseCase: UpdateLocations,
    private val getFavoriteLocationsUseCase: GetFavoriteLocations,
    private val getLocationsUseCase: GetLocations,
    private val changeFavoriteUSeCase: ChangeFavorite
) : ViewModel() {

    var uiState by mutableStateOf(UIState())
        private set

    data class UIState(
        val isLoading: Boolean = true,
        val selectedLocation: Location? = null,
        val filterFavoritesSelected: Boolean = false
    )

    private val _favoriteLocations = MutableStateFlow<PagingData<Location>>(PagingData.empty())
    val favoriteLocations: Flow<PagingData<Location>> = _favoriteLocations

    private val _locations = MutableStateFlow<PagingData<Location>>(PagingData.empty())
    val locations: Flow<PagingData<Location>> = _locations

    private val _filter = MutableStateFlow("")
    val filter: StateFlow<String> = _filter

    sealed class SideEffect {
        data class ShowError(val message: String) : SideEffect()
    }

    private val _sideEffect = Channel<SideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    sealed class UIEvent {
        data object OnUpdateLocations : UIEvent()
        data class OnSelectLocation(val location: Location?) : UIEvent()
        data class OnFavoriteLocation(val location: Location) : UIEvent()
        data class OnFilterTyping(val text: String) : UIEvent()
        data class OnFilterByFavoriteClick(val checked: Boolean) : UIEvent()
    }

    fun onUiEvent(event: UIEvent) {
        when (event) {
            OnUpdateLocations -> updateLocations()
            is OnFavoriteLocation -> handleFavoriteLocation(event.location)
            is OnSelectLocation -> selectLocation(event.location)
            is OnFilterTyping -> filterLocations(event.text)
            is OnFilterByFavoriteClick -> favoriteFilterSelected(event.checked)
        }
    }

    init {
        onUiEvent(OnUpdateLocations)
        viewModelScope.launch(Dispatchers.IO) {
            _filter.collectLatest { newQuery ->
                if (uiState.filterFavoritesSelected) {
                    getFavoriteLocationsUseCase(newQuery).cachedIn(viewModelScope)
                        .collectLatest { data -> _favoriteLocations.value = data }
                } else {
                    getLocationsUseCase(newQuery).cachedIn(viewModelScope).collectLatest { data ->
                        _locations.value = data
                    }
                }
            }
        }
    }

    private fun updateLocations() {
        viewModelScope.launch(Dispatchers.IO) {
            executeUseCase(
                useCase = updateLocationUseCase(),
                onSuccess = {
                    getFavoriteLocations()
                    getLocations()
                },
                onLoading = {
                    uiState = uiState.copy(isLoading = true)
                },
                onError = {
                    _sideEffect.send(
                        SideEffect.ShowError(it.message.ifEmpty { "Error trying to fetch data" })
                    )
                    uiState = uiState.copy(isLoading = false)
                }
            )
        }
    }

    private fun getLocations() {
        viewModelScope.launch(Dispatchers.IO) {
            getLocationsUseCase(_filter.value).cachedIn(viewModelScope).collectLatest { data ->
                _locations.value = data
            }
        }
        uiState = uiState.copy(isLoading = false)
    }

    private fun getFavoriteLocations() {
        viewModelScope.launch(Dispatchers.IO) {
            getFavoriteLocationsUseCase(_filter.value).cachedIn(viewModelScope)
                .collectLatest { data ->
                    _favoriteLocations.value = data
                }
        }
        uiState = uiState.copy(isLoading = false)
    }

    private fun selectLocation(location: Location?) {
        uiState = uiState.copy(selectedLocation = location)
    }

    private fun handleFavoriteLocation(location: Location) {
        viewModelScope.launch(Dispatchers.IO) {
            changeFavoriteUSeCase(location.id)
            getLocations()
        }
    }

    private fun filterLocations(filter: String) {
        _filter.value = filter
    }

    private fun favoriteFilterSelected(isChecked: Boolean) {
        if (isChecked) {
            getFavoriteLocations()
        } else {
            getLocations()
        }

        uiState = uiState.copy(filterFavoritesSelected = isChecked)
    }
}
