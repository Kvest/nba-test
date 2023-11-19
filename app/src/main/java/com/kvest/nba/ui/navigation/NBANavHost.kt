package com.kvest.nba.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun NBANavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = PlayersListDestination.route,
        modifier = modifier,
    ) {
        addPlayersListToNavGraph(navController)
        addPlayerDetailsToNavGraph(navController)
        addTeamDetailsToNavGraph(navController)
    }
}