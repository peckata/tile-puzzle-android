package com.example.tile_puzzle

import com.example.tile_puzzle.injection.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App : DaggerApplication() {

    // Dagger initialization
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
            DaggerAppComponent.factory().create(this)
}