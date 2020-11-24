package com.diljith.mvvm.livedatas;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

/**
 * Created by yadhukrishnan.e@oneteam.us
 */

public class MessageShowerLiveData extends SimpleLiveData<String> {

    public void observe(@NonNull LifecycleOwner owner, @NonNull final MessageObserver observer) {
        super.observe(owner, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                if (s == null) {
                    return;
                }
                observer.showMessage(s);
            }
        });
    }

    public interface MessageObserver {
        void showMessage(String message);
    }
}
