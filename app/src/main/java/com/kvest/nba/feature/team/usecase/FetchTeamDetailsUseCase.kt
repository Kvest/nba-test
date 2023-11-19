package com.kvest.nba.feature.team.usecase

import com.kvest.nba.feature.team.model.Team
import com.kvest.nba.feature.team.repository.TeamRepository
import javax.inject.Inject

class FetchTeamDetailsUseCase @Inject constructor(
    private val teamRepository: TeamRepository,
) {
    suspend fun execute(teamId: Long): Result<Team> {
        return teamRepository.getTeam(teamId)
    }
}