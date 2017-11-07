package com.fanpc.myapplication.application;

import android.app.Application;
import android.content.Context;

/**
 * Created by fanpc on 2017/10/12.
 */

public class MyApplication extends Application {
    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        MyApplication.appContext = getApplicationContext();
    }

    public static Context getAppContext(){
        return appContext;
    }
}
