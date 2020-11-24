package com.diljith.mvvm.livedatas;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

/**
 * Created by appsteam on 12/3/18.
 */

public class ObjectLiveData<T> extends SimpleLiveData<T> {

    public void observe(@NonNull LifecycleOwner owner, @NonNull final ObjectDataObserver<T> observer) {
        super.observe(owner, new Observer<T>() {
            @Override
            public void onChanged(@NonNull T t) {
                observer.passedObject(t);
            }
        });
    }

    @Override
    public void setValue(T t) {
        super.setValue(t);
    }


    public interface ObjectDataObserver<T> {
        void passedObject(T object);
    }

}
