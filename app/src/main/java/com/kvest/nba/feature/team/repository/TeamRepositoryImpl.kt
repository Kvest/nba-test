package com.kvest.nba.feature.team.repository

import com.kvest.nba.api.TeamService
import com.kvest.nba.feature.team.model.Team
import java.util.concurrent.CancellationException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TeamRepositoryImpl @Inject constructor(
    private val teamService: TeamService,
) : TeamRepository {
    override suspend fun getTeam(teamId: Long): Result<Team> {
        return try {
            val response = teamService.getTeam(teamId)

            if (response.isSuccessful) {
                val team = requireNotNull(response.body()).toTeamModel()

                Result.success(team)
            } else {
                Result.failure(Exception(response.message()))
            }
        } catch (e: Exception) {
            if (e is CancellationException) throw e

            Result.failure(e)
        }
    }
}