package com.example.mvvm_android.memo.ui.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm_android.util.SingleLiveEvent;

public class MemoViewModel extends ViewModel {
    private final MutableLiveData<String> content = new MutableLiveData<>("dummy Text");

    private final MutableLiveData<String> moveEventLabel = new SingleLiveEvent<>();

    public LiveData<String> getMoveEventLabel(){
        return moveEventLabel;
    }

    public LiveData<String> getContent(){
        return content;
    }

    public void onTextChanged(CharSequence text){
        content.setValue(text.toString());
    }

    public void move_SafeArgs(){
        moveEventLabel.setValue("SafeArgs");
    }

    public void move_Bundle(){
        moveEventLabel.setValue("Bundle");
    }

    public void move_ViewModel(){
        moveEventLabel.setValue("ViewModel");
    }
}