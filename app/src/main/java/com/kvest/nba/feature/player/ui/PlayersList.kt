package com.kvest.nba.feature.player.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.kvest.nba.R
import com.kvest.nba.feature.player.model.Player
import com.kvest.nba.ui.theme.Copper

@Composable
fun PlayersList(
    modifier: Modifier = Modifier,
    playersListViewModel: PlayersListViewModel = viewModel()
) {
    val playerItems: LazyPagingItems<Player> =
        playersListViewModel.uiState.collectAsLazyPagingItems()

    PlayersList(modifier = modifier, playerItems = playerItems)
}

@Composable
fun PlayersList(
    modifier: Modifier = Modifier,
    playerItems: LazyPagingItems<Player>,
) {
    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(
                count = playerItems.itemCount,
                key = playerItems.itemKey { it.id }
            ) { index ->
                val player = playerItems[index]
                if (player != null) {
                    PlayersListItem(
                        player = player,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .clickable { /* TODO */ }
                    )
                }
            }

            if (playerItems.loadState.append is LoadState.Loading) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }

            if (playerItems.loadState.append is LoadState.Error) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Button(
                            onClick = { playerItems.retry() }
                        ) {
                            Text(text = stringResource(id = R.string.retry))
                        }
                    }
                }
            }
        }

        if (playerItems.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

        if (playerItems.loadState.refresh is LoadState.Error) {
            Column(
                modifier = Modifier.align(Alignment.Center),
            ) {
                Text(
                    text = stringResource(id = R.string.failed_load_players),
                    modifier = Modifier.padding(bottom = 8.dp),
                )
                Button(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onClick = { playerItems.retry() }
                ) {
                    Text(text = stringResource(id = R.string.retry))
                }
            }
        }
    }
}

@Composable
fun PlayersListItem(
    player: Player,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .height(IntrinsicSize.Min)
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(6.dp)
            )
    ) {
        Box(
            modifier = Modifier
                .width(50.dp)
                .fillMaxHeight()
                .background(
                    color = Copper,
                    shape = RoundedCornerShape(topStart = 6.dp, bottomStart = 6.dp)
                ),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = player.position,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
        }
        Column(
            modifier = Modifier.padding(top = 4.dp, bottom = 4.dp, start = 8.dp)
        ) {
            Text(
                text = "${player.firstName} ${player.lastName}",
                style = MaterialTheme.typography.body1
            )
            Text(
                text = "${player.teamName}",
                color = Color.Gray,
                style = MaterialTheme.typography.body1.copy(fontSize = 14.sp)
            )
        }
    }
}

@Preview
@Composable
fun PlayerListItemPreview() {
    val player = Player(43, "Bennedict", "Mathurin", "G-F", null, null, null, 12, "Pacers")
    PlayersListItem(player)
}
