package com.diljith.mvvm.livedatas;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.List;

/**
 * Created by appsteam-Harish on 23/2/18.
 *
 */

public class RecyclerViewLiveData<T> extends MutableLiveData<RecyclerViewLiveData.RecyclerViewItem<T>> {

    public void observe(@NonNull LifecycleOwner owner, @NonNull final Observer<? super RecyclerViewItem<T>> observer) {
        super.observe(owner, new Observer<RecyclerViewItem<T>>() {
            @Override
            public void onChanged(@Nullable RecyclerViewItem<T> t) {
                observer.onChanged(t);
            }
        });
    }

    @Override
    public void setValue(RecyclerViewItem<T> t) {
        super.setValue(t);
    }

    public static class RecyclerViewItem<T> {
        int changeType;
        int position;
        T item;
        List<T> items;
    }
}
