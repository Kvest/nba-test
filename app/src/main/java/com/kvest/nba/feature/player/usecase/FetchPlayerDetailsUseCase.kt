package com.kvest.nba.feature.player.usecase

import com.kvest.nba.feature.player.model.PlayerDetails
import com.kvest.nba.feature.player.repository.PlayerRepository
import javax.inject.Inject

class FetchPlayerDetailsUseCase @Inject constructor(
    private val playerRepository: PlayerRepository,
) {
    suspend fun execute(palyerId: Long): Result<PlayerDetails> {
        return playerRepository.getPlayerDetails(palyerId)
    }
}