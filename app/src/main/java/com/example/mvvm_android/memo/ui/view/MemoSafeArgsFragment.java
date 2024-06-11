package com.example.mvvm_android.memo.ui.view;

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
import com.example.mvvm_android.memo.ui.viewModel.MemoSafeArgsViewModel;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;

public class MemoSafeArgsFragment extends Fragment {
    MemoSafeArgsViewModel memoSafeArgsViewModel;
    FragmentMemoSafeArgsBinding binding;

    private Disposable disposable;

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

        disposable = memoSafeArgsViewModel.getBackButton().observeOn(AndroidSchedulers.mainThread()).subscribe(
                aObject -> {
                    Navigation.findNavController(requireView()).popBackStack();
                },
                Throwable::printStackTrace
        );
    }

}