package com.example.mvvm_android.memo.ui.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class MemoBundleViewModel extends ViewModel {
    private final MutableLiveData<String> content = new MutableLiveData<>("dummy");
    private final PublishSubject<Object> backButton = PublishSubject.create();

    public LiveData<String> getContent() {
        return content;
    }

    public Observable<Object> getBackButton(){
        return backButton;
    }

    public void setContent(String content){
        this.content.setValue(content);
    }

    public void setBackButton(){
        backButton.onNext(new Object());
    }
}
