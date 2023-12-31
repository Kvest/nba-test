package com.kvest.nba.feature.player.repository

import com.kvest.nba.api.PlayerService
import com.kvest.nba.api.dto.PlayersListDTO
import com.kvest.nba.feature.player.model.PlayerDetails
import com.kvest.nba.feature.player.model.PlayersPage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.CancellationException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlayerRepositoryImpl @Inject constructor(
    private val playerService: PlayerService,
) : PlayerRepository {
    override suspend fun getNextPlayersPage(
        page: Int,
        itemsPerPage: Int
    ): Result<PlayersPage> {
        return try {
            val response =
                playerService.getPlayersList(page = page, perPage = itemsPerPage, search = null)

            if (response.isSuccessful) {
                val team = requireNotNull(response.body()).toPlayersPage()

                Result.success(team)
            } else {
                Result.failure(Exception(response.message()))
            }
        } catch (e: Exception) {
            if (e is CancellationException) throw e

            Result.failure(e)
        }
    }

    override suspend fun getPlayerDetails(playerId: Long): Result<PlayerDetails> {
        return try {
            val response = playerService.getPlayer(playerId)

            if (response.isSuccessful) {
                val player = requireNotNull(response.body()).toPlayerDetailsModel()

                Result.success(player)
            } else {
                Result.failure(Exception(response.message()))
            }
        } catch (e: Exception) {
            if (e is CancellationException) throw e

            Result.failure(e)
        }
    }

    private suspend fun PlayersListDTO.toPlayersPage(): PlayersPage {
        val players = withContext(Dispatchers.Main) {
            this@toPlayersPage.players.map { it.toPlayerModel() }
        }

        return PlayersPage(
            players = players,
            currentPage = this.meta.currentPage,
            nextPage = this.meta.nextPage,
        )
    }
}