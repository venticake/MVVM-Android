package com.example.mvvm_android.todoItem.domain.usecase;

import com.example.mvvm_android.todoCore.data.repository.TodoRepository;

/**
 * ViewModel에서 Todo의 상태를 업데이트하기 위한 usecase
 */
public class UpdateTodoStatusAsyncUseCase {
    private final TodoRepository todoRepository;

    public UpdateTodoStatusAsyncUseCase() {
        this.todoRepository = new TodoRepository();
    }

    /**
     * Todo의 상태를 업데이트
     *
     * @param id Todo의 id
     * @param status Todo의 상태 (true: 완료, false: 미완료)
     */
    public void execute(Long id, boolean status) {
        todoRepository.updateTodoStatus(id, status);
    }
}
