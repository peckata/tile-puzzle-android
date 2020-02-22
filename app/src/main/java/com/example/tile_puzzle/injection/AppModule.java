package com.example.tile_puzzle.injection;

import android.content.Context;

import com.example.tile_puzzle.App;

import javax.inject.Singleton;


import dagger.Module;
import dagger.Provides;

@Module
public abstract class AppModule {

    @Provides
    @Singleton
    static Context provideContext(App app) {
        return app;
    }

}