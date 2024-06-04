package com.example.mvvm_android.ui.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm_android.domain.model.TodoItem;
import com.example.mvvm_android.domain.usecase.AddTodoUseCase;
import com.example.mvvm_android.domain.usecase.ClearAllTodoUseCase;
import com.example.mvvm_android.domain.usecase.GetAllTodoUseCase;

import java.util.ArrayList;
import java.util.List;

public class TodoListViewModel extends ViewModel {

    private MutableLiveData<List<TodoItem>> todoList = new MutableLiveData<>();

    public LiveData<List<TodoItem>> getTodoList() {
        return todoList;
    }

    AddTodoUseCase useCaseAddTodo;
    GetAllTodoUseCase useCaseGetAllTodo;
    ClearAllTodoUseCase useCaseClearAllTodo;

    public TodoListViewModel() {
        useCaseAddTodo = new AddTodoUseCase();
        useCaseGetAllTodo = new GetAllTodoUseCase();
        useCaseClearAllTodo = new ClearAllTodoUseCase();

        todoList.setValue(useCaseGetAllTodo.execute());
    }



    public void addTodoItem(){
        useCaseAddTodo.execute();
        // 되게 비효율적으로 보인다.
        // Todo: 하나씩 업데이트 되게 수정하기.
        todoList.setValue(useCaseGetAllTodo.execute());
    }

    public void clearTodoList(){
        useCaseClearAllTodo.execute();
        todoList.setValue(new ArrayList<>());
    }
}
