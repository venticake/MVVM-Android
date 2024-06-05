package com.example.mvvm_android.todoDetail.ui;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.mvvm_android.R;
import com.example.mvvm_android.databinding.ActivityTodoDetailBinding;

public class TodoDetailActivity extends AppCompatActivity {
    private TodoDetailViewModel todoDetailViewModel;
    private ActivityTodoDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_detail);
        todoDetailViewModel = new ViewModelProvider(this).get(TodoDetailViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_todo_detail);
        binding.setViewModel(todoDetailViewModel);
        binding.setLifecycleOwner(this);

        if(getIntent().hasExtra("content")){
            String content = getIntent().getStringExtra("content");

            todoDetailViewModel.setContent(content);
        }
    }
}