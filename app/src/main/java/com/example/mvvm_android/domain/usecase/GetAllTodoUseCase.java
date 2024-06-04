package com.example.mvvm_android.domain.usecase;

import com.example.mvvm_android.data.local.Todo;
import com.example.mvvm_android.domain.model.TodoItem;
import com.example.mvvm_android.data.repository.TodoRepository;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;

public class GetAllTodoUseCase {
    private final TodoRepository todoRepository;

    public GetAllTodoUseCase() {
        todoRepository = new TodoRepository();
    }

    public List<TodoItem> execute() {
        RealmResults<Todo> realmResults = todoRepository.getAllTodos();

        List<TodoItem> todoItems = new ArrayList<>();

        for(Todo todo : realmResults) {
            TodoItem todoItem = new TodoItem(todo.getId(), todo.getContent(), todo.isCompleted(), todo.getDatetime());
            todoItems.add(todoItem);
        }

        return todoItems;
    }
}
