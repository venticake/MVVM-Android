package com.example.mvvm_android.memo.ui.view;

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
import com.example.mvvm_android.memo.ui.viewModel.MemoViewModel;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;

public class MemoVMFragment extends Fragment {
    MemoViewModel memoViewModel;
    FragmentMemoVMBinding binding;

    private Disposable disposable;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_memo_v_m, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        memoViewModel = new ViewModelProvider(requireActivity()).get(MemoViewModel.class);
        binding.setViewModel(memoViewModel);
        binding.setLifecycleOwner(this);

        disposable = memoViewModel.getBackButton().observeOn(AndroidSchedulers.mainThread()).subscribe(
                aObject -> Navigation.findNavController(requireView()).popBackStack(),
                Throwable::printStackTrace
        );
    }

    @Override
    public void onStop() {
        super.onStop();
        disposable.dispose();
    }
}