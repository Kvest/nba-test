package com.kvest.nba.feature.player.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kvest.nba.feature.player.model.Player
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Provider

private const val ITEMS_PER_PAGE = 35
private const val INITIAL_PAGE = 1
private const val PREFETCH_DISTANCE = 4

@HiltViewModel
class PlayersListViewModel @Inject constructor(
    private val playersPagingSourceProvider: Provider<PlayersPagingSource>,
) : ViewModel() {
    val uiState: Flow<PagingData<Player>> = Pager(
        config = PagingConfig(
            pageSize = ITEMS_PER_PAGE,
            prefetchDistance = PREFETCH_DISTANCE
        ),
        initialKey = INITIAL_PAGE,
        pagingSourceFactory = { playersPagingSourceProvider.get() }
    ).flow.cachedIn(viewModelScope)
}

