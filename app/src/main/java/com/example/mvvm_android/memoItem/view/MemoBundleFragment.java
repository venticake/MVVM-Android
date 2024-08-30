package com.example.mvvm_android.memoItem.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvvm_android.R;
import com.example.mvvm_android.databinding.FragmentMemoBundleBinding;
import com.example.mvvm_android.memoItem.viewModel.MemoBundleViewModel;
import com.example.mvvm_android.memoItem.viewModel.MemoSafeArgsViewModel;

/**
 * MemoEditorFragment에서 작성한 내용을 표현하는 fragment
 * Navigation을 통한 이동 시 데이터를 Bundle에 담아 전송.
 */
public class MemoBundleFragment extends Fragment {
    MemoBundleViewModel memoBundleViewModel;
    FragmentMemoBundleBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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

        String content = getArguments() != null ? getArguments().getString("content") : "dummyData";

        memoBundleViewModel.setContent(content);

        /**
         * MemoEditorViewModel의 backButtonEvent를 통하여 MemoEditorFragment로 되돌아가는 메서드
         *
         * @see MemoSafeArgsViewModel#getBackButtonEvent()
         */
        binding.backToMemo.setOnClickListener(v -> Navigation.findNavController(requireView()).popBackStack());
    }
}