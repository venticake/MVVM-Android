package com.example.mvvm_android.memoItem.viewModel;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class MemoSafeArgsViewModel extends ViewModel {
    private final ObservableField<String> content = new ObservableField<>("dummy");
    private final PublishSubject<Object> backButton = PublishSubject.create();

    public ObservableField<String> getContent() {
        return content;
    }

    public void setContent(String content){
        this.content.set(content);
    }

    public Observable<Object> getBackButton(){
        return backButton;
    }

    public void setBackButton(){
        backButton.onNext(new Object());
    }
}