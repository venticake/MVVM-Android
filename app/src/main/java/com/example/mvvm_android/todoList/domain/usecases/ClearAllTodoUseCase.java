package com.example.mvvm_android.todoList.domain.usecases;

import com.example.mvvm_android.todoCore.data.repository.TodoRepository;

public class ClearAllTodoUseCase {
    private final TodoRepository todoRepository;

    public ClearAllTodoUseCase() { this.todoRepository = new TodoRepository(); }

    public void execute() {
        todoRepository.clearAllTodos();
    }
}
