package com.example.tile_puzzle.injection

import com.example.tile_puzzle.domain.SampleRepository
import com.example.tile_puzzle.domain.SampleRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DomainModule {

    /** [SampleRepository] is implemented by [SampleRepositoryImpl] **/
    @Binds
    @Singleton
    abstract fun bindSampleRepository(impl: SampleRepositoryImpl): SampleRepository
}