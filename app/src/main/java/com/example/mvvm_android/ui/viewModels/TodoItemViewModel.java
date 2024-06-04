package com.example.mvvm_android.ui.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TodoItemViewModel extends ViewModel {
    private final MutableLiveData<String> content = new MutableLiveData<>("This is content");
    private final MutableLiveData<Boolean> checked = new MutableLiveData<>(false);
    private final MutableLiveData<String> createdDate = new MutableLiveData<>("This is time");


    public LiveData<String> getContent(){
        return content;
    }
    public LiveData<Boolean> isChecked() { return checked; }
    public LiveData<String> getCreatedDate(){
        return createdDate;
    public LiveData<String> getCreatedAt(){
        return createdAt;
    }

    public TodoItemViewModel() {

    public void setTodoItem(String content, String createdAt, Boolean completed){
        this.content.setValue(content);
        this.createdAt.setValue(createdAt);
        this.completed.setValue(completed);
    }

    /**
     * Done 클릭 시 checked 값 변경
     * 또한 content와 createdDate 글자색을 회색으로 변경 및 가운데 줄 생성
     * */
    public void onDoneClicked(){

        checked.setValue(Boolean.FALSE.equals(checked.getValue()));

    }

}
