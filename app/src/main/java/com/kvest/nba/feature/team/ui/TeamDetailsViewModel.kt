package com.kvest.nba.feature.team.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kvest.nba.feature.team.usecase.FetchTeamDetailsUseCase
import com.kvest.nba.ui.navigation.TeamDetailsDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val fetchTeamDetailsUseCase: FetchTeamDetailsUseCase,
) : ViewModel() {
    private val teamId: Long = savedStateHandle[TeamDetailsDestination.teamIdArg] ?: -1

    private val _uiState: MutableStateFlow<TeamDetailsUiState> = MutableStateFlow(
        TeamDetailsUiState.TeamDetailsLoadingState
    )
    val uiState: StateFlow<TeamDetailsUiState> = _uiState.asStateFlow()

    init {
        loadTeamDetails()
    }

    fun onIntent(intent: TeamDetailsIntent) {
        when (intent) {
            TeamDetailsIntent.ReloadTeamDetailsIntent -> reloadTeamDetails()
        }
    }

    private fun reloadTeamDetails() {
        _uiState.value = TeamDetailsUiState.TeamDetailsLoadingState
        loadTeamDetails()
    }

    private fun loadTeamDetails() {
        viewModelScope.launch {
            fetchTeamDetailsUseCase.execute(teamId)
                .onSuccess { team ->
                    _uiState.value = TeamDetailsUiState.TeamDetailsState(team)
                }
                .onFailure {
                    _uiState.value = TeamDetailsUiState.TeamDetailsLoadFailedState
                }
        }
    }
}