package com.kvest.nba.api

import com.kvest.nba.api.dto.PlayerDTO
import com.kvest.nba.api.dto.PlayersListDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PlayerService {
    @GET("players")
    suspend fun getPlayersList(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("search") search: String?
    ): Response<PlayersListDTO>


    @GET("players/{player_id}")
    suspend fun getPlayer(
        @Path("player_id") playerId: Long,
    ): Response<PlayerDTO>
}