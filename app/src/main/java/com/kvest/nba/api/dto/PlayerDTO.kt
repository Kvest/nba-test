package com.kvest.nba.api.dto

import com.squareup.moshi.Json

class PlayerDTO(
    @field:Json(name = "id")
    val id: Long,
    @field:Json(name = "first_name")
    val firstName: String,
    @field:Json(name = "last_name")
    val lastName: String,
    @field:Json(name = "position")
    val position: String,
    @field:Json(name = "height_feet")
    val heightFeet: Int?,
    @field:Json(name = "height_inches")
    val heightInches: Int?,
    @field:Json(name = "weight_pounds")
    val weightPounds: Int?,
    @field:Json(name = "team")
    val team: TeamDTO,
)