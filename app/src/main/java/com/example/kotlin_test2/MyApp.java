package com.example.kotlin_test2;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

public class MyApp extends Application {
    private static MyApp application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        ARouter.init(this);
    }

    public static MyApp getApplication() {
        return application;
    }
}
