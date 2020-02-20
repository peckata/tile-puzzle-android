package com.example.tile_puzzle.injection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tile_puzzle.framework.ViewModelFactory
import com.example.tile_puzzle.framework.ViewModelKey
import com.example.tile_puzzle.ui.SampleActivity
import com.example.tile_puzzle.ui.SampleViewModel
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
     * Inject dependencies into [SampleActivity]
     **/
    @ContributesAndroidInjector
    abstract fun contributeSampleActivity(): SampleActivity

    /**
     * [ViewModel] identified by [SampleViewModel]::class is implemented by [SampleViewModel]
     */
    @Binds
    @IntoMap
    @ViewModelKey(SampleViewModel::class)
    abstract fun bindSampleViewModel(viewModel: SampleViewModel): ViewModel
}