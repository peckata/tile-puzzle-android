package com.example.tile_puzzle.framework

import android.content.Context
import android.util.TypedValue

fun Context.resourceIdFromAttribute(attr: Int): Int {
    val outValue = TypedValue()
    this.theme.resolveAttribute(attr, outValue, true)
    return outValue.resourceId
}
