package com.example.mvvm_android.domain.model;

public class TodoItem {
    private long id;
    private String content;
    private boolean completed;
    private String createdDate;

    public TodoItem(long id, String content, boolean completed, String createdDate) {
        this.id = id;
        this.content = content;
        this.completed = completed;
        this.createdDate = createdDate;
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

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
