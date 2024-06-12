package com.example.mvvm_android.todoList.domain.usecase;

import com.example.mvvm_android.todoCore.data.local.TodoRecord;
import com.example.mvvm_android.todoCore.data.repository.TodoRepository;
import com.example.mvvm_android.todoCore.domain.model.Todo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class GetAllTodoAsyncUseCase {
    private final TodoRepository todoRepository;

    public GetAllTodoAsyncUseCase() {
        todoRepository = new TodoRepository();
    }

    public Single<List<Todo>> execute() {
        return todoRepository.getAllTodosAsync()
                .map(todoRecords -> {
                    List<Todo> result = new ArrayList<>();
                    for(TodoRecord todoRecord : todoRecords) {
                        result.add(new Todo(
                                todoRecord.getId(),
                                todoRecord.getContent(),
                                todoRecord.isCompleted(),
                                todoRecord.getCreatedAt())
                        );
                    }

                    return result;
                });
    }
}
