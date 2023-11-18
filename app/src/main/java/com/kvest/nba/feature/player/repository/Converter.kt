package com.kvest.nba.feature.player.repository

import com.kvest.nba.api.dto.TeamDTO
import com.kvest.nba.feature.team.model.Team

fun TeamDTO.toTeamModel(): Team  = Team(
    id = this.id,
    abbreviation = this.abbreviation,
    city = this.city,
    conference = this.conference,
    division = this.division,
    fullName = this.fullName,
    name = this.name,
)