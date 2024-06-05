package com.example.mvvm_android;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize Realm
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("todoRecord.realm")
                .schemaVersion(2)
                // Todo : UI Thread가 아닌, background에서 동작하도록 수정하기.
                // realm 전용 thread를 만들어서 처리하기
                .allowWritesOnUiThread(true)
                .build();



        Realm.setDefaultConfiguration(config);
    }
}
