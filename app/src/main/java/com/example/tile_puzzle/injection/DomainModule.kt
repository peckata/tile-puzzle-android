package com.example.tile_puzzle.injection

import com.example.tile_puzzle.domain.GameRepository
import com.example.tile_puzzle.domain.GameRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DomainModule {

    /** [GameRepository] is implemented by [GameRepositoryImpl] **/
    @Binds
    @Singleton
    abstract fun bindGameRepository(impl: GameRepositoryImpl): GameRepository
}