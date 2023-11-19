package com.kvest.nba.feature.player.ui

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kvest.nba.feature.player.model.Player
import com.kvest.nba.feature.player.repository.PlayerRepository
import javax.inject.Inject

class PlayersPagingSource @Inject constructor(
    private val playerRepository: PlayerRepository,
) : PagingSource<Int, Player>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Player> {
        val currentPage = params.key ?: 0
        params.loadSize

        val nextPlayersPageResult =
            playerRepository.getNextPlayersPage(page = currentPage, itemsPerPage = params.loadSize)

        return if (nextPlayersPageResult.isSuccess) {
            val nextPlayersPage = requireNotNull(nextPlayersPageResult.getOrNull())

            LoadResult.Page(
                data = nextPlayersPage.players,
                prevKey = if (nextPlayersPage.currentPage == 1) null else nextPlayersPage.currentPage - 1,
                nextKey = nextPlayersPage.nextPage,
            )
        } else {
            LoadResult.Error(
                requireNotNull(nextPlayersPageResult.exceptionOrNull())
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Player>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}