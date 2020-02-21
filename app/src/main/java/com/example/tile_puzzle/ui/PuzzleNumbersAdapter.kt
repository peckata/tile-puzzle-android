package com.example.tile_puzzle.ui

import android.content.Context
import android.view.View
import android.widget.TextView
import com.example.tile_puzzle_view.PuzzleView

/**
 * Example {@link PuzzleView.Adapter} implementation that renders numbers in the puzzle view pieces
 */
class PuzzleNumbersAdapter(
    private val sizeX: Int,
    private val sizeY: Int,
    private val pieceSequence: List<Int>
) : PuzzleView.Adapter() {

    override fun getBoardSizeX(): Int {
        return sizeX
    }

    override fun getBoardSizeY(): Int {
        return sizeY
    }

    override fun getInitialPuzzlePieceSequence(): List<Int> {
        return pieceSequence
    }

    override fun createPuzzlePieceView(context: Context, seqNum: Int): View {
        val view = TextView(context)
        view.text = seqNum.toString()
        return view
    }

}