package com.kvest.nba.feature.player.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.kvest.nba.feature.player.model.Player

@Composable
fun PlayerDetails(
    playerId: Long,
    showTeamDetails: (teamId: Long) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PlayerDetailsViewModel = hiltViewModel()
) {
    Text(text = "Show player $playerId")
}

@Composable
fun PlayerDetails(
    player: Player,
    showTeamDetails: (teamId: Long) -> Unit,
    modifier: Modifier = Modifier,
) {

}