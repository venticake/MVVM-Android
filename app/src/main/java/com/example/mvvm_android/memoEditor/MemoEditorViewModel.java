package com.example.mvvm_android.memoEditor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.mvvm_android.util.SingleLiveEvent;

/**
 * MemoEditorFragment의 ViewModel.
 * content를 통하여 TextEdit의 값과 아래 TextView 값을 동기화.
 * MemoItem의 fragment로 이동.
 */
public class MemoEditorViewModel extends ViewModel {
    // 데이터 변경 접근 제어, 책임 분리, 테스트 용이성을 위해 MutableLiveData와 LiveData를 사용.
    private final MutableLiveData<String> content = new MutableLiveData<>("LiveData Dummy");

    private final SingleLiveEvent<String> memoNavigationEvent = new SingleLiveEvent<>();

    private final SingleLiveEvent<Void> backButtonEvent = new SingleLiveEvent<>();

    public LiveData<String> getMemoNavigationEvent() {
        return memoNavigationEvent;
    }

    public LiveData<String> getContent() {
        return content;
    }

    public LiveData<Void> getBackButtonEvent() {
        return backButtonEvent;
    }

    /**
     * EditText와 TextView 값을 같게 한다.
     *
     * @param text: EditText에서 변화한 값
     */
    public void onTextChanged(CharSequence text) {
        content.setValue(text.toString());
    }

    /**
     * MemoItem.memoSafeArgsFragment로 이동.
     *
     * @see com.example.mvvm_android.memoItem.view.MemoSafeArgsFragment
     */
    public void moveMemoSafeArgs() {
        memoNavigationEvent.setValue("SafeArgs");
    }

    /**
     * MemoItem.memoBundleFragment로 이동.
     *
     * @see com.example.mvvm_android.memoItem.view.MemoBundleFragment
     */
    public void moveMemoBundle() {
        memoNavigationEvent.setValue("Bundle");
    }

    /**
     * MemoItem.memoViewModelFragment로 이동.
     *
     * @see com.example.mvvm_android.memoItem.view.MemoSafeArgsFragment
     */
    public void moveMemoViewModel() {
        memoNavigationEvent.setValue("ViewModel");
    }

    /**
     * MemoItem.memoViewModelFragment의 backButtonEvent를 처리하기 위해 사용.
     * viewModel 공유를 위해 작성.
     */
    public void handleBackButtonEvent() {
        backButtonEvent.call();
    }
}