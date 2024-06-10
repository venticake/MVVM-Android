package com.example.mvvm_android.memo.ui.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MemoSafeArgsViewModel extends ViewModel {
    private final MutableLiveData<String> content = new MutableLiveData<>("dummy");

    public LiveData<String> getContent() {
        return content;
    }

    public void setContent(String content){
        this.content.setValue(content);
    }
}
