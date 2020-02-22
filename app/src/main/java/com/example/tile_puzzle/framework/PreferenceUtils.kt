package com.example.tile_puzzle.framework

import android.content.SharedPreferences
import androidx.annotation.WorkerThread
import androidx.core.content.edit
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


class IntListPreference(
        private val preferences: Lazy<SharedPreferences>,
        private val name: String,
        private val defaultValue: List<Int>
) : ReadWriteProperty<Any, List<Int>> {

    @WorkerThread
    override fun getValue(thisRef: Any, property: KProperty<*>): List<Int> {
        val csvStringList = preferences.value.getString(name, null)
        return csvStringList?.split(",")?.map { it.trim().toInt() } ?: defaultValue
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: List<Int>) {
        preferences.value.edit { putString(name, value.joinToString()) }
    }
}

class IntPreference(
        private val preferences: Lazy<SharedPreferences>,
        private val name: String,
        private val defaultValue: Int
) : ReadWriteProperty<Any, Int> {

    @WorkerThread
    override fun getValue(thisRef: Any, property: KProperty<*>): Int {
        return preferences.value.getInt(name, defaultValue)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Int) {
        preferences.value.edit { putInt(name, value) }
    }
}