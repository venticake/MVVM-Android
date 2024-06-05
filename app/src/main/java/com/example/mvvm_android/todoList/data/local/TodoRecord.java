package com.example.mvvm_android.todoList.data.local;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class TodoRecord extends RealmObject {
    @PrimaryKey
    private long id;
    private String content;
    private String datetime;
    private boolean completed;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
