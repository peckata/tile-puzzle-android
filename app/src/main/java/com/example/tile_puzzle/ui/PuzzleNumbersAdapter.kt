package com.example.tile_puzzle.ui

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import android.widget.TextView
import com.example.tile_puzzle.framework.resourceIdFromAttribute
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

    override fun createAndBindPuzzlePieceView(context: Context, seqNum: Int): View {
        // create clickable wrapper view
        val parentView = FrameLayout(context)
        parentView.isClickable = true
        parentView.isFocusable = true
        parentView.setBackgroundResource(context.resourceIdFromAttribute(android.R.attr.selectableItemBackground))

        // add the text view and center in the parent
        val childTextView = TextView(context)
        childTextView.text = seqNum.toString()
        val childParams = FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        childParams.gravity = Gravity.CENTER
        parentView.addView(childTextView, childParams)

        return parentView
    }

}
