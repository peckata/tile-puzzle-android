package com.example.tile_puzzle_view.utils

import android.animation.ObjectAnimator
import android.view.View

internal fun View.animateViewTranslation(coordinates: Pair<Float, Float>) {
    val (x, y) = coordinates
    // check what kind of translation we need x or y axis
    val animateX = translationX != x
    val translation = if (animateX) "translationX" else "translationY"
    val newValue = if (animateX) x else y

    ObjectAnimator.ofFloat(this, translation, newValue).apply {
        duration = 300
        start()
    }
}
