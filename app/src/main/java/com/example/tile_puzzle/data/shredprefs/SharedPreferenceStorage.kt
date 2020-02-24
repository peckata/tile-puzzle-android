package com.example.tile_puzzle.data.shredprefs

import android.content.Context
import android.content.SharedPreferences
import com.example.tile_puzzle.domain.PuzzleGame
import com.example.tile_puzzle.framework.IntListPreference
import com.example.tile_puzzle.framework.IntPreference

import javax.inject.Inject

/**
 * Storage for game progress.
 */
interface PreferenceStorage {

    var gameSizeX: Int
    var gameSizeY: Int
    var gameSequence: List<Int>

    fun saveGame(game: PuzzleGame)

    fun loadGame(): PuzzleGame
}

/**
 * [PreferenceStorage] impl backed by [android.content.SharedPreferences].
 */
class SharedPreferenceStorage @Inject constructor(context: Context) : PreferenceStorage {

    companion object {
        private const val PREFS_NAME = "com.example.tile_puzzle"
        private const val GAME_SIZE_X = "sizeX"
        private const val GAME_SIZE_Y = "sizeY"
        private const val GAME_SEQUENCE = "sequence"
    }

    private val prefs: Lazy<SharedPreferences> = lazy {
        // Lazy to prevent IO access to main thread.
        context.applicationContext.getSharedPreferences(
                PREFS_NAME, Context.MODE_PRIVATE
        )
    }

    override var gameSizeX by IntPreference(prefs, GAME_SIZE_X, PuzzleGame.DEFAULT_SIZE_X)
    override var gameSizeY by IntPreference(prefs, GAME_SIZE_Y, PuzzleGame.DEFAULT_SIZE_Y)
    override var gameSequence by IntListPreference(prefs, GAME_SEQUENCE, PuzzleGame.create().sequence)

    override fun saveGame(game: PuzzleGame) {
        gameSizeX = game.sizeX
        gameSizeY = game.sizeY
        gameSequence = game.sequence
    }

    override fun loadGame(): PuzzleGame {
        return PuzzleGame(gameSizeX, gameSizeY, gameSequence)
    }

}
