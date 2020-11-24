package com.diljith.mvvm.livedatas;

import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;


import com.diljith.mvvm.utiles.LibraryUtil;

import static com.diljith.mvvm.livedatas.ApiResponseLiveData.CONNECTION_PROBLEM;


/**
 * Created by dhiljith.devadas@oneteamus.com
 */

public class ApiResponseLiveDataWithErrorCode<T>  extends BinLiveData<T> {

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
                    }else if(response.errorCode==400)
                    {
                        observer. showErrorCodeMessage(response.message,response.errorCode);
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
        void showErrorCodeMessage(String message, int errorCode);
        void showLogoutAlert(String message);
        void showFailureResponse(Response<T> response);
    }
}
