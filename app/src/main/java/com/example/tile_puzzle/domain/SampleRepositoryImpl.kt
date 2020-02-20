package com.example.tile_puzzle.domain

import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SampleRepositoryImpl @Inject constructor()
    : SampleRepository {

    override fun getCurrentTime(): Long = System.currentTimeMillis()
}