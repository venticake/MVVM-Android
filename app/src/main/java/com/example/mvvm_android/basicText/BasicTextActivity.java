/*
 * Navigation을 이용하여 Fragment -> Activity 전환을 구현하기 위해 만든 Activity.
 * */

package com.example.mvvm_android.basicText;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mvvm_android.R;

public class BasicTextActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_text);
    }
}