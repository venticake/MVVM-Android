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
import com.example.mvvm_android.ui.viewModels.TodoItemViewModel;

public class TodoItemFragment extends Fragment {
    private TodoItemViewModel todoItemViewModel;
    private FragmentTodoItemBinding binding;

    public TodoItemFragment() {
        // Required empty public constructor
    }

    public static TodoItemFragment newInstance() {
        TodoItemFragment fragment = new TodoItemFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
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
    }
}