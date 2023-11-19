package com.kvest.nba.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kvest.nba.feature.player.ui.PlayerDetails

object PlayerDetailsDestination : NavigationDestination {
    private const val baseRout = "players"
    const val playerIdArg = "player_id"
    override val route = "${baseRout}/{${playerIdArg}}"
    val arguments = listOf(
        navArgument(playerIdArg) { type = NavType.LongType }
    )

    fun buildRout(playerId: Long): String {
        return "${baseRout}/${playerId}"
    }
}

fun NavGraphBuilder.addPlayerDetailsToNavGraph(navController: NavHostController) {
    composable(
        route = PlayerDetailsDestination.route,
        arguments = PlayerDetailsDestination.arguments,
    ) { backStackEntry ->
        val playerId = backStackEntry.arguments?.getLong(PlayerDetailsDestination.playerIdArg) ?: -1
        val showTeamDetails = remember {
            { teamId: Long -> navController.navigate(TeamDetailsDestination.buildRout(teamId)) }
        }

        PlayerDetails(
            playerId = playerId,
            showTeamDetails = showTeamDetails,
            modifier = Modifier.fillMaxSize(),
        )
    }
}