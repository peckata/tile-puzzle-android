package com.example.tile_puzzle.framework.lifecycle

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

/**
 * Kotlin sugar for [EventObserver].
 */

inline fun <T> LiveData<Event<T>>.observeEvent(owner: LifecycleOwner, crossinline onEventUnhandledContent: (T) -> Unit) {
    this.observe(owner,
        EventObserver { t: T ->
            onEventUnhandledContent(t)
        })
}
