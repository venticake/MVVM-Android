package com.example.mvvm_android.memo.ui.viewModel;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm_android.util.SingleLiveEvent;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class MemoViewModel extends ViewModel {
    private final ObservableField<String> _content = new ObservableField<>("RxJava Dummy");

    private final PublishSubject<String> _moveEventLabel = PublishSubject.create();

    private final PublishSubject<Object> _backButton = PublishSubject.create();

    public Observable<String> getMoveEventLabel() {
        return _moveEventLabel;
    }

    public ObservableField<String> getContent() {
        return _content;
    }

    public Observable<Object> getBackButton() {
        return _backButton;
    }

    public void onTextChangedRx(CharSequence text) {
        _content.set(text.toString());
    }

    public void move_SafeArgs() {
        _moveEventLabel.onNext("SafeArgs");
    }

    public void move_Bundle() {
        _moveEventLabel.onNext("Bundle");
    }

    public void move_ViewModel() {
        _moveEventLabel.onNext("ViewModel");
    }

    public void setBackButton(){
        _backButton.onNext(new Object());
    }
}