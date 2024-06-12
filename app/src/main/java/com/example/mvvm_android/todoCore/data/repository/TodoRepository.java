package com.example.mvvm_android.todoCore.data.repository;

import static io.realm.Realm.getDefaultConfiguration;

import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.mvvm_android.core.BaseApplication;
import com.example.mvvm_android.todoCore.data.local.TodoRecord;

import java.util.Date;
import java.util.Objects;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmResults;

public class TodoRepository {
    private final Realm realm;
    private final Handler handler;

    public TodoRepository() {
        this.realm = Realm.getDefaultInstance();
        handler = BaseApplication.getHandler();
    }

    public void addTodo(final String content) {
        realm.executeTransactionAsync(realm -> {
            TodoRecord todoRecord = realm.createObject(TodoRecord.class, System.currentTimeMillis());
            todoRecord.setContent(content);
            todoRecord.setCompleted(false);
            todoRecord.setDatetime(new Date(System.currentTimeMillis()).toString());
        });
    }

    // 백그라운드 스레드에서 동작하도록 수정
    public Completable addTodoAsync(final String content) {
        return Completable.create(emitter -> {
            handler.post(() -> {
                Realm.getInstanceAsync(getDefaultConfiguration(), new Realm.Callback() {
                    @Override
                    public void onSuccess(@NonNull Realm realm) {
                        realm.executeTransactionAsync(_realm -> {
                            TodoRecord todoRecord = _realm.createObject(TodoRecord.class, System.currentTimeMillis());
                            todoRecord.setContent(content);
                            todoRecord.setCompleted(false);
                            todoRecord.setDatetime(new Date(System.currentTimeMillis()).toString());
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

    public void clearAllTodos() {
        realm.executeTransactionAsync(realm -> {
            realm.deleteAll();
        });
    }

    public void clearAllTodosAsync() {
        Realm.getInstanceAsync(getDefaultConfiguration(), new Realm.Callback() {
            @Override
            public void onSuccess(@NonNull Realm realm) {
                realm.executeTransactionAsync(_realm -> {
                    _realm.deleteAll();
                });
            }
        });
    }

    public RealmResults<TodoRecord> getAllTodos() {
        return realm.where(TodoRecord.class).findAll();
    }

    public Single<RealmResults<TodoRecord>> getAllTodosAsync() {
        return Single.create(emitter -> {
            handler.post(() -> {
                Realm.getInstanceAsync(getDefaultConfiguration(), new Realm.Callback() {
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

    public void updateTodoStatus(Long id, boolean completed) {
        handler.post(() -> {
            Realm.getInstanceAsync(getDefaultConfiguration(), new Realm.Callback() {
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

    public Single<TodoRecord> findTodoById(long id) {
        return Single.create(emitter -> {
            handler.post(() -> {
                Realm.getInstanceAsync(getDefaultConfiguration(), new Realm.Callback() {
                    @Override
                    public void onSuccess(Realm realm) {
                        TodoRecord todoRecord = realm.where(TodoRecord.class).equalTo("id", id).findFirst();

                        emitter.onSuccess(todoRecord);
                    }

                    @Override
                    public void onError(Throwable exception) {
                        super.onError(exception);
                        Log.e("Realm", Objects.requireNonNull(exception.getMessage()));
                        emitter.onError(exception);
                    }
                });
            });
        });
    }
}
