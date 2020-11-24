package com.diljith.mvvm.livedatas;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

public class IntegerLiveData extends SimpleLiveData<Integer>{

    public void observe(@NonNull LifecycleOwner owner, @NonNull final IntegerObserver observer) {
        super.observe(owner, new Observer<Integer>() {

            @Override
            public void onChanged(@Nullable Integer aBoolean) {
                if (aBoolean == null) {
                    return;
                }
                observer.checkValue(aBoolean);
            }
        });
    }

    public interface IntegerObserver {
        void checkValue(Integer value);
    }
}
