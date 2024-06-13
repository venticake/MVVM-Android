package com.example.mvvm_android.todoList.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm_android.todoCore.domain.model.Todo;
import com.example.mvvm_android.todoList.domain.usecase.AddTodoAsyncUseCase;
import com.example.mvvm_android.todoList.domain.usecase.ClearAllTodosAsyncUseCase;
import com.example.mvvm_android.todoList.domain.usecase.GetAllTodoAsyncUseCase;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * TodoListFragment에서 사용하는 ViewModel
 */
public class TodoListViewModel extends ViewModel {

    private final MutableLiveData<List<Todo>> todoList = new MutableLiveData<>();

    public LiveData<List<Todo>> getTodoList() {
        return todoList;
    }

    private Disposable getAllTodoDisposable;
    private Disposable addTodoDisposable;

    AddTodoAsyncUseCase useCaseAddTodo;
    GetAllTodoAsyncUseCase useCaseGetAllTodo;
    ClearAllTodosAsyncUseCase useCaseClearAllTodo;

    public TodoListViewModel() {
        useCaseAddTodo = new AddTodoAsyncUseCase();
        useCaseGetAllTodo = new GetAllTodoAsyncUseCase();
        useCaseClearAllTodo = new ClearAllTodosAsyncUseCase();

        fetchTodos();
    }

    /**
     * 모든 Todo를 가져오는 메서드
     */
    public void fetchTodos() {
        getAllTodoDisposable = useCaseGetAllTodo.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        todoList::setValue,
                        Throwable::printStackTrace
                );
    }

    /**
     * Todo를 추가하는 메서드
     */
    public void addTodoItem() {
        addTodoDisposable = useCaseAddTodo.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::fetchTodos,
                        Throwable::printStackTrace
                );
    }

    /**
     * 모든 Todo를 삭제하는 메서드
     */
    public void clearTodoList() {
        useCaseClearAllTodo.execute();
        todoList.setValue(new ArrayList<>());
    }

    /**
     * Disposable과 ViewModel의 lifecycle을 맞춰주기 위해 Override
     */
    @Override
    protected void onCleared() {
        super.onCleared();
        getAllTodoDisposable.dispose();
        addTodoDisposable.dispose();
    }
}
