package com.example.mvvm_android.todoCore.data.local;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Realm DB의 todo를 위한 model class
 */
public class TodoRecord extends RealmObject {
    /**
     * id : 생성된 시간을 기본키로 사용한다.
     * */
    @PrimaryKey
    private long id;
    private String content;
    private String createdAt;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
