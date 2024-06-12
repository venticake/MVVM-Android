package com.example.mvvm_android.todoItem.domain.usecase;

import com.example.mvvm_android.todoCore.data.repository.TodoRepository;


public class UpdateTodoStatusUseCase {
    private final TodoRepository todoRepository;

    public UpdateTodoStatusUseCase() {
        this.todoRepository = new TodoRepository();
    }

    public void execute(Long id, boolean status) {
        todoRepository.updateTodoStatus(id, status);
    }
}
