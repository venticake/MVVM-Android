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
                .allowWritesOnUiThread(true)
                .allowQueriesOnUiThread(true)
                .build();



        Realm.setDefaultConfiguration(config);
    }
}
