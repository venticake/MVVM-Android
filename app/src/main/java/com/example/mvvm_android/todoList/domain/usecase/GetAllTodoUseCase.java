package com.example.mvvm_android.todoList.domain.usecase;

import com.example.mvvm_android.todoCore.data.local.TodoRecord;
import com.example.mvvm_android.todoCore.data.repository.TodoRepository;
import com.example.mvvm_android.todoCore.domain.model.Todo;

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
            Todo todo = new Todo(todoRecord.getId(), todoRecord.getContent(), todoRecord.isCompleted(), todoRecord.getCreatedAt());
            todos.add(todo);
        }

        return todos;
    }
}
