package com.example.mvvm_android.todoItem.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.mvvm_android.R;
import com.example.mvvm_android.databinding.FragmentTodoItemBinding;
import com.example.mvvm_android.todoList.ui.TodoListFragmentDirections;

/**
 * TodoListFragment 내부의 LinearLayout에 붙이는 Todo 내용이 담긴 Fragment
 */
public class TodoItemFragment extends Fragment {
    private TodoItemViewModel todoItemViewModel;
    private FragmentTodoItemBinding binding;

    public TodoItemFragment() {
        // Required empty public constructor
    }

    /**
     * Todo의 id를 기반으로 새로운 instance를 만든다.
     *
     * @param id todo의 id
     * @return 생성된 TodoItemFragment 전송
     */
    public static TodoItemFragment newInstance(Long id) {
        TodoItemFragment fragment = new TodoItemFragment();
        Bundle args = new Bundle();
        args.putLong("id", id);
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

        if (getArguments() != null) {
            Long id = getArguments().getLong("id");

            todoItemViewModel.setTodoItem(id);
        }

        binding.getRoot().setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(requireView());

            NavDirections action = TodoListFragmentDirections.actionTodoListFragmentToTodoDetailFragment(requireArguments().getLong("id"));

            navController.navigate(action);
        });

        binding.doneButton.setOnClickListener(v -> todoItemViewModel.onDoneClicked());
    }
}