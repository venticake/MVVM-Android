package com.example.mvvm_android.todoDetail.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm_android.todoDetail.domain.usecase.FindTodoByIdUseCase;
import com.example.mvvm_android.util.SingleLiveEvent;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class TodoDetailViewModel extends ViewModel {
    private final MutableLiveData<String> content = new MutableLiveData<>("this is dummy");
    private final MutableLiveData<String> createdAt = new MutableLiveData<>("2024-01-01");
    private final MutableLiveData<String> isDone = new MutableLiveData<>("nope");

    private final SingleLiveEvent<Void> backToList = new SingleLiveEvent<>();

    private final FindTodoByIdUseCase findTodoByIdUsecase;

    private Disposable disposable;

    public TodoDetailViewModel() {
        findTodoByIdUsecase = new FindTodoByIdUseCase();
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
        disposable = findTodoByIdUsecase.execute(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        todo -> {
                            content.setValue(todo.getContent());
                            createdAt.setValue(todo.getCreatedAt());
                            isDone.setValue(todo.isCompleted() ? "It is done" : "It isn't done");
                        },
                        Throwable::printStackTrace
                );
    }

    public void backToList() {
        backToList.call();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }
}
