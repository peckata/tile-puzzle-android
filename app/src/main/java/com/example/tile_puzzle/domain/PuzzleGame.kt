package com.example.tile_puzzle.domain

class PuzzleGame(val sizeX: Int, val sizeY: Int, val sequence: List<Int>) {

    companion object {
        const val DEFAULT_SIZE_X = 3
        const val DEFAULT_SIZE_Y = 5

        fun create(): PuzzleGame {
            return create(DEFAULT_SIZE_X, DEFAULT_SIZE_Y)
        }

        fun create(sizeX: Int, sizeY: Int): PuzzleGame {
            val maxSeqNum = sizeX * sizeY - 1

            val sequence = (1..maxSeqNum).toMutableList()
            sequence.shuffle() // randomize pieces
            sequence.add(0)

            return PuzzleGame(sizeX, sizeY, sequence)
        }
    }
}
