package com.example.mvvm_android.todoList.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm_android.todoCore.domain.models.Todo;
import com.example.mvvm_android.todoList.domain.usecases.AddTodoUseCase;
import com.example.mvvm_android.todoList.domain.usecases.ClearAllTodoUseCase;
import com.example.mvvm_android.todoList.domain.usecases.GetAllTodoUseCase;

import java.util.ArrayList;
import java.util.List;

public class TodoListViewModel extends ViewModel {

    private final MutableLiveData<List<Todo>> todoList = new MutableLiveData<>();

    public LiveData<List<Todo>> getTodoList() {
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
        todoList.setValue(useCaseGetAllTodo.execute());
    }

    public void clearTodoList(){
        useCaseClearAllTodo.execute();
        todoList.setValue(new ArrayList<>());
    }
}
