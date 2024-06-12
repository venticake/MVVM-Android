package com.example.mvvm_android.todoList.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.mvvm_android.R;
import com.example.mvvm_android.todoItem.ui.TodoItemFragment;
import com.example.mvvm_android.databinding.FragmentTodoListBinding;
import com.example.mvvm_android.todoCore.domain.model.Todo;

public class TodoListFragment extends Fragment {
    private TodoListViewModel todoListViewModel;
    private FragmentTodoListBinding binding;
    private int todoCounter = 0;
    public TodoListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        requireActivity().getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Log.d("onBackPressed", "onBackPressed");
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_todo_list, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        todoListViewModel = new ViewModelProvider(this).get(TodoListViewModel.class);
        binding.setViewModel(todoListViewModel);
        // LifecyleOwner에 fragment 대신 Fragment's view lifecycle 사용
        binding.setLifecycleOwner(getViewLifecycleOwner());

        observeEvent();
    }

    private void observeEvent() {
        todoListViewModel.getTodoList().observe(getViewLifecycleOwner(), todoList -> {
            // todoCounter가 0인 경우: 아무것도 추가되지 않음 -> 전체 만듬
            if(todoCounter == 0){
                getChildFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                if(!todoList.isEmpty()){
                    for (Todo todo : todoList) {
                        addTodoItemFragment(todo);
                    }
                    Log.d("todoCounter", "total Todo added");
                }
            }else{
                if(todoList.isEmpty()){
                    // todoCounter가 0이 아니며, todoList의 크기가 0일 경우 : 투두 클러이 -> 전체 삭제
                    todoCounter = 0;
                    getChildFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    Log.d("todoCounter", "todo cleared");
                }else if(todoList.size() > todoCounter){
                    // todoCounter가 0이 아니며, todoList의 크기가 todo보다 클 경우 : 하나 추가됨 -> 마지막 하나 추가
                    addTodoItemFragment(todoList.get(todoList.size()-1));
                    Log.d("todoCounter", "new Todo added " + todoList.size());
                }
            }
        });
    }

    /**
     * todoData를 기반으로 TodoItemFragment를 추가한다.
     * */
    public void addTodoItemFragment(Todo todo){
        int todoId = ++todoCounter;
        TodoItemFragment todoItemFragment = TodoItemFragment.newInstance(todo.getId());

        String fragmentTag = "todoItemFragment" + todoId;

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.FragmentContainerView, todoItemFragment, fragmentTag);
        transaction.addToBackStack(fragmentTag);
        transaction.commit();
    }
}