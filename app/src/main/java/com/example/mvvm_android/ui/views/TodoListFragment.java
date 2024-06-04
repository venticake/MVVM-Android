package com.example.mvvm_android.ui.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvvm_android.R;
import com.example.mvvm_android.databinding.FragmentTodoListBinding;
import com.example.mvvm_android.domain.model.TodoItem;
import com.example.mvvm_android.ui.viewModels.TodoListViewModel;

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
        binding.setLifecycleOwner(this);

        // todoList가 update될 때마다 TodoItemFragment를 추가
        // 지금 상태는 다 지우고 새로 업데이트 한다 -> 매우 비효율적
        // Todo : 하나씩 업데이트 할 수 있도록 해보기
        todoListViewModel.getTodoList().observe(getViewLifecycleOwner(), todoList -> {
            // clear fragment
            getChildFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            // Todo : 현재는 더미데이터를 기반으로 같은 내용의 TodoItemFragment를 만들고 있다. id를 기반으로 Usecase를
            //  거쳐 Realm에서 데이터를 가져올지, 단순하게 Fragment의 매개변수로 값을 처리할 지 고민해보자. (이 경우에 Fragment -> ViewModel로 값을 어떻게 보낼 지 고민해봐야한다.)
            for (TodoItem todo : todoList) {
                addTodoItemFragment(todo);
            }
        });
    }

    public void addTodoItemFragment(TodoItem todo){
        int todoId = ++todoCounter;
        TodoItemFragment todoItemFragment = TodoItemFragment.newInstance(todo);

        String fragmentTag = "todoItemFragment" + todoId;

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.FragmentContainerView, todoItemFragment, fragmentTag);
        transaction.addToBackStack(fragmentTag);
        transaction.commit();
    }
}