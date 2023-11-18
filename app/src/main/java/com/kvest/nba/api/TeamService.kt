package com.kvest.nba.api

import com.kvest.nba.api.dto.TeamDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TeamService {
    @GET("teams/{team_id}")
    suspend fun getTeam(
        @Path("team_id") teamId: Long,
    ): Response<TeamDTO>
}