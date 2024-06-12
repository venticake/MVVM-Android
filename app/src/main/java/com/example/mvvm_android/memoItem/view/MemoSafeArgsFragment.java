package com.example.mvvm_android.memoItem.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvvm_android.R;
import com.example.mvvm_android.databinding.FragmentMemoSafeArgsBinding;
import com.example.mvvm_android.memoItem.viewModel.MemoSafeArgsViewModel;

/**
 * MemoEditorFragment에서 작성한 내용을 표현하는 fragment
 * Navigation을 통한 이동 시 데이터를 SafeArgs로 생성된 메서드에 담아 전송.
 */
public class MemoSafeArgsFragment extends Fragment {
    MemoSafeArgsViewModel memoSafeArgsViewModel;
    FragmentMemoSafeArgsBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_memo_safe_args, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        memoSafeArgsViewModel = new ViewModelProvider(this).get(MemoSafeArgsViewModel.class);
        binding.setViewModel(memoSafeArgsViewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());

        String content = MemoSafeArgsFragmentArgs.fromBundle(this.getArguments()).getContent();
        memoSafeArgsViewModel.setContent(content);

        navigateBackButtonEvent();
    }

    /**
     * MemoSafeArgsViewModel의 backButtonEvent를 통하여 MemoEditorFragment로 되돌아가는 메서드
     *
     * @see MemoSafeArgsViewModel#getBackButtonEvent()
     */
    private void navigateBackButtonEvent() {
        memoSafeArgsViewModel.getBackButtonEvent().observe(getViewLifecycleOwner(), observer ->
                Navigation.findNavController(requireView()).popBackStack()
        );
    }
}