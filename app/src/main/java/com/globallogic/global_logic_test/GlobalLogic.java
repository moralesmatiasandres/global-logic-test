package com.globallogic.global_logic_test;

import android.app.Application;

import com.globallogic.global_logic_test.network.RestClient;

public class GlobalLogic extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RestClient.init(this);
    }
}
