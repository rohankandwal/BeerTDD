package com.itcse.beerrecepies;

import android.app.Application;

import timber.log.Timber;

public class BeerRecepieApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
