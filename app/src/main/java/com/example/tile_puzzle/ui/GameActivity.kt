package com.example.tile_puzzle.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.tile_puzzle.R
import com.example.tile_puzzle.framework.DaggerAppCompatActivity
import com.example.tile_puzzle.framework.lifecycle.observeEvent
import com.example.tile_puzzle_view.setSequenceChangeListener
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.game_activity.*
import javax.inject.Inject


class GameActivity : DaggerAppCompatActivity(R.layout.game_activity) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: GameViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        puzzle_view.adapter = PuzzleNumbersAdapter(
            viewModel.game.sizeX,
            viewModel.game.sizeY,
            viewModel.game.sequence
        )

        puzzle_view.setSequenceChangeListener {
            viewModel.onPuzzleSequenceChange(it)
        }

        viewModel.showMessageCommand.observeEvent(this) { message ->
            Snackbar.make(content_layout, message, Snackbar.LENGTH_LONG).show()
        }
    }
}
