package com.example.mvvm_android.todoDetail.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm_android.todoDetail.domain.usecase.FindTodoByIdUseCase;
import com.example.mvvm_android.util.SingleLiveEvent;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * TodoDetailFragment에서 사용하는 ViewModel
 * SafeArgs를 통하여 Todo의 id를 받아온다.
 */
public class TodoDetailViewModel extends ViewModel {
    private final MutableLiveData<String> content = new MutableLiveData<>("this is dummy");
    private final MutableLiveData<String> createdAt = new MutableLiveData<>("2024-01-01");
    private final MutableLiveData<String> isDone = new MutableLiveData<>("nope");

    private final SingleLiveEvent<Void> backButtonEvent = new SingleLiveEvent<>();

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

    public LiveData<Void> getBackButtonEvent() {
        return backButtonEvent;
    }

    /**
     * id를 통해 Todo를 찾아서 LiveData에 담는다.
     *
     * @param id Todo의 id
     */
    public void setTodo(Long id) {
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

    /**
     * BackButton을 눌렀을 때 발생하는 이벤트 처리
     */
    public void handleBackButtonEvent() {
        backButtonEvent.call();
    }

    /**
     * RxJava의 disposable과 ViewModel의 생애주기를 맞추기 위해 사용
     */
    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }
}
