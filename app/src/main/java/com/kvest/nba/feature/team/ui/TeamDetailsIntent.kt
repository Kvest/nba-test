package com.kvest.nba.feature.team.ui

sealed interface TeamDetailsIntent {
    data object ReloadTeamDetailsIntent : TeamDetailsIntent
}