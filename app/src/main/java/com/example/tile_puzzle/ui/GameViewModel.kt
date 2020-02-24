package com.example.tile_puzzle.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tile_puzzle.domain.GameRepository
import com.example.tile_puzzle.framework.lifecycle.Event
import javax.inject.Inject

class GameViewModel @Inject constructor(
    private val repository: GameRepository
) : ViewModel() {

    private val _showMessageCommand = MutableLiveData<Event<String>>()
    val showMessageCommand: LiveData<Event<String>> get() = _showMessageCommand

    val game = repository.getCurrentGame()

    fun onPuzzleSequenceChange(pieceSequence: List<Int>) {

        repository.saveCurrentGameSequence(pieceSequence)

        if (repository.isGameComplete())
            _showMessageCommand.value = Event("Congrats puzzle is complete")
    }

}