package com.example.mvvm_android.memoEditor;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvvm_android.R;
import com.example.mvvm_android.databinding.FragmentMemoBinding;
import com.example.mvvm_android.memoItem.view.MemoViewModelFragment;


/**
 * memo editor를 가지고 있는 fragment
 * memoItem의 fragment로 이동 역시 여기서 이뤄짐.
 */
public class MemoEditorFragment extends Fragment {

    private MemoEditorViewModel memoEditorViewModel;
    private FragmentMemoBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_memo, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // viewModel 초기화 및 databinding 세팅
        memoEditorViewModel = new ViewModelProvider(requireActivity()).get(MemoEditorViewModel.class);
        binding.setViewModel(memoEditorViewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());

        navigateMemoNavigationEvent();
    }

    /**
     * memoNavigationEvent를 observe하여 알맞은 fragment로 이동
     *
     * @see com.example.mvvm_android.memoItem.view.MemoSafeArgsFragment
     * @see com.example.mvvm_android.memoItem.view.MemoBundleFragment
     * @see MemoViewModelFragment
     */
    private void navigateMemoNavigationEvent() {
        memoEditorViewModel.getMemoNavigationEvent().observe(getViewLifecycleOwner(), label -> {
                    NavController navController = Navigation.findNavController(requireView());
                    switch (label) {
                        case "SafeArgs":
                            NavDirections action =
                                    MemoEditorFragmentDirections.actionMemoFragmentToMemoSafeArgsFragment(memoEditorViewModel.getContent().getValue());
                            navController.navigate(action);
                            break;
                        case "Bundle":
                            Bundle bundle = new Bundle();
                            bundle.putString("content", memoEditorViewModel.getContent().getValue());
                            navController.navigate(R.id.action_memoFragment_to_memoBundleFragment, bundle);
                            break;
                        case "ViewModel":
                            navController.navigate(R.id.memoVMFragment);
                            break;
                    }
                }
        );
    }
}