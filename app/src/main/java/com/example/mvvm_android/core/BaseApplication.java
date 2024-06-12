package com.example.mvvm_android.core;

import android.app.Application;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/*
*   Realm 사용을 위한 BaseApplication.
*   App 시작 시 맨 처음에 생성.
* */

public class BaseApplication extends Application {
    HandlerThread handlerThread;
    Looper looper;
    static Handler handler;

    @Override
    public void onCreate() {
        super.onCreate();

        handlerThread = new HandlerThread("RealmThread");
        handlerThread.start();
        looper = handlerThread.getLooper();
        handler = new Handler(looper);

        // 백그라운드 스레드에서 Realm 초기화 작업 수행
        handler.post(() -> {
            // Realm 초기화 및 구성
            Realm.init(this);
            RealmConfiguration config = new RealmConfiguration.Builder()
                    .name("todoRecord.realm")
                    .schemaVersion(2)
                    .build();

            Realm.setDefaultConfiguration(config);
        });
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        handlerThread.quit();
    }


    // 전역적으로 사용할 수 있는 static Handler 객체 반환
    public static Handler getHandler() {
        return handler;
    }
}
