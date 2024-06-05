package com.example.mvvm_android.todoList.ui.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm_android.todoList.domain.usecases.UpdateTodoStatusUseCase;
import com.example.mvvm_android.util.SingleLiveEvent;

public class TodoItemViewModel extends ViewModel {
    private final MutableLiveData<String> content = new MutableLiveData<>("This is content");
    private final MutableLiveData<Boolean> completed = new MutableLiveData<>(false);
    private final MutableLiveData<String> createdAt = new MutableLiveData<>("This is time");

    private final SingleLiveEvent<Void> startTodoDetailActivity = new SingleLiveEvent<>();


    public LiveData<String> getContent(){
        return content;
    }
    public LiveData<Boolean> isChecked() { return completed; }
    public LiveData<String> getCreatedAt(){
        return createdAt;
    }
    public LiveData<Void> getStartTodoDetailActivity(){ return startTodoDetailActivity; }

    private final UpdateTodoStatusUseCase updateTodoStatusUseCase;

    public TodoItemViewModel(){
        this.updateTodoStatusUseCase = new UpdateTodoStatusUseCase();
    }

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

        this.completed.setValue(Boolean.FALSE.equals(this.completed.getValue()));
        updateTodoStatusUseCase.execute(getCreatedAt().getValue(), Boolean.TRUE.equals(isChecked().getValue()));
    }

    public void startTodoDetailActivity(){
        startTodoDetailActivity.call();
    }

}
