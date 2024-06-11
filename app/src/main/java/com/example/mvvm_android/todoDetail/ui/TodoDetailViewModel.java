package com.example.mvvm_android.todoDetail.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm_android.todoDetail.domain.usecase.FindTodoByIdUsecase;
import com.example.mvvm_android.todoCore.domain.model.Todo;
import com.example.mvvm_android.util.SingleLiveEvent;

public class TodoDetailViewModel extends ViewModel {
    private final MutableLiveData<String> content = new MutableLiveData<>("this is dummy");
    private final MutableLiveData<String> createdAt = new MutableLiveData<>("2024-01-01");
    private final MutableLiveData<String> isDone = new MutableLiveData<>("nope");

    private final SingleLiveEvent<Void> backToList = new SingleLiveEvent<>();

    private final FindTodoByIdUsecase findTodoByIdUsecase;

    public TodoDetailViewModel() {
        findTodoByIdUsecase = new FindTodoByIdUsecase();
    }

    public LiveData<String> getContent() {
        return content;
    }

    public LiveData<String> getCreatedAt() {
        return createdAt;
    }

    public LiveData<String> getIsDone() {
        return isDone;
    }

    public LiveData<Void> getBackToList() {
        return backToList;
    }

    public void setTodo(Long id) {
        // usecase를 통해 realm에서 todo를 가져온다.
        Todo todo = findTodoByIdUsecase.execute(id);

        content.setValue(todo.getContent());
        createdAt.setValue(todo.getCreatedAt());
        isDone.setValue(todo.isCompleted() ? "It is done" : "It isn't done");
    }

    public void backToList() {
        backToList.call();
    }
}
