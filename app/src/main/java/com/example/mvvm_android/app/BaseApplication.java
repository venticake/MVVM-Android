package com.example.mvvm_android.app;

import android.app.Application;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

import io.realm.Realm;
import io.realm.RealmConfiguration;


/**
 * Application 전체에서 사용되는 기본 Application 클래스.
 * Realm DB 초기화 및 관리를 담당하는 HandlerThread와 Handler를 생성.
 * 그리고 Application에서 전역적으로 사용할 수 있는 static Handler 객체를 제공.
 */
public class BaseApplication extends Application {
    HandlerThread handlerThread;
    Looper looper;
    static Handler handler;

    @Override
    public void onCreate() {
        super.onCreate();

        initRealmThread();
    }

    /**
     * Realm 데이터베이스 초기화를 위하여 HandlerThread와 Handler를 생성하는 메서드.
     * HandlerThread를 생성하고 시작한 뒤, Looper 객체를 가져와 Handler를 생성.
     * 그리고 Handler를 사용하여 백그라운드 스레드에서 Realm 초기화 작업을 수행.
     */
    private void initRealmThread() {
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
                    .schemaVersion(3)
                    .deleteRealmIfMigrationNeeded()
                    .build();

            Realm.setDefaultConfiguration(config);
        });
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        handlerThread.quit();
    }


    /**
     * 전역적으로 사용할 수 있는 static Handler 객체를 반환하는 메서드.
     *
     * @return 전역 Handler 객체
     */
    public static Handler getHandler() {
        return handler;
    }
}
