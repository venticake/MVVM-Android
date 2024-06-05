package com.example.mvvm_android.todoList.ui.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mvvm_android.R;
import com.example.mvvm_android.todoDetail.ui.TodoDetailActivity;
import com.example.mvvm_android.todoList.ui.viewModels.TodoItemViewModel;
import com.example.mvvm_android.databinding.FragmentTodoItemBinding;
import com.example.mvvm_android.todoList.domain.models.Todo;

public class TodoItemFragment extends Fragment {
    private TodoItemViewModel todoItemViewModel;
    private FragmentTodoItemBinding binding;

    public TodoItemFragment() {
        // Required empty public constructor
    }

    public static TodoItemFragment newInstance(Todo todo) {
        TodoItemFragment fragment = new TodoItemFragment();
        Bundle args = new Bundle();
        // 이걸 나눠서 담아..?
        args.putString("content", todo.getContent());
        args.putString("createdAt", todo.getCreatedAt());
        args.putBoolean("completed", todo.isCompleted());
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
        binding.setLifecycleOwner(getViewLifecycleOwner());

        // 이게 onCreated가 아니라 onViewCreated에 있어도 괜찮은가?
        if (getArguments() != null) {
            String content = getArguments().getString("content");
            String createdAt = getArguments().getString("createdAt");
            boolean completed = getArguments().getBoolean("completed");

            todoItemViewModel.setTodoItem(content, createdAt, completed);
        }

        // viewModel의 startTodoDetailActivity observe
        todoItemViewModel.getStartTodoDetailActivity().observe(getViewLifecycleOwner(), unused -> {
            Intent intent = new Intent(getActivity(), TodoDetailActivity.class);
            intent.putExtra("content", todoItemViewModel.getContent().getValue());
            startActivity(intent);
        });
    }
}