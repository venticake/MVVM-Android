package com.example.mvvm_android.todoDetail.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TodoDetailViewModel extends ViewModel {
    private MutableLiveData<String> content = new MutableLiveData<>("this is dummy");

    public LiveData<String> getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content.setValue(content);
    }
}
