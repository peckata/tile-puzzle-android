package com.example.tile_puzzle.ui.puzzle

import kotlin.math.abs

class PuzzleBoardRules(val sizeX: Int, val sizeY: Int, puzzlePieceSequence: List<Int>) {

    private val _puzzlePieceSequence = puzzlePieceSequence.toMutableList()
    val puzzlePieceSequence: List<Int> = _puzzlePieceSequence

    init {
        // validate data provided in puzzlePieceSequence: count, values, empty item
        val totalItems = sizeX * sizeY

        if (totalItems != puzzlePieceSequence.size)
            throw IllegalArgumentException("Puzzle piece sequence does not match the size of the puzzle board")

        if (puzzlePieceSequence.distinct().size != puzzlePieceSequence.size)
            throw IllegalArgumentException("Puzzle piece sequence does not have distinct items")

        puzzlePieceSequence.forEach {
            if (it !in 0 until totalItems)
                throw IllegalArgumentException("Some of the puzzle piece sequence numbers does not match the board range")
        }
    }

    /**
     *  Moves puzzle piece to empty slot and returns the new position for the specified piece number.
     *
     *  Puzzle piece will be moved only if the piece is next to empty piece, otherwise just returns
     *  the current piece location, if the piece is not allowed to move.
     *
     *  @param pieceNum The piece sequence number that we try to move to the empty slot
     *
     *  @return The new location in the puzzle board if the piece is moved, otherwise the current piece location
     *
     */
    fun movePuzzlePiece(pieceNum: Int): Pair<Int, Int> {

        val startIndex = _puzzlePieceSequence.indexOf(pieceNum)
        val endIndex = _puzzlePieceSequence.indexOf(EMPTY_PIECE_NUM)

        if (startIndex == -1) throw IllegalArgumentException("Invalid item piece number")

        val startPos = calculatePositionFromIndex(startIndex)
        val endPos = calculatePositionFromIndex(endIndex)

        return if (canMove(startPos, endPos)) {
            _puzzlePieceSequence.swap(startIndex, endIndex)
            endPos
        } else {
            startPos
        }
    }

    /**
     * Returns the position of the puzzle piece in the puzzle board
     *
     * @param pieceNum The piece sequence number
     *
     * @return The position of the puzzle piece in the puzzle board
     */
    fun getPositionForPuzzlePiece(pieceNum: Int): Pair<Int, Int> {
        val index = _puzzlePieceSequence.indexOf(pieceNum)
        if (index == -1) throw IllegalArgumentException("Invalid item piece number")
        return calculatePositionFromIndex(index)
    }

    private fun calculatePositionFromIndex(index: Int): Pair<Int, Int> {
        val x: Int = index.rem(sizeX)
        val y: Int = index / sizeX
        return Pair(x, y)
    }

    private fun canMove(startPos: Pair<Int, Int>, endPos: Pair<Int, Int>): Boolean {
        val (startX, startY) = startPos
        val (endX, endY) = endPos

        // both pos should not be outside the board
        val isInsideBoard = (startX in 0 until sizeX) && (startY in 0 until sizeY)
                && (endX in 0 until sizeX) && (endY in 0 until sizeY)
        if (!isInsideBoard) return false

        // positions need to be neighbours on x or y axis
        val isNeighborX = (startY == endY) && abs(startX - endX) == 1
        val isNeighborY = (startX == endX) && abs(startY - endY) == 1
        return isNeighborX || isNeighborY
    }

    // TODO should this be outside?
    private fun MutableList<Int>.swap(index1: Int, index2: Int) {
        val tmp = this[index1]
        this[index1] = this[index2]
        this[index2] = tmp
    }

    companion object {
        const val EMPTY_PIECE_NUM = 0
    }
}