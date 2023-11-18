package com.kvest.nba.feature.player.model

class Player(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val position: String,
    val heightFeet: Int?,
    val heightInches: Int?,
    val weightPounds: Int?,
    val teamId: Long,
    val teamName: String,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Player

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
