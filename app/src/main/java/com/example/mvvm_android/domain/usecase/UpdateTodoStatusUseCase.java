package com.example.mvvm_android.domain.usecase;

import com.example.mvvm_android.data.repository.TodoRepository;

public class UpdateTodoStatusUseCase {
    private TodoRepository todoRepository;

    public UpdateTodoStatusUseCase() {
        this.todoRepository = new TodoRepository();
    }

    public void execute(int todoId, boolean status) {

    }
}
