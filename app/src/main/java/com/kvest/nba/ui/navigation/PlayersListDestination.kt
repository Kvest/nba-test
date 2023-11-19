package com.kvest.nba.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.kvest.nba.feature.player.ui.PlayersList

object PlayersListDestination : NavigationDestination {
    override val route = "players"
}

fun NavGraphBuilder.addPlayersListToNavGraph(navController: NavHostController) {
    composable(PlayersListDestination.route) {
        val showPlayerDetails = remember {
            { playerId: Long -> navController.navigate(PlayerDetailsDestination.buildRout(playerId)) }
        }

        PlayersList(
            showPlayerDetails = showPlayerDetails,
            modifier = Modifier.fillMaxSize(),
        )
    }
}