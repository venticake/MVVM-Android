package com.example.mvvm_android.todoList.ui.views;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.mvvm_android.R;
import com.example.mvvm_android.todoList.ui.viewModels.TodoListViewModel;
import com.example.mvvm_android.databinding.FragmentTodoListBinding;
import com.example.mvvm_android.todoList.domain.models.Todo;

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

        todoListViewModel.getTodoList().observe(getViewLifecycleOwner(), todoList -> {
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
                    todoCounter = 0;
                    getChildFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    Log.d("todoCounter", "todo cleared");
                }else{
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
        TodoItemFragment todoItemFragment = TodoItemFragment.newInstance(todo);

        String fragmentTag = "todoItemFragment" + todoId;

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.FragmentContainerView, todoItemFragment, fragmentTag);
        transaction.addToBackStack(fragmentTag);
        transaction.commit();
    }
}