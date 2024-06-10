package com.example.mvvm_android.todoDetail.domain.usecases;

import com.example.mvvm_android.todoCore.data.local.TodoRecord;
import com.example.mvvm_android.todoCore.data.repository.TodoRepository;
import com.example.mvvm_android.todoCore.domain.models.Todo;

public class FindTodoByIdUsecase {
    TodoRepository todoRepository;

    public FindTodoByIdUsecase() {
        todoRepository = new TodoRepository();
    }

    public Todo execute(long id){
        TodoRecord todoRecord = todoRepository.findTodoById(id);

        return new Todo(todoRecord.getId(),
                todoRecord.getContent(),
                todoRecord.isCompleted(),
                todoRecord.getDatetime());
    }

}
