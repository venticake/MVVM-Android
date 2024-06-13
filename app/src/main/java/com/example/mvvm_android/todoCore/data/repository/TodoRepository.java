package com.example.mvvm_android.todoCore.data.repository;

import static io.realm.Realm.getDefaultConfiguration;

import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.mvvm_android.app.BaseApplication;
import com.example.mvvm_android.todoCore.data.local.TodoRecord;

import java.util.Date;
import java.util.Objects;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Realm DB에 있는 Todo들을 관리하는 repository
 * Todo를 추가, 조회, 수정, 삭제하는 기능 제공
 * 더 나은 사용자 경험을 위하여 비동기 연산 지원
 */
public class TodoRepository {
    private final Realm realm;
    private final Handler handler;

    private final @NonNull RealmConfiguration defaultConfiguration;

    /**
     * Realm 인스턴스, Handler 초기화
     * getDefaultConfiguration() == null 인 경우 대비
     */
    public TodoRepository() {
        this.realm = Realm.getDefaultInstance();
        handler = BaseApplication.getHandler();
        RealmConfiguration config = getDefaultConfiguration();

        if (config == null) {
            defaultConfiguration = new RealmConfiguration.Builder()
                    .name("todoRecord.realm")
                    .schemaVersion(3)
                    .deleteRealmIfMigrationNeeded()
                    .build();
        } else {
            defaultConfiguration = config;
        }
    }

    /**
     * Realm instance에 새로운 todoRecord 추가
     * 동기 방식
     *
     * @param content: todo 내용
     */
    public void addTodo(final String content) {
        realm.executeTransactionAsync(realm -> {
            TodoRecord todoRecord = realm.createObject(TodoRecord.class, System.currentTimeMillis());
            todoRecord.setContent(content);
            todoRecord.setCompleted(false);
            todoRecord.setCreatedAt(new Date(System.currentTimeMillis()).toString());
        });
    }


    /**
     * Todo item을 Realm DB에 Completable을 사용하여 비동기적으로 추가.
     *
     * @param content todo Item의 내용
     * @return 연산이 완료됐을 때 emit하는 Completable
     */
    public Completable addTodoAsync(final String content) {
        return Completable.create(emitter -> {
            handler.post(() -> {
                Realm.getInstanceAsync(defaultConfiguration, new Realm.Callback() {
                    @Override
                    public void onSuccess(@NonNull Realm realm) {
                        realm.executeTransactionAsync(_realm -> {
                            TodoRecord todoRecord = _realm.createObject(TodoRecord.class, System.currentTimeMillis());
                            todoRecord.setContent(content);
                            todoRecord.setCompleted(false);
                            todoRecord.setCreatedAt(new Date(System.currentTimeMillis()).toString());
                        }, emitter::onComplete, emitter::onError);
                    }

                    @Override
                    public void onError(@NonNull Throwable exception) {
                        super.onError(exception);
                        Log.e("Realm", Objects.requireNonNull(exception.getMessage()));
                    }
                });
            });

        }).subscribeOn(Schedulers.io());
    }

    /**
     * Realm DB의 Todo들을 삭제
     */
    public void clearAllTodos() {
        realm.executeTransaction(realm -> {
            realm.deleteAll();
        });
    }

    /**
     * Realm DB의 Todo들을 비동기적으로 삭제
     */
    public void clearAllTodosAsync() {
        Realm.getInstanceAsync(defaultConfiguration, new Realm.Callback() {
            @Override
            public void onSuccess(@NonNull Realm realm) {
                realm.executeTransactionAsync(_realm -> {
                    _realm.deleteAll();
                });
            }
        });
    }

    /**
     * Realm DB의 모든 Todo를 조회
     *
     * @return RealmResults<TodoRecord>
     */
    public RealmResults<TodoRecord> getAllTodos() {
        return realm.where(TodoRecord.class).findAll();
    }

    /**
     * Realm DB의 모든 Todo를 비동기적으로 조회
     *
     * @return 조회가 완료됐을 때 emit하는 Single<RealmResults<TodoRecord>
     */
    public Single<RealmResults<TodoRecord>> getAllTodosAsync() {
        return Single.create(emitter -> {
            handler.post(() -> {
                Realm.getInstanceAsync(defaultConfiguration, new Realm.Callback() {
                    @Override
                    public void onSuccess(@NonNull Realm realm) {
                        realm.executeTransactionAsync(_realm -> {
                            RealmResults<TodoRecord> realmResults = _realm.where(TodoRecord.class)
                                    .findAll();
                            emitter.onSuccess(realmResults);
                        }, emitter::onError);
                    }
                });
            });
        });
    }

    /**
     * id를 기반으로 Realm DB에서 Todo를 찾아 완료 상태를 업데이트
     *
     * @param id        Todo의 id
     * @param completed Todo의 상태 (true: 완료, false: 미완료)
     */
    public void updateTodoStatus(Long id, boolean completed) {
        handler.post(() -> {
            Realm.getInstanceAsync(defaultConfiguration, new Realm.Callback() {
                @Override
                public void onSuccess(@NonNull Realm realm) {
                    realm.executeTransactionAsync(_realm -> {
                        TodoRecord todoRecord = _realm.where(TodoRecord.class).equalTo("id", id).findFirst();
                        assert todoRecord != null;
                        todoRecord.setCompleted(completed);
                    });
                }

            });
        });
    }

    /**
     * id를 기반으로 Realm DB에서 Todo를 조회
     *
     * @param id Todo의 id
     * @return 조회가 완료됐을 때 emit하는 Single<TodoRecord>
     */
    public Single<TodoRecord> findTodoById(long id) {
        return Single.create(emitter -> {
            handler.post(() -> {
                // todo :
                Realm.getInstanceAsync(defaultConfiguration, new Realm.Callback() {
                    @Override
                    public void onSuccess(@NonNull Realm realm) {
                        TodoRecord todoRecord = realm.where(TodoRecord.class).equalTo("id", id).findFirst();

                        emitter.onSuccess(todoRecord);
                    }

                    @Override
                    public void onError(@NonNull Throwable exception) {
                        super.onError(exception);
                        Log.e("Realm", Objects.requireNonNull(exception.getMessage()));
                        emitter.onError(exception);
                    }
                });
            });
        });
    }
}
