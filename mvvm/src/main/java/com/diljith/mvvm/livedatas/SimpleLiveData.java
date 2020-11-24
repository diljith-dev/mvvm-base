package com.diljith.mvvm.livedatas;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

/**
 * Created by yadhukrishnan.e@oneteam.us
 */

public class SimpleLiveData<T> extends MutableLiveData<T> {

    @MainThread
    public void observe(@NonNull LifecycleOwner owner, @NonNull final Observer<? super T> observer) {
        super.observe(owner, new Observer<T>() {
            @Override
            public void onChanged(@Nullable T t) {
                observer.onChanged(t);
            }
        });
    }

    @MainThread
    public void setValue(T t) {
        super.setValue(t);
    }
}
