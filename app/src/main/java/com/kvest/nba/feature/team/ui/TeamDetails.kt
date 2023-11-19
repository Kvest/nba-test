package com.kvest.nba.feature.team.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TeamDetails(
    teamId: Long,
    modifier: Modifier = Modifier,
) {
    Text(text = "team $teamId")
}