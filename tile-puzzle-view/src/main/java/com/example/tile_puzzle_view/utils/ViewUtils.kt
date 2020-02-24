package com.example.tile_puzzle_view.utils

import android.animation.ObjectAnimator
import android.content.Context
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout

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

internal fun View.makeClickable(backgroundResId: Int):View {
    // create clickable wrapper view
    val clickableView = FrameLayout(context)
    clickableView.isClickable = true
    clickableView.isFocusable = true
    clickableView.setBackgroundResource(backgroundResId)
    // add the view and center in the parent
    val childParams = FrameLayout.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
    childParams.gravity = Gravity.CENTER
    clickableView.addView(this, childParams)
    return clickableView
}

internal fun Context.resourceIdFromAttribute(attr: Int): Int {
    val outValue = TypedValue()
    this.theme.resolveAttribute(attr, outValue, true)
    return outValue.resourceId
}
