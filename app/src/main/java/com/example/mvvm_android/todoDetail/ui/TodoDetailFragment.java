package com.example.mvvm_android.todoDetail.ui;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvvm_android.R;
import com.example.mvvm_android.databinding.FragmentTodoDetailBinding;
public class TodoDetailFragment extends Fragment {

    private TodoDetailViewModel viewModel;
    private FragmentTodoDetailBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_todo_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(TodoDetailViewModel.class);
        binding = FragmentTodoDetailBinding.bind(view);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setViewModel(viewModel);

        Long id = TodoDetailFragmentArgs.fromBundle(getArguments()).getId();
        viewModel.setTodo(id);

        viewModel.getBackToList().observe(getViewLifecycleOwner(), todo -> Navigation.findNavController(view).popBackStack());
    }
}