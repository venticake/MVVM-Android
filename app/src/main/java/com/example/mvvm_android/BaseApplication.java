package com.example.mvvm_android;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("todo.realm")
                .schemaVersion(1)
                .build();

        Realm.setDefaultConfiguration(config);
    }
}
