package com.kvest.nba.feature.team.ui

import com.kvest.nba.feature.team.model.Team

sealed interface TeamDetailsUiState {
    data object TeamDetailsLoadingState : TeamDetailsUiState
    data class TeamDetailsState(val teamDetails: Team) : TeamDetailsUiState
    data object TeamDetailsLoadFailedState : TeamDetailsUiState
}