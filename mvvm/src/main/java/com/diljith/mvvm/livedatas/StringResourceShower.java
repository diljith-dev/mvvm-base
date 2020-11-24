package com.diljith.mvvm.livedatas;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

/**
 * Created by yadhukrishnan.e@oneteam.us
 */

public class StringResourceShower extends SimpleLiveData<Integer> {

    public void observe(@NonNull LifecycleOwner owner, @NonNull final ResourceShowerObserver observer) {
        super.observe(owner, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                if (integer == null) {
                    return;
                }

                observer.message(integer);
            }
        });
    }

    public interface ResourceShowerObserver {
        void message(@StringRes int resourceId);
    }


}
