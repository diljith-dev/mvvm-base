package com.diljith.mvvm.livedatas;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

/**
 * Created by yadhukrishnan.e@oneteam.us
 */

public class IntentCallerLiveData extends SimpleLiveData<Bundle> {

    public void observe(@NonNull LifecycleOwner owner, @NonNull final BundlePassObserver observer) {
        super.observe(owner, new Observer<Bundle>() {
            @Override
            public void onChanged(@Nullable Bundle bundle) {
                observer.passedDetails(bundle);
            }
        });
    }

    public interface BundlePassObserver {
        void passedDetails(Bundle bundle);
    }

}
