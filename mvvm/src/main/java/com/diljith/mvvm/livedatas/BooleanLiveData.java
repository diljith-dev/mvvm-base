package com.diljith.mvvm.livedatas;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

/**
 * Created by melvin.john@oneteam.us
 */

public class BooleanLiveData extends SimpleLiveData<Boolean> {

    public void observe(@NonNull LifecycleOwner owner, @NonNull final BooleanObserver observer) {
        super.observe(owner, new Observer<Boolean>() {

            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean == null) {
                    return;
                }
                observer.checkValue(aBoolean);
            }
        });
    }

    public interface BooleanObserver {
        void checkValue(Boolean value);
    }
}
