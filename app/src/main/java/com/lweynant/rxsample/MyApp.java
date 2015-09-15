package com.lweynant.rxsample;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by lweynant on 15/09/15.
 */
public class MyApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
        }
    }
}
