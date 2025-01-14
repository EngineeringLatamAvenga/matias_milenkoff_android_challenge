package com.mtmilenkoff.locationapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mtmilenkoff.domain.models.Location
import com.mtmilenkoff.domain.usecases.AddFavorite
import com.mtmilenkoff.domain.usecases.GetFavoriteLocations
import com.mtmilenkoff.domain.usecases.GetLocations
import com.mtmilenkoff.domain.usecases.RemoveFavorite
import com.mtmilenkoff.domain.usecases.UpdateLocations
import com.mtmilenkoff.locationapp.MainViewModel.UIEvent.OnFavoriteLocation
import com.mtmilenkoff.locationapp.MainViewModel.UIEvent.OnFilterTyping
import com.mtmilenkoff.locationapp.MainViewModel.UIEvent.OnSelectLocation
import com.mtmilenkoff.locationapp.MainViewModel.UIEvent.OnUpdateLocations
import com.mtmilenkoff.locationapp.utils.executeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
@HiltViewModel
class MainViewModel @Inject constructor(
    private val updateLocationUseCase: UpdateLocations,
    private val getFavoriteLocationsUseCase: GetFavoriteLocations,
    private val getLocationsUseCase: GetLocations,
    private val addFavoriteUSeCase: AddFavorite,
    private val removeFavoriteUseCase: RemoveFavorite
) : ViewModel() {

    var uiState by mutableStateOf(UIState())
        private set

    data class UIState(
        val isLoading: Boolean = true,
        val favoriteLocations: List<Location> = emptyList(),
        val selectedLocation: Location? = null,
        val filterFavoritesSelected: Boolean = false
    )

    private val _locations = MutableStateFlow<PagingData<Location>>(PagingData.empty())
    val locations: Flow<PagingData<Location>> = _locations

    private val _filter = MutableStateFlow("")
    val filter: StateFlow<String> = _filter

    sealed class UIEvent {
        data object OnUpdateLocations : UIEvent()
        data class OnSelectLocation(val location: Location?) : UIEvent()
        data class OnFavoriteLocation(val location: Location) : UIEvent()
        data class OnFilterTyping(val text: String) : UIEvent()
    }

    fun onUiEvent(event: UIEvent) {
        when (event) {
            OnUpdateLocations -> updateLocations()
            is OnFavoriteLocation -> handleFavoriteLocation(event.location)
            is OnSelectLocation -> selectLocation(event.location)
            is OnFilterTyping -> filterLocations(event.text)
        }
    }

    init {
        onUiEvent(OnUpdateLocations)
        viewModelScope.launch(Dispatchers.IO) {
            _filter.debounce(500L).collectLatest { newQuery ->
                getLocationsUseCase(newQuery).cachedIn(viewModelScope).collectLatest { pagingData ->
                    _locations.value = pagingData
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

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun getFavoriteLocations() {
        viewModelScope.launch(Dispatchers.IO) {
            _filter.flatMapLatest {
                getFavoriteLocationsUseCase(it)
                }.collect {
                uiState = uiState.copy(favoriteLocations = it)
            }
        }
    }

    private fun selectLocation(location: Location?) {
        uiState = uiState.copy(selectedLocation = location)
    }

    private fun handleFavoriteLocation(location: Location) {
        viewModelScope.launch(Dispatchers.IO) {
            if (uiState.favoriteLocations.contains(location)) {
                removeFavoriteUseCase(location.id)
            } else {
                addFavoriteUSeCase(location.id)
            }
        }
    }

    private fun filterLocations(filter: String) {
        _filter.value = filter
    }
}
