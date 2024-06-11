package com.example.mvvm_android.todoCore.domain.model;

public class Todo {
    private long id;
    private String content;
    private boolean completed;
    private String createdAt;

    public Todo(long id, String content, boolean completed, String createdAt) {
        this.id = id;
        this.content = content;
        this.completed = completed;
        this.createdAt = createdAt;
    }

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

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
