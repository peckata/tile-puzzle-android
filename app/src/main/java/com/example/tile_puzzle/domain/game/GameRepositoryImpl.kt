package com.example.tile_puzzle.domain.game

import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class GameRepositoryImpl @Inject constructor() : GameRepository {

    var currentPuzzleGame: PuzzleGame? = null

    override fun createNewGame(sizeX: Int, sizeY: Int): PuzzleGame {
        val maxSeqNum = sizeX * sizeY - 1

        val sequence = (1..maxSeqNum).toMutableList()
//        sequence.shuffle() TODO uncomment to randomize pieces
        sequence.add(0)

        currentPuzzleGame = PuzzleGame(sizeX, sizeY, sequence)
        return currentPuzzleGame!!
    }

    override fun getCurrentGame(): PuzzleGame? {
        return currentPuzzleGame
    }

    override fun saveCurrentGameSequence(newSequence: List<Int>) {
        currentPuzzleGame?.let {
            currentPuzzleGame = PuzzleGame(it.sizeX, it.sizeY, newSequence)
        }
    }

    override fun isGameComplete(): Boolean {
        // check if the game pieces are in order and last element is the empty slot
        currentPuzzleGame?.let {
            val sequence = it.sequence.toMutableList()
            val lastElement = sequence.removeAt(sequence.lastIndex)
            if (lastElement != 0) return false
            return sequence.asSequence().zipWithNext { a, b -> a < b }.all { it }
        }
        return false
    }

}