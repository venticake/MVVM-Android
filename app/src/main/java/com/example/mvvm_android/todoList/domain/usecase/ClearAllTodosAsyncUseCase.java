package com.example.mvvm_android.todoList.domain.usecase;

import com.example.mvvm_android.todoCore.data.repository.TodoRepository;

public class ClearAllTodosAsyncUseCase {
    private final TodoRepository todoRepository;

    public ClearAllTodosAsyncUseCase() { this.todoRepository = new TodoRepository(); }

    public void execute() {
        todoRepository.clearAllTodosAsync();
    }
}
