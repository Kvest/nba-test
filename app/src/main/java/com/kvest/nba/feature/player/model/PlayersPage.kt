package com.kvest.nba.feature.player.model

class PlayersPage(
    val players: List<Player>,
    val currentPage: Int,
    val nextPage: Int?,
)
