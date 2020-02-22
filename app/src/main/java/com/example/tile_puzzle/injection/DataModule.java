package com.example.tile_puzzle.injection;

import android.content.Context;

import com.example.tile_puzzle.data.shredprefs.PreferenceStorage;
import com.example.tile_puzzle.data.shredprefs.SharedPreferenceStorage;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;


@Module
public abstract class DataModule {

    @Provides
    @Singleton
    static PreferenceStorage provideSharedPreference(Context applicationContext) {
        return new SharedPreferenceStorage(applicationContext);
    }

}
