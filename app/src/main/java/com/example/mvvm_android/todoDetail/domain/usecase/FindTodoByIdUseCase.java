package com.example.mvvm_android.todoDetail.domain.usecase;

import com.example.mvvm_android.todoCore.data.repository.TodoRepository;
import com.example.mvvm_android.todoCore.domain.model.Todo;

import io.reactivex.rxjava3.core.Single;

/**
 * ViewModel에서 id를 통해 Todo를 찾는 UseCase
 */
public class FindTodoByIdUseCase {
    TodoRepository todoRepository;

    public FindTodoByIdUseCase() {
        todoRepository = new TodoRepository();
    }


    /**
     * todoRepository에서 id를 기준으로 조회
     *
     * @param id 조회할 todo의 id
     * @return TodoRepository에서 조회한 내용을 Todo로 변환만 하여 반환
     */
    public Single<Todo> execute(long id) {
        return todoRepository
                .findTodoById(id)
                .map(todoRecord ->
                        new Todo(todoRecord.getId(),
                                todoRecord.getContent(),
                                todoRecord.isCompleted(),
                                todoRecord.getCreatedAt()));
    }

}
