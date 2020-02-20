package com.example.tile_puzzle.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.tile_puzzle.R
import com.google.android.material.snackbar.Snackbar
import com.example.tile_puzzle.framework.DaggerAppCompatActivity
import com.example.tile_puzzle.framework.observeEvent
import com.example.tile_puzzle.ui.SampleViewModel
import kotlinx.android.synthetic.main.sample_activity.*
import javax.inject.Inject

class SampleActivity : DaggerAppCompatActivity(R.layout.sample_activity) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: SampleViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Dependency injection and content view creation are complete at this point

        refresh_fab.setOnClickListener {
            viewModel.onRefresh()
        }

        // ViewModel can't have any references to its Views.
        // Instead Views should observe the ViewModel.

        viewModel.time.observe(this) { text ->
            time_txt.text = text
        }

        viewModel.showMessageCommand.observeEvent(this) { message ->
            Snackbar.make(content_layout, message, Snackbar.LENGTH_LONG)
                    .show()
        }
    }
}
