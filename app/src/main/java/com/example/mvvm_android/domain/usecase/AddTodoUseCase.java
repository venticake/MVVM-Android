package com.example.mvvm_android.domain.usecase;

import com.example.mvvm_android.data.repository.TodoRepository;

import java.util.Random;

public class AddTodoUseCase {
    TodoRepository todoRepository;
    Random random;

    public AddTodoUseCase() {
        todoRepository = new TodoRepository();
        random = new Random();
        long seed = System.currentTimeMillis();
        random.setSeed(seed);
    }

    private static final String[] todoSample = new String[]{
            "todo1",
            "todo2",
            "todo3",
            "todo4",
            "todo5",
            "todo6",
            "todo7",
    };

    public void execute() {
        int idx = random.nextInt(todoSample.length);

        todoRepository.addTodo(todoSample[idx]);
    }

}
