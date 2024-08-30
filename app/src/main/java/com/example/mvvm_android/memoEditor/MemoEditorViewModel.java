package com.example.mvvm_android.memoEditor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * MemoEditorFragment의 ViewModel.
 * content를 통하여 TextEdit의 값과 아래 TextView 값을 동기화.
 * MemoItem의 fragment로 이동.
 */
public class MemoEditorViewModel extends ViewModel {
    // 데이터 변경 접근 제어, 책임 분리, 테스트 용이성을 위해 MutableLiveData와 LiveData를 사용.
    private final MutableLiveData<String> content = new MutableLiveData<>("LiveData Dummy");

    public LiveData<String> getContent() {
        return content;
    }

    public void setMemoContext(String content) {
        this.content.setValue(content);
    }

}