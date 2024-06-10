package com.example.mvvm_android.memo.ui.view;

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
import com.example.mvvm_android.databinding.FragmentMemoBundleBinding;
import com.example.mvvm_android.memo.ui.viewModel.MemoBundleViewModel;

public class MemoBundleFragment extends Fragment {
    MemoBundleViewModel memoBundleViewModel;
    FragmentMemoBundleBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_memo_bundle, container, false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        memoBundleViewModel = new ViewModelProvider(this).get(MemoBundleViewModel.class);
        binding.setViewModel(memoBundleViewModel);
        binding.setLifecycleOwner(this);

        assert getArguments() != null;
        String content = getArguments().getString("content");

        memoBundleViewModel.setContent(content);
    }
}