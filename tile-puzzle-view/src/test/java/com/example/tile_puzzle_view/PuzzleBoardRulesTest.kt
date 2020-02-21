package com.example.tile_puzzle_view

import org.junit.Test

import org.junit.Assert.*

class PuzzleBoardRulesTest {


    @Test
    fun `Exception is thrown when the puzzle sequence does not match the size of the board`() {
        // TODO
    }

    @Test
    fun `Exception is thrown when the puzzle sequence does not have unique values`() {
        // TODO
    }

    @Test
    fun `Exception is thrown when the puzzle sequence values are outside the board values range`() {
        // TODO
    }

    /**
     * GIVEN: A list of 9 unique puzzle piece numbers, where value of one is zero (empty slot)
     * WHEN: A 3x3 puzzle board is created
     * THEN: Each puzzle piece will be positioned on the board based on the index in the list
     */
    @Test
    fun `Puzzle items are placed in the proper place on the Puzzle Board`() {
        // GIVEN
        val puzzlePieceSequence = listOf(1, 2, 3, 4, 5, 6, 7, 8, 0)

        // WHEN
        val puzzleBoard = PuzzleBoardRules(3, 3, puzzlePieceSequence)

        // THEN
        assertEquals(Pair(0, 0), puzzleBoard.getPositionForPuzzlePiece(1))
        assertEquals(Pair(1, 0), puzzleBoard.getPositionForPuzzlePiece(2))
        assertEquals(Pair(2, 0), puzzleBoard.getPositionForPuzzlePiece(3))
        assertEquals(Pair(0, 1), puzzleBoard.getPositionForPuzzlePiece(4))
        assertEquals(Pair(1, 1), puzzleBoard.getPositionForPuzzlePiece(5))
        assertEquals(Pair(2, 1), puzzleBoard.getPositionForPuzzlePiece(6))
        assertEquals(Pair(0, 2), puzzleBoard.getPositionForPuzzlePiece(7))
        assertEquals(Pair(1, 2), puzzleBoard.getPositionForPuzzlePiece(8))
        assertEquals(Pair(2, 2), puzzleBoard.getPositionForPuzzlePiece(0))
    }

    /**
     * GIVEN: 3x3 puzzle board with one empty slot
     * WHEN: Puzzle piece requested to move is next to the empty slot on the x axis
     * THEN: The Puzzle Piece will swap its position with the empty slot and the board will be updated
     */
    @Test
    fun `Puzzle piece should be moved to empty slot when is next to empty slot on the X axis`() {
        // GIVEN
        val puzzlePieceSequence = listOf(1, 2, 3, 4, 5, 6, 7, 8, 0)
        val puzzleBoard = PuzzleBoardRules(3, 3, puzzlePieceSequence)
        assertEquals(Pair(1, 2), puzzleBoard.getPositionForPuzzlePiece(8))
        assertEquals(Pair(2, 2), puzzleBoard.getPositionForPuzzlePiece(0))

        // WHEN
        val newPosition = puzzleBoard.movePuzzlePiece(8)

        // THEN
        assertEquals(Pair(2, 2), newPosition)
        assertEquals(Pair(2, 2), puzzleBoard.getPositionForPuzzlePiece(8))
        assertEquals(Pair(1, 2), puzzleBoard.getPositionForPuzzlePiece(0))
        assertArrayEquals(
            intArrayOf(1, 2, 3, 4, 5, 6, 7, 0, 8),
            puzzleBoard.puzzlePieceSequence.toIntArray()
        )
    }

    /**
     * GIVEN: 3x3 puzzle board with one empty slot
     * WHEN: Puzzle piece requested to move is next to the empty slot on the y axis
     * THEN: The Puzzle Piece will swap its position with the empty slot and the board will be updated
     */
    @Test
    fun `Puzzle piece should be moved to empty slot when is next to empty slot on the Y axis`() {
        // GIVEN
        val puzzlePieceSequence = listOf(1, 2, 3, 4, 5, 6, 7, 8, 0)
        val puzzleBoard = PuzzleBoardRules(3, 3, puzzlePieceSequence)
        assertEquals(Pair(2, 1), puzzleBoard.getPositionForPuzzlePiece(6))
        assertEquals(Pair(2, 2), puzzleBoard.getPositionForPuzzlePiece(0))

        // WHEN
        val newPosition = puzzleBoard.movePuzzlePiece(6)

        // THEN
        assertEquals(Pair(2, 2), newPosition)
        assertEquals(Pair(2, 2), puzzleBoard.getPositionForPuzzlePiece(6))
        assertEquals(Pair(2, 1), puzzleBoard.getPositionForPuzzlePiece(0))
        assertArrayEquals(
            intArrayOf(1, 2, 3, 4, 5, 0, 7, 8, 6),
            puzzleBoard.puzzlePieceSequence.toIntArray()
        )
    }

    /**
     * GIVEN: 3x3 puzzle board with one empty slot
     * WHEN: Puzzle piece requested to move is not next to the empty slot on the x or y axis
     * THEN: The Puzzle piece will not swap its position and the board will keep its state
     */
    @Test
    fun `Puzzle piece should not be moved when is not next to empty slot on the X or Y axis`() {
        // GIVEN
        val puzzlePieceSequence = listOf(1, 2, 3, 4, 5, 6, 7, 8, 0)
        val puzzleBoard = PuzzleBoardRules(3, 3, puzzlePieceSequence)
        assertEquals(Pair(0, 0), puzzleBoard.getPositionForPuzzlePiece(1))
        assertEquals(Pair(2, 2), puzzleBoard.getPositionForPuzzlePiece(0))

        // WHEN
        val newPosition = puzzleBoard.movePuzzlePiece(1)

        // THEN
        assertEquals(Pair(0, 0), newPosition)
        assertEquals(Pair(0, 0), puzzleBoard.getPositionForPuzzlePiece(1))
        assertEquals(Pair(2, 2), puzzleBoard.getPositionForPuzzlePiece(0))
        assertArrayEquals(
            intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 0),
            puzzleBoard.puzzlePieceSequence.toIntArray()
        )
    }

}
