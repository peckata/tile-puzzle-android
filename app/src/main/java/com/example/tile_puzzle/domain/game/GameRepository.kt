package com.example.tile_puzzle.domain.game

interface GameRepository {

    fun createNewGame(sizeX:Int, sizeY: Int): PuzzleGame

    fun getCurrentGame(): PuzzleGame

    fun saveCurrentGameSequence(newSequence: List<Int>)

    fun isGameComplete():Boolean
}