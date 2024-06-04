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

    public void updateTodoStatus(final long id, final boolean completed){
        realm.executeTransaction(realm -> {
            Todo todo = realm.where(Todo.class).equalTo("id", id).findFirst();
            assert todo != null;
            todo.setCompleted(completed);
        });
    }

    public RealmResults<Todo> getAllTodos(){
        return realm.where(Todo.class).findAll();
    }
}
