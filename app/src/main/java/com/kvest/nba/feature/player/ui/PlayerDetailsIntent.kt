package com.kvest.nba.feature.player.ui

sealed interface PlayerDetailsIntent {
    data object ReloadPlayerDetailsIntent : PlayerDetailsIntent
}