package com.example.mvvm_android.todoList.domain.usecase;

import com.example.mvvm_android.todoCore.data.repository.TodoRepository;

import java.util.Random;

import io.reactivex.rxjava3.core.Completable;

/**
 * ViewModel에서 Todo를 추가할 때 사용하는 UseCase
 */
public class AddTodoAsyncUseCase {
    TodoRepository todoRepository;
    Random random;

    public AddTodoAsyncUseCase() {
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

    /**
     * todoSample에서 랜덤으로 선택한 todo를 추가한다.
     * @return Todo 추가가 완료되면 처리할 Completable을 반환한다.
     */
    public Completable execute() {
        int idx = random.nextInt(todoSample.length);


        return todoRepository.addTodoAsync(todoSample[idx]);
    }

}
