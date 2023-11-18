package com.kvest.nba.feature.team.repository

import com.kvest.nba.feature.team.model.Team

interface TeamRepository {
    suspend fun getTeam(teamId: Long): Result<Team>
}