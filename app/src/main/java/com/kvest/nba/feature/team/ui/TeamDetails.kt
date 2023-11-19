package com.kvest.nba.feature.team.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kvest.nba.R
import com.kvest.nba.feature.team.model.Team

@Composable
fun TeamDetails(
    teamId: Long,
    modifier: Modifier = Modifier,
    viewModel: TeamDetailsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val uiStateCopy = uiState

    when (uiStateCopy) {
        TeamDetailsUiState.TeamDetailsLoadFailedState -> {
            val reloadAction: () -> Unit = remember {
                { viewModel.onIntent(TeamDetailsIntent.ReloadTeamDetailsIntent) }
            }

            TeamDetailsLoadFailed(reloadAction = reloadAction, modifier = modifier)
        }

        TeamDetailsUiState.TeamDetailsLoadingState -> TeamDetailsLoading(modifier = modifier)
        is TeamDetailsUiState.TeamDetailsState ->
            TeamDetails(team = uiStateCopy.teamDetails, modifier = modifier)
    }
}

@Composable
fun TeamDetails(
    team: Team,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "${team.fullName}\n(${team.abbreviation})",
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            style = MaterialTheme.typography.body1,
        )
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = stringResource(R.string.team_from, team.city),
            fontSize = 16.sp,
            style = MaterialTheme.typography.body1,
        )
        Text(
            modifier = Modifier,
            text = "${team.conference} / ${team.division}",
            color = Color.Gray,
            style = MaterialTheme.typography.body1,
        )
    }
}

@Composable
fun TeamDetailsLoadFailed(
    reloadAction: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = R.string.failed_load_team_details),
            modifier = Modifier.padding(bottom = 8.dp),
        )
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = reloadAction,
        ) {
            Text(text = stringResource(id = R.string.retry))
        }
    }
}

@Composable
fun TeamDetailsLoading(
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}