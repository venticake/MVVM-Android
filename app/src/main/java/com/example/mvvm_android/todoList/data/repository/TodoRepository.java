package com.example.mvvm_android.todoList.data.repository;

import com.example.mvvm_android.todoList.data.local.TodoRecord;

import io.realm.Realm;
import io.realm.RealmResults;

public class TodoRepository {
    private final Realm realm;

    public TodoRepository(){
        this.realm = Realm.getDefaultInstance();
    }

    public void addTodo(final String content){
        this.realm.executeTransaction(realm -> {
            TodoRecord todoRecord = realm.createObject(TodoRecord.class, System.currentTimeMillis());
            todoRecord.setContent(content);
            todoRecord.setCompleted(false);
            todoRecord.setDatetime(String.valueOf(System.currentTimeMillis()));
        });
    }

    public void clearAllTodos(){
        this.realm.executeTransaction(realm -> realm.deleteAll());
    }

    public RealmResults<TodoRecord> getAllTodos(){
        return realm.where(TodoRecord.class).findAll();
    }

    public void updateTodoStatus(final String CreatedAt, boolean completed){
        this.realm.executeTransaction(realm -> {
            TodoRecord todoRecord = realm.where(TodoRecord.class).equalTo("datetime", CreatedAt).findFirst();
            assert todoRecord != null;
            todoRecord.setCompleted(completed);
        });
    }
}
