package com.kvest.nba.feature.player.repository

import com.kvest.nba.feature.player.model.Player
import com.kvest.nba.feature.player.model.PlayerDetails
import com.kvest.nba.feature.player.model.PlayersPage

interface PlayerRepository {
    suspend fun getNextPlayersPage(page: Int, itemsPerPage: Int): Result<PlayersPage>
    suspend fun getPlayerDetails(playerId: Long): Result<PlayerDetails>
}