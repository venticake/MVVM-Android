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

/**
 * TodoItemFragment의 ViewModel
 * TodoList에서 받은 id를 가지고 Todo를 Realm에서 가져온다.
 */
public class TodoItemViewModel extends ViewModel {
    // 데이터 변경 접근 제어, 책임 분리, 테스트 용이성을 위해 MutableLiveData와 LiveData를 사용.
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
        updateTodoStatusAsyncUseCase = new UpdateTodoStatusAsyncUseCase();
        findTodoByIdUsecase = new FindTodoByIdUseCase();
    }

    /**
     * TodoItemFragment에서 받은 id를 가지고 Todo를 Realm에서 가져온다.
     * 비동기 연산
     *
     * @param id todo의 id, id > 0
     */
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
     * Done 클릭 시 checked 값을 true <-> false로 변경
     * content와 createdDate 글자색을 회색으로 변경
     */
    public void onDoneClicked() {

        this.completed.setValue(Boolean.FALSE.equals(this.completed.getValue()));
        updateTodoStatusAsyncUseCase.execute(id, Boolean.TRUE.equals(isChecked().getValue()));
    }

    public void startTodoDetailActivity() {
        startTodoDetailActivity.call();
    }

    /**
     * disposable과 생애주기를 맞추기 위해 Override.
     */
    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }

}
