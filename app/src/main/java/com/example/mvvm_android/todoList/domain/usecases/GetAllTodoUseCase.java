package com.example.mvvm_android.todoList.domain.usecases;

import com.example.mvvm_android.todoList.data.local.TodoRecord;
import com.example.mvvm_android.todoList.data.repository.TodoRepository;
import com.example.mvvm_android.todoList.domain.models.Todo;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;

public class GetAllTodoUseCase {
    private final TodoRepository todoRepository;

    public GetAllTodoUseCase() {
        todoRepository = new TodoRepository();
    }

    public List<Todo> execute() {
        RealmResults<TodoRecord> realmResults = todoRepository.getAllTodos();

        List<Todo> todos = new ArrayList<>();

        for(TodoRecord todoRecord : realmResults) {
            Todo todo = new Todo(todoRecord.getId(), todoRecord.getContent(), todoRecord.isCompleted(), todoRecord.getDatetime());
            todos.add(todo);
        }

        return todos;
    }
}
