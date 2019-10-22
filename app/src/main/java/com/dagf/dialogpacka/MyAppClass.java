package com.dagf.dialogpacka;

import android.app.Application;

import com.bugsnag.android.Bugsnag;

public class MyAppClass extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Bugsnag.init(this);
    }
}
