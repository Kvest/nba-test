package com.kvest.nba.feature.player.repository

import com.kvest.nba.api.dto.PlayerDTO
import com.kvest.nba.feature.player.model.Player
import com.kvest.nba.feature.player.model.PlayerDetails

fun PlayerDTO.toPlayerModel(): Player = Player(
    id = this.id,
    firstName = this.firstName,
    lastName = this.lastName,
    position = this.position,
    teamName = this.team.name,
)


fun PlayerDTO.toPlayerDetailsModel(): PlayerDetails = PlayerDetails(
    id = this.id,
    firstName = this.firstName,
    lastName = this.lastName,
    position = this.position,
    heightFeet = this.heightFeet,
    heightInches = this.heightInches,
    weightPounds = this.weightPounds,
    teamId = this.team.id,
    teamName = this.team.name,
)