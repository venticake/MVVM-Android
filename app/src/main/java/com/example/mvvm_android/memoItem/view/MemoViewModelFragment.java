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
import com.example.mvvm_android.databinding.FragmentMemoVMBinding;
import com.example.mvvm_android.memoEditor.MemoEditorViewModel;
import com.example.mvvm_android.memoItem.viewModel.MemoSafeArgsViewModel;

/**
 * MemoEditorFragment에서 작성한 내용을 표현하는 fragment
 * MemoEditorViewModel을 공유하여 데이터 사용.
 */
public class MemoViewModelFragment extends Fragment {
    MemoEditorViewModel memoEditorViewModel;
    FragmentMemoVMBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_memo_v_m, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        memoEditorViewModel = new ViewModelProvider(requireActivity()).get(MemoEditorViewModel.class);
        binding.setViewModel(memoEditorViewModel);
        binding.setLifecycleOwner(this);

        navigateBackButtonEvent();
    }

    /**
     * MemoEditorViewModel의 backButtonEvent를 통하여 MemoEditorFragment로 되돌아가는 메서드
     *
     * @see MemoSafeArgsViewModel#getBackButtonEvent()
     */
    private void navigateBackButtonEvent() {
        memoEditorViewModel.getBackButtonEvent().observe(getViewLifecycleOwner(), memo ->
                Navigation.findNavController(requireView()).popBackStack()
        );
    }
}