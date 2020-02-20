package com.example.tile_puzzle.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tile_puzzle.domain.SampleRepository
import com.example.tile_puzzle.framework.Event
import java.text.DateFormat
import java.util.*
import javax.inject.Inject

class SampleViewModel @Inject constructor(
        private val repository: SampleRepository
)
    : ViewModel() {

    private val formatter = DateFormat.getTimeInstance(DateFormat.MEDIUM)

    private val _time = MutableLiveData<String>()
    val time: LiveData<String> get() = _time

    private val _showMessageCommand = MutableLiveData<Event<String>>()
    val showMessageCommand: LiveData<Event<String>> get() = _showMessageCommand

    init {
        updateTime()
    }

    fun onRefresh() {
        updateTime()
        _showMessageCommand.value = Event("Time updated")
    }

    private fun updateTime() {
        val timeMillis = repository.getCurrentTime()
        _time.value = formatter.format(Date(timeMillis))
    }
}