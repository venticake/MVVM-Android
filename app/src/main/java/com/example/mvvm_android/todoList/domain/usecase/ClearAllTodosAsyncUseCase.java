package com.example.mvvm_android.todoList.domain.usecase;

import com.example.mvvm_android.todoCore.data.repository.TodoRepository;

/**
 * ViewModel에서 Todo를 모두 삭제하는 UseCase
 */
public class ClearAllTodosAsyncUseCase {
    private final TodoRepository todoRepository;

    public ClearAllTodosAsyncUseCase() { this.todoRepository = new TodoRepository(); }

    /**
     * TodoRepository에서 모든 Todo를 비동기적으로 삭제한다.
     */
    public void execute() {
        todoRepository.clearAllTodosAsync();
    }
}
