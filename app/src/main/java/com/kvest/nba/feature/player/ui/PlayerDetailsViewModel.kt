package com.kvest.nba.feature.player.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.kvest.nba.ui.navigation.PlayerDetailsDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlayerDetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val playerId: Long = savedStateHandle[PlayerDetailsDestination.playerIdArg] ?: -1
}