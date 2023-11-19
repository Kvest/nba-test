package com.kvest.nba.feature.player.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kvest.nba.feature.player.usecase.FetchPlayerDetailsUseCase
import com.kvest.nba.ui.navigation.PlayerDetailsDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val fetchPlayerDetailsUseCase: FetchPlayerDetailsUseCase,
) : ViewModel() {
    private val playerId: Long = savedStateHandle[PlayerDetailsDestination.playerIdArg] ?: -1

    private val _uiState: MutableStateFlow<PlayerDetailsUiState> = MutableStateFlow(
        PlayerDetailsUiState.PlayerDetailsLoadingState
    )
    val uiState: StateFlow<PlayerDetailsUiState> = _uiState.asStateFlow()

    init {
        loadPlayerDetails()
    }

    fun onIntent(intent: PlayerDetailsIntent) {
        when (intent) {
            PlayerDetailsIntent.ReloadPlayerDetailsIntent -> reloadPlayerDetails()
        }
    }

    private fun reloadPlayerDetails() {
        _uiState.value = PlayerDetailsUiState.PlayerDetailsLoadingState
        loadPlayerDetails()
    }

    private fun loadPlayerDetails() {
        viewModelScope.launch {
            fetchPlayerDetailsUseCase.execute(playerId)
                .onSuccess { playerDetails ->
                    _uiState.value = PlayerDetailsUiState.PlayerDetailsState(playerDetails)
                }
                .onFailure {
                    _uiState.value = PlayerDetailsUiState.PlayerDetailsLoadFailedState
                }
        }
    }
}