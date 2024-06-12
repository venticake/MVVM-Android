package com.example.mvvm_android.basicText;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mvvm_android.R;

/**
 * Navigation을 통해 fragment -> activity 전환을 구현하기 위해 만든 Activity
 * */
public class BasicTextActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_text);
    }
}