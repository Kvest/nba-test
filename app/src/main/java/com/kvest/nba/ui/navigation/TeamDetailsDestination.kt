package com.kvest.nba.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kvest.nba.feature.team.ui.TeamDetails

object TeamDetailsDestination : NavigationDestination {
    private const val baseRout = "teams"
    const val teamIdArg = "team_id"
    override val route = "${baseRout}/{${teamIdArg}}"
    val arguments = listOf(
        navArgument(teamIdArg) { type = NavType.LongType }
    )

    fun buildRout(teamId: Long): String {
        return "${baseRout}/${teamId}"
    }
}

fun NavGraphBuilder.addTeamDetailsToNavGraph(navController: NavHostController) {
    composable(
        route = TeamDetailsDestination.route,
        arguments = TeamDetailsDestination.arguments,
    ) { backStackEntry ->
        val teamId = backStackEntry.arguments?.getLong(TeamDetailsDestination.teamIdArg) ?: -1

        TeamDetails(
            teamId = teamId,
            modifier = Modifier.fillMaxSize(),
        )
    }
}