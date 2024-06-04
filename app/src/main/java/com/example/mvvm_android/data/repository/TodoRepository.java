package com.example.mvvm_android.data.repository;

import com.example.mvvm_android.data.local.Todo;

import io.realm.Realm;
import io.realm.RealmResults;

public class TodoRepository {
    private Realm realm;

    public TodoRepository(){
        this.realm = Realm.getDefaultInstance();
    }

    public void addTodo(final String content){
        this.realm.executeTransaction(realm -> {
            Todo todo = realm.createObject(Todo.class, System.currentTimeMillis());
            todo.setContent(content);
            todo.setCompleted(false);
        });
    }

    public void clearAllTodos(){
        this.realm.executeTransaction(realm -> {
            realm.deleteAll();
        });
    }

    public RealmResults<Todo> getAllTodos(){
        return realm.where(Todo.class).findAll();
    }
}
