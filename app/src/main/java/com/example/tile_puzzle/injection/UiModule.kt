package com.example.tile_puzzle.injection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tile_puzzle.framework.lifecycle.ViewModelFactory
import com.example.tile_puzzle.framework.lifecycle.ViewModelKey
import com.example.tile_puzzle.ui.GameActivity
import com.example.tile_puzzle.ui.GameViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class UiModule {

    /**
     * [ViewModelProvider.Factory] is implemented by [ViewModelFactory]
     */
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    /**
     * Inject dependencies into [GameActivity]
     **/
    @ContributesAndroidInjector
    abstract fun contributeSampleActivity(): GameActivity

    /**
     * [ViewModel] identified by [GameViewModel]::class is implemented by [GameViewModel]
     */
    @Binds
    @IntoMap
    @ViewModelKey(GameViewModel::class)
    abstract fun bindSampleViewModel(viewModel: GameViewModel): ViewModel
}