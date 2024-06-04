package com.example.mvvm_android.data.repository;

import com.example.mvvm_android.data.local.Todo;

import io.realm.Realm;
import io.realm.RealmResults;

public class TodoRepository {
    private final Realm realm;

    public TodoRepository(){
        this.realm = Realm.getDefaultInstance();
    }

    public void addTodo(final String content){
        this.realm.executeTransaction(realm -> {
            Todo todo = realm.createObject(Todo.class, System.currentTimeMillis());
            todo.setContent(content);
            todo.setCompleted(false);
            todo.setDatetime(String.valueOf(System.currentTimeMillis()));
        });
    }

    public void clearAllTodos(){
        this.realm.executeTransaction(realm -> realm.deleteAll());
    }

    public RealmResults<Todo> getAllTodos(){
        return realm.where(Todo.class).findAll();
    }

    public void updateTodoStatus(final String CreatedAt, boolean completed){
        this.realm.executeTransaction(realm -> {
            Todo todo = realm.where(Todo.class).equalTo("datetime", CreatedAt).findFirst();
            assert todo != null;
            todo.setCompleted(completed);
        });
    }
}
