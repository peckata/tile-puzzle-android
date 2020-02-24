package com.example.tile_puzzle_view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.example.tile_puzzle_view.utils.animateViewTranslation
import com.example.tile_puzzle_view.utils.makeClickable
import com.example.tile_puzzle_view.utils.resourceIdFromAttribute
import kotlin.properties.Delegates

class PuzzleView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private lateinit var boardRules: PuzzleBoardRules
    private lateinit var puzzlePieces: List<View>

    /**
     * Callback to be invoked when the puzzle sequence changed on the Puzzle Board
     */
    var sequenceChangeListener: PuzzleSequenceChangeListener? = null

    /**
     * Adapter class for providing configuration for the Puzzle board and
     * rendering the puzzle pieces displayed in the {@link PuzzleView}
     */
    var adapter by Delegates.observable<Adapter?>(null) { _, _, newValue ->
        newValue?.let { adapter ->
            removeAllViews()
            boardRules = PuzzleBoardRules(
                adapter.getBoardSizeX(),
                adapter.getBoardSizeY(),
                adapter.getInitialPuzzlePieceSequence()
            )

            post {
                // create and display Items
                puzzlePieces = generatePuzzlePieces(boardRules, adapter)
                displayPuzzlePieces(puzzlePieces)
            }
        }
    }


    private fun generatePuzzlePieces(boardRules: PuzzleBoardRules, adapter: Adapter): List<View> {
        val pieces = mutableListOf<View>()

        // calculate puzzle piece dimensions
        val pieceWidth = width.toFloat() / adapter.getBoardSizeX()
        val pieceHeight = height.toFloat() / adapter.getBoardSizeY()

        // create views for each puzzle piece on the board
        adapter.getInitialPuzzlePieceSequence().forEach { seqPos ->
            if (seqPos != 0) {
                val boardPos = boardRules.getPositionForPuzzlePiece(seqPos)
                val pieceInfo = PuzzlePieceInfo(seqPos, boardPos, pieceWidth, pieceHeight)
                val pieceView = adapter.createClickablePuzzlePieceView(context, seqPos)
                pieceView.tag = pieceInfo
                pieceView.setOnClickListener { onPuzzleItemClick(it) }
                pieces.add(pieceView)
            }
        }
        return pieces
    }

    private fun displayPuzzlePieces(puzzlePieces: List<View>) {
        puzzlePieces.forEach { itemView ->
            val pieceInfo = itemView.tag as PuzzlePieceInfo
            val (x, y) = pieceInfo.getCoordinates()
            itemView.translationX = x
            itemView.translationY = y
            addView(itemView, LayoutParams(pieceInfo.width.toInt(), pieceInfo.height.toInt()))
        }
    }

    /**
     * Handles clicks on a Puzzle piece view.
     *
     * Depending on the position of the clicked item the puzzle view can be updated or not.
     * As result when the board is being updated anyone listening for changes in the
     * Puzzle piece sequence will be notified using the {@link PuzzleSequenceChangeListener}
     */
    private fun onPuzzleItemClick(view: View) {
        val pieceInfo = view.tag as PuzzlePieceInfo
        val currentPos = pieceInfo.boardPos
        val newPosition = boardRules.movePuzzlePiece(pieceInfo.seqNum)
        // ignore if no changes to position
        if (currentPos == newPosition) return

        // update piece with new position on board
        pieceInfo.boardPos = newPosition
        view.animateViewTranslation(pieceInfo.getCoordinates())

        // notify anyone listening for changes in puzzle
        sequenceChangeListener?.onPuzzleSequenceChange(boardRules.puzzlePieceSequence)
    }

    /**
     * Helper class for tagging and calculating the position of the Puzzle Item View
     */
    internal class PuzzlePieceInfo(
        val seqNum: Int,
        var boardPos: Pair<Int, Int>,
        val width: Float,
        val height: Float
    ) {
        /**
         * Returns the coordinates for the Puzzle item view based on the relative position in the board
         */
        fun getCoordinates(): Pair<Float, Float> {
            val (posX, posY) = boardPos
            val x = (posX * width)
            val y = (posY * height)
            return Pair(x, y)
        }
    }

    /**
     * Interface definition for a callback to be invoked when the puzzle sequence changed.
     */
    interface PuzzleSequenceChangeListener {
        /**
         * Called when a puzzle sequence has changed.
         *
         * @param pieceSequence The updated sequence of puzzle pieces.
         */
        fun onPuzzleSequenceChange(pieceSequence: List<Int>)
    }

    /**
     * Base class for an PuzzleView Adapter
     *
     * <p>Adapters provide a binding from an app-specific data set to views that are displayed
     * within a {@link PuzzleView}.</p>
     */
    abstract class Adapter {

        /**
         * Returns the total number of items in the horizontal puzzle side.
         *
         * @return The total number of items in the horizontal puzzle side.
         */
        abstract fun getBoardSizeX(): Int

        /**
         * Returns the total number of items in the vertical puzzle side.
         *
         * @return The total number of items in the vertical puzzle side.
         */
        abstract fun getBoardSizeY(): Int

        /**
         * Returns the initial sequence of puzzle items represented as flat list.
         *
         * The item position in the board is represented by the position in the list
         * when rendered from left to right and top to bottom.
         *
         * @return The initial sequence of puzzle items represented as flat list.
         */
        abstract fun getInitialPuzzlePieceSequence(): List<Int>

        /**
         * Returns the view for the puzzle piece given the specific sequence number of the position in the board.
         *
         * @param context View or Activity context
         * @param seqNum Sequence number of the puzzle item
         *
         * @return The view for the puzzle piece given the specific sequence number of the position in the board.
         */
        abstract fun createPuzzlePieceView(context: Context, seqNum: Int): View

        /**
         * Returns the clickable view for the puzzle piece given the specific sequence number of the position in the board.
         *
         * @param context View or Activity context
         * @param seqNum Sequence number of the puzzle item
         *
         * @return The view for the puzzle piece given the specific sequence number of the position in the board.
         *
         */
        fun createClickablePuzzlePieceView(context: Context, seqNum: Int): View {
            return createPuzzlePieceView(context, seqNum).makeClickable(getBackgroundResId(context))
        }

        /**
         * Returns the resource id for the background drawable of the puzzle item.
         *
         * Override this method to provide your own res id
         *
         * @return The resource id for the background drawable of the puzzle item
         */
        fun getBackgroundResId(context: Context): Int {
            return context.resourceIdFromAttribute(android.R.attr.selectableItemBackground)
        }
    }

}

fun PuzzleView.setSequenceChangeListener(f: (pieceSequence: List<Int>) -> Unit) {
    sequenceChangeListener = object : PuzzleView.PuzzleSequenceChangeListener {
        override fun onPuzzleSequenceChange(pieceSequence: List<Int>) {
            f(pieceSequence)
        }
    }
}
