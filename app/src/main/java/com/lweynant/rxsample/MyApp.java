package com.lweynant.rxsample;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import timber.log.Timber;

/**
 * Created by lweynant on 15/09/15.
 */
public class MyApp extends Application{


    public static RefWatcher getRefWatcher (Context context){
        Timber.d("getRefWatcher");
        MyApp app = (MyApp)context.getApplicationContext();
        return app.refWatcher;
    }

    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        refWatcher= LeakCanary.install(this);
        if (BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
        }
        Timber.d("onCreate");
    }
}
