package com.example.mvvm_android.ui.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvvm_android.R;
import com.example.mvvm_android.databinding.FragmentTodoItemBinding;
import com.example.mvvm_android.domain.model.TodoItem;
import com.example.mvvm_android.ui.viewModels.TodoItemViewModel;

public class TodoItemFragment extends Fragment {
    private TodoItemViewModel todoItemViewModel;
    private FragmentTodoItemBinding binding;

    public TodoItemFragment() {
        // Required empty public constructor
    }

    public static TodoItemFragment newInstance(TodoItem todoItem) {
        TodoItemFragment fragment = new TodoItemFragment();
        Bundle args = new Bundle();
        // 이걸 나눠서 담아..?
        args.putString("content", todoItem.getContent());
        args.putString("createdAt", todoItem.getCreatedAt());
        args.putBoolean("completed", todoItem.isCompleted());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_todo_item, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        todoItemViewModel = new ViewModelProvider(this).get(TodoItemViewModel.class);
        binding.setViewModel(todoItemViewModel);
        binding.setLifecycleOwner(this);

        // 이게 onCreated가 아니라 onViewCreated에 있어도 괜찮은가?
        if (getArguments() != null) {
            String content = getArguments().getString("content");
            String createdAt = getArguments().getString("createdAt");
            boolean completed = getArguments().getBoolean("completed");

            todoItemViewModel.setTodoItem(content, createdAt, completed);
        }
    }
}