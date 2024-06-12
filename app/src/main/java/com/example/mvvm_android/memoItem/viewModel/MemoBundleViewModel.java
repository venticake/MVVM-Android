package com.example.mvvm_android.memoItem.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm_android.util.SingleLiveEvent;

/**
 * MemoBundleFragment의 ViewModel
 * content를 통해 MemoEditor에서 작성한 내용을 확인 가능
 * */
public class MemoBundleViewModel extends ViewModel {
    // 데이터 변경 접근 제어, 책임 분리, 테스트 용이성을 위해 MutableLiveData와 LiveData를 사용.
    private final MutableLiveData<String> content = new MutableLiveData<>("dummy");
    private final SingleLiveEvent<Void> backButtonEvent = new SingleLiveEvent<>();

    public LiveData<String> getContent() {
        return content;
    }

    public LiveData<Void> getBackButtonEvent() {
        return backButtonEvent;
    }

    /**
     * MemoEditorFragment에서 작성한 글 내용 동기화
     * */
    public void setContent(String content) {
        this.content.setValue(content);
    }

    /**
     * MemoEditorFragment로 되돌아간다.
     *
     * @see com.example.mvvm_android.memoEditor.MemoEditorFragment
     */
    public void handleBackButtonEvent() {
        backButtonEvent.call();
    }
}
