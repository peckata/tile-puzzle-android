package com.example.tile_puzzle.domain

import com.example.tile_puzzle.data.shredprefs.PreferenceStorage
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameRepositoryImpl @Inject constructor(
    private val preferenceStorage: PreferenceStorage
) : GameRepository {

    override fun createNewGame(sizeX: Int, sizeY: Int): PuzzleGame {
        val game = PuzzleGame.create(sizeX, sizeY)
        preferenceStorage.saveGame(game)
        return game
    }

    override fun getCurrentGame(): PuzzleGame {
        return preferenceStorage.loadGame()
    }

    override fun saveCurrentGameSequence(newSequence: List<Int>) {
        preferenceStorage.gameSequence = newSequence
    }

    override fun isGameComplete(): Boolean {
        // check if the game pieces are in order and last element is the empty slot
        val sequence = preferenceStorage.gameSequence.toMutableList()
        val lastElement = sequence.removeAt(sequence.lastIndex)
        if (lastElement != 0) return false
        return sequence.asSequence().zipWithNext { a, b -> a < b }.all { it }
    }

}
