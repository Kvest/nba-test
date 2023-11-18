package com.kvest.nba.api.dto

import com.squareup.moshi.Json

class PlayersListDTO(
    @field:Json(name = "data")
    val players: List<PlayerDTO>,
    @field:Json(name = "meta")
    val meta: PageMetaDTO,
)