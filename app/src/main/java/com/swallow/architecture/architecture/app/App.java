package com.swallow.architecture.architecture.app;

import android.content.Context;
import android.os.Environment;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.swallow.architecture.architecture.view.LoadingActivity;

import swallow.com.model_utils.CrashUtils;
import swallow.com.model_utils.Utils;


public class App extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        CrashUtils.init(Environment.getExternalStorageDirectory().getPath() + "/crash/", LoadingActivity.class);
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
