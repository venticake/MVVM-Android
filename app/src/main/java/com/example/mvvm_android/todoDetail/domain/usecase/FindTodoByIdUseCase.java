package com.example.mvvm_android.todoDetail.domain.usecase;

import com.example.mvvm_android.todoCore.data.local.TodoRecord;
import com.example.mvvm_android.todoCore.data.repository.TodoRepository;
import com.example.mvvm_android.todoCore.domain.model.Todo;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;

public class FindTodoByIdUseCase {
    TodoRepository todoRepository;

    public FindTodoByIdUseCase() {
        todoRepository = new TodoRepository();
    }

    public Single<Todo> execute(long id) {
        return todoRepository
                .findTodoById(id)
                .map(todoRecord ->
                        new Todo(todoRecord.getId(),
                                todoRecord.getContent(),
                                todoRecord.isCompleted(),
                                todoRecord.getDatetime()));
    }

}
