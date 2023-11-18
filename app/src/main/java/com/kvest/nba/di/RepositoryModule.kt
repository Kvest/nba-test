package com.kvest.nba.di

import com.kvest.nba.feature.player.repository.PlayerRepository
import com.kvest.nba.feature.player.repository.PlayerRepositoryImpl
import com.kvest.nba.feature.team.repository.TeamRepository
import com.kvest.nba.feature.team.repository.TeamRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindPlayerRepository(repo: PlayerRepositoryImpl): PlayerRepository

    @Binds
    abstract fun bindTeamRepository(repo: TeamRepositoryImpl): TeamRepository
}