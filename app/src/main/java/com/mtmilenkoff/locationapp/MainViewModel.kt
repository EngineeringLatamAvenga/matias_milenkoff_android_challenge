package com.mtmilenkoff.locationapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mtmilenkoff.domain.usecases.UpdateLocations
import com.mtmilenkoff.locationapp.MainViewModel.UIEvent.OnUpdateLocations
import com.mtmilenkoff.locationapp.utils.executeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val updateLocationUseCase: UpdateLocations
) : ViewModel() {

    var uiState by mutableStateOf(UIState())
    private set

    data class UIState(
        val isLoading: Boolean = false
    )

    sealed class UIEvent {
        data object OnUpdateLocations : UIEvent()
    }

    fun onUiEvent(event: UIEvent) {
        when (event) {
            OnUpdateLocations -> updateLocation()
        }
    }

    private fun updateLocation() {
        viewModelScope.launch {
            executeUseCase(
                useCase = updateLocationUseCase.invoke(),
                onSuccess = {
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
}
