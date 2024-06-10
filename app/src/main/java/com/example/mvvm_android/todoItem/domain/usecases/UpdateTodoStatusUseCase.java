package com.example.mvvm_android.todoItem.domain.usecases;

import com.example.mvvm_android.todoCore.data.repository.TodoRepository;

public class UpdateTodoStatusUseCase {
    private final TodoRepository todoRepository;

    public UpdateTodoStatusUseCase() {
        this.todoRepository = new TodoRepository();
    }

    public void execute(String createdAt, boolean status) {
        todoRepository.updateTodoStatus(createdAt, status);
    }
}
