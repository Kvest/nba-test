package com.kvest.nba.feature.player.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kvest.nba.R
import com.kvest.nba.feature.player.model.PlayerDetails
import com.kvest.nba.ui.theme.Copper

@Composable
fun PlayerDetails(
    playerId: Long,
    showTeamDetails: (teamId: Long) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PlayerDetailsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val uiStateCopy = uiState

    when (uiStateCopy) {
        PlayerDetailsUiState.PlayerDetailsLoadFailedState -> {
            val reloadAction: () -> Unit = remember {
                { viewModel.onIntent(PlayerDetailsIntent.ReloadPlayerDetailsIntent) }
            }
            PlayerDetailsLoadFailed(
                reloadAction = reloadAction,
                modifier = modifier
            )
        }

        PlayerDetailsUiState.PlayerDetailsLoadingState -> PlayerDetailsLoading(modifier = modifier)
        is PlayerDetailsUiState.PlayerDetailsState ->
            PlayerDetails(
                playerDetails = uiStateCopy.playerDetails,
                showTeamDetails = showTeamDetails,
                modifier = modifier,
            )
    }
}

@Composable
fun PlayerDetails(
    playerDetails: PlayerDetails,
    showTeamDetails: (teamId: Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .padding(bottom = 12.dp),
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(
                        color = Copper,
                        shape = RoundedCornerShape(topStart = 6.dp)
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = playerDetails.position,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
            }

            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "${playerDetails.firstName} ${playerDetails.lastName}",
                fontSize = 20.sp,
                style = MaterialTheme.typography.body1,
            )
        }
        
        Row(
            modifier = Modifier
                .clickable { showTeamDetails(playerDetails.teamId) }
                .padding(bottom = 8.dp),
        ) {
            Text(
                modifier = Modifier.padding(bottom = 8.dp, end = 8.dp),
                text = stringResource(R.string.play_in, playerDetails.teamName),
                fontSize = 16.sp,
                style = MaterialTheme.typography.body1,
            )
            Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null)
        }
        
        if (playerDetails.heightFeet != null && playerDetails.heightInches != null) {
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = stringResource(R.string.player_height, playerDetails.heightFeet, playerDetails.heightInches),
                color = Color.Gray,
                style = MaterialTheme.typography.body1,
            )
        }
        if (playerDetails.weightPounds != null) {
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = stringResource(R.string.player_weight, playerDetails.weightPounds),
                color = Color.Gray,
                style = MaterialTheme.typography.body1,
            )
        }
    }


}

@Composable
fun PlayerDetailsLoadFailed(
    reloadAction: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = R.string.failed_load_player_details),
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
fun PlayerDetailsLoading(
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}