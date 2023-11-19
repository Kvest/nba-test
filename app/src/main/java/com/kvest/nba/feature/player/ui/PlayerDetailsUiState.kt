package com.kvest.nba.feature.player.ui

import com.kvest.nba.feature.player.model.PlayerDetails

sealed interface PlayerDetailsUiState {
    data object PlayerDetailsLoadingState : PlayerDetailsUiState
    data class PlayerDetailsState(val playerDetails: PlayerDetails) : PlayerDetailsUiState
    data object PlayerDetailsLoadFailedState : PlayerDetailsUiState
}