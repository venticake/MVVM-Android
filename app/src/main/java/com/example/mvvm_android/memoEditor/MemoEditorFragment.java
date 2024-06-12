/**
 * 하단 BottomNavBar를 통해 Fragment -> Fragment 이동
 * ViewModel에 RxJava 적용
 * Navigation을 통해 이동 (Bundle, SafeArgs, ViewModel 공유로 데이터 전달)
* */

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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvvm_android.R;
import com.example.mvvm_android.databinding.FragmentMemoBinding;

import java.util.Objects;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;

public class MemoEditorFragment extends Fragment {

    private MemoEditorViewModel memoEditorViewModel;
    private FragmentMemoBinding binding;

    // RxJava의 Observable을 구독하기 위해 사용. fragment의 lifeCycle에 맞춰 종료하기 위해 선언.
    private Disposable disposable;

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

        // 다른 fragment로 이동하기 위해 RxJava를 사용
        disposable = memoEditorViewModel.getMoveEventLabel().observeOn(AndroidSchedulers.mainThread()).subscribe(
                label -> {
                    NavController navController = Navigation.findNavController(requireView());
                    switch (label){
                        case "SafeArgs":
                            NavDirections action =
                                    MemoEditorFragmentDirections.actionMemoFragmentToMemoSafeArgsFragment(memoEditorViewModel.getContent().get());
                            navController.navigate(action);
                            break;
                        case "Bundle":
                            Bundle bundle = new Bundle();
                            bundle.putString("content", memoEditorViewModel.getContent().get());
                            navController.navigate(R.id.action_memoFragment_to_memoBundleFragment, bundle);
                            break;
                        case "ViewModel":
                            navController.navigate(R.id.memoVMFragment);
                            break;
                    }
                },
                error -> Log.e("MemoFragment", Objects.requireNonNull(error.getMessage()))
        );
    }

    @Override
    public void onStop() {
        super.onStop();
        disposable.dispose();
    }
}