package com.example.mvvm_android.util;

import android.util.Log;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.concurrent.atomic.AtomicBoolean;

public class SingleLiveEvent<T> extends MutableLiveData<T> {
    private static final String TAG = "SingleLiveEvent";
    private AtomicBoolean pending = new AtomicBoolean(false);

    /**
     * Observes the internal MutableLiveData and notifies the observer only once the value has changed.
     * This method ensures that only one observer is notified of changes.
     *
     * @param owner    the LifecycleOwner which controls the observer
     * @param observer the Observer that will receive the notifications
     */
    @MainThread
    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull final Observer<? super T> observer){
        if(hasActiveObservers()){
            Log.w(TAG, "Multiple observers registered but only one will be notified of changes");
        }

        // Observe the internal MutableLiveData
        super.observe(owner, t -> {
            if(pending.compareAndSet(true, false)){
                observer.onChanged(t);
            }
        });
    }

    /**
     * Sets the value of the internal MutableLiveData and marks the event as pending.
     * This ensures that only one observer is notified of the change.
     *
     * @param t the new value to set
     */
    @MainThread
    public void setValue(T t){
        pending.set(true);
        super.setValue(t);
    }
    /**
     * Triggers the SingleLiveEvent by setting the value to null.
     * This is a convenience method for calling setValue(null).
     */
    @MainThread
    public void call(){
        setValue(null);
    }
}
