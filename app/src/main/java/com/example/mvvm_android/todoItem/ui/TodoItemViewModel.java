package com.example.mvvm_android.todoItem.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm_android.todoDetail.domain.usecase.FindTodoByIdUsecase;
import com.example.mvvm_android.todoCore.domain.model.Todo;
import com.example.mvvm_android.todoItem.domain.usecase.UpdateTodoStatusUseCase;
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
    private final FindTodoByIdUsecase findTodoByIdUsecase;

    public TodoItemViewModel(){
        this.updateTodoStatusUseCase = new UpdateTodoStatusUseCase();
        findTodoByIdUsecase = new FindTodoByIdUsecase();
    }

    public void setTodoItem(Long id){
        Todo todo = findTodoByIdUsecase.execute(id);


        this.content.setValue(todo.getContent());
        this.createdAt.setValue(todo.getCreatedAt());
        this.completed.setValue(todo.isCompleted());
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
