package com.example.mvvm_android.app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.mvvm_android.R;
import com.example.mvvm_android.databinding.ActivityMainBinding;

import java.util.Objects;


/**
 * Application의 메인 화면을 담당하는 Activity 클래스
 * Navigation을 사용하여 화면 전환을 관리
 */
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initNavigation();
    }

    /**
     * 안드로이드 화면 이동을 위한 Navigation 초기화 : NavHostFragment
     * navController를 통한 bottomNavigationView 초기화
     *
     * @throws NullPointerException : navHostFragment의 NavController가 null일 경우
     */
    private void initNavigation() {
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = Objects.requireNonNull(navHostFragment).getNavController();

        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);
    }
}