package com.example.mvvm_android.memo.ui.view;

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
import com.example.mvvm_android.memo.ui.viewModel.MemoViewModel;

import java.util.Objects;

public class MemoFragment extends Fragment {

    private MemoViewModel memoViewModel;
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
        memoViewModel = new ViewModelProvider(requireActivity()).get(MemoViewModel.class);
        binding.setViewModel(memoViewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());

        memoViewModel.getContent().observe(getViewLifecycleOwner(), content -> binding.memoTextView.setText(content));

        memoViewModel.getMoveEventLabel().observe(getViewLifecycleOwner(), label -> {
            NavController navController = Navigation.findNavController(requireView());
            switch (label){
                case "SafeArgs":
                    NavDirections action =
                            MemoFragmentDirections.actionMemoFragmentToMemoSafeArgsFragment(memoViewModel.getContent().getValue());
                    navController.navigate(action);
                    break;
                case "Bundle":
                    Bundle bundle = new Bundle();
                    bundle.putString("content", memoViewModel.getContent().getValue());
                    navController.navigate(R.id.action_memoFragment_to_memoBundleFragment, bundle);
                    break;
                case "ViewModel":
                    navController.navigate(R.id.memoVMFragment);
                    break;
            }
        });
    }
}