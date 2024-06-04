package com.example.mvvm_android.domain.usecase;

import com.example.mvvm_android.data.repository.TodoRepository;

public class ClearAllTodoUseCase {
    private TodoRepository todoRepository;

    public ClearAllTodoUseCase() { this.todoRepository = new TodoRepository(); }

    public void execute() {
        todoRepository.clearAllTodos();
    }
}
