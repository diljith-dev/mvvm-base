package com.diljith.mvvm.livedatas;

import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;


import com.diljith.mvvm.utiles.LibraryUtil;



public class ApiResponseLiveData<T>  extends BinLiveData<T> {

    public static final int CONNECTION_PROBLEM = -1;




    public void observe(LifecycleOwner owner, final ApiObserver<T> observer) {
        super.observe(owner, new Observer<Response<T>>() {
            @Override
            public void onChanged(@Nullable Response<T> response) {
                if (response == null) {
                    return;
                }
                if (response.success) {
//                    if (response.result != null)
//                    {
//                        if (response.result.equalsIgnoreCase(Constants.FAILURE) && response.token == 1)
//                            observer.showLogoutAlert(response.body);
//                        else
                            observer.showSuccessResponse(response.body);
                   // }
                }
                else {
                    if (response.status == CONNECTION_PROBLEM) {
                        observer.showConnectionError(response.errorBody);
                    } else if(response.errorCode==401)
                    {
                        observer. showLogoutAlert(response.message);
                    }

                    else {
                        observer.showFailureResponse(LibraryUtil.ApiResponseParser(response));
                    }
                }
            }
        });
    }

    public interface ApiObserver<T> {
        void showSuccessResponse(T response);
        void showFailureResponse(String response);
        void showConnectionError(String message);
        void showLogoutAlert(String message);
        void showFailureResponse(Response<T> response);
    }
}
