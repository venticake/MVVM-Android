package com.example.mvvm_android.todoItem.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm_android.todoDetail.domain.usecase.FindTodoByIdUseCase;
import com.example.mvvm_android.todoItem.domain.usecase.UpdateTodoStatusAsyncUseCase;
import com.example.mvvm_android.util.SingleLiveEvent;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class TodoItemViewModel extends ViewModel {
    private final MutableLiveData<String> content = new MutableLiveData<>("This is content");
    private final MutableLiveData<Boolean> completed = new MutableLiveData<>(false);
    private final MutableLiveData<String> createdAt = new MutableLiveData<>("This is time");
    private Long id;

    private final SingleLiveEvent<Void> startTodoDetailActivity = new SingleLiveEvent<>();


    public LiveData<String> getContent() {
        return content;
    }

    public LiveData<Boolean> isChecked() {
        return completed;
    }

    public LiveData<String> getCreatedAt() {
        return createdAt;
    }

    public LiveData<Void> getStartTodoDetailActivity() {
        return startTodoDetailActivity;
    }

    private final UpdateTodoStatusAsyncUseCase updateTodoStatusAsyncUseCase;
    private final FindTodoByIdUseCase findTodoByIdUsecase;

    private Disposable disposable;

    public TodoItemViewModel() {
        this.updateTodoStatusAsyncUseCase = new UpdateTodoStatusAsyncUseCase();
        findTodoByIdUsecase = new FindTodoByIdUseCase();
    }

    public void setTodoItem(Long id) {
        this.id = id;
        disposable = findTodoByIdUsecase.execute(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        todo -> {
                            this.content.setValue(todo.getContent());
                            this.createdAt.setValue(todo.getCreatedAt());
                            this.completed.setValue(todo.isCompleted());
                        },
                        Throwable::printStackTrace
                );
    }

    /**
     * Done 클릭 시 checked 값 변경
     * 또한 content와 createdDate 글자색을 회색으로 변경
     */
    public void onDoneClicked() {

        this.completed.setValue(Boolean.FALSE.equals(this.completed.getValue()));
        updateTodoStatusAsyncUseCase.execute(id, Boolean.TRUE.equals(isChecked().getValue()));
    }

    public void startTodoDetailActivity() {
        startTodoDetailActivity.call();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }

}
