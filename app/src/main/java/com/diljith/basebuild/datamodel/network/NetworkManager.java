package com.diljith.basebuild.datamodel.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


import com.diljith.mvvm.utiles.PreferenceController;
import com.diljith.mvvm.livedatas.ApiResponseLiveData;
import com.diljith.mvvm.livedatas.ApiResponseLiveDataWithErrorCode;

import org.json.JSONObject;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class NetworkManager {

    private static final String live_URL = "https://api.carrioo.com/api/"; // live
    private static final String dev_URL = "https://devapi.carrioo.com/api/"; // Dev
    // private static final String dev_URL = "http://167.114.36.64:3000/api/"; // base url

    private   String BASE_URL;
    public static boolean isLiveBaseURL =false;

    public static final int CONNECTION_PROBLEM = -1;
    public static final String NO_INTERNET_CONNECTION = "No Internet Connection";
    private static final String SERVER_NOT_REACHABLE = "Server not reachable, please try again later.";
    private static NetworkManager mManager;
    private ApiPrototype mApiInterface;

    private String result;
    private int mToken;

    private NetworkManager() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(3, TimeUnit.MINUTES)
                .connectTimeout(3, TimeUnit.MINUTES)
                .writeTimeout(3,TimeUnit.MINUTES)
                .addInterceptor(logging)
                .build();
        if(isLiveBaseURL) {
            BASE_URL=live_URL;
        }
        else {
            BASE_URL=dev_URL;
        }

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).
                addConverterFactory(new NullOnEmptyConverterFactory()).
                addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient).build();
        mApiInterface = retrofit.create(ApiPrototype.class);
    }

    public static NetworkManager getInstance() {
        if (mManager == null) {
            mManager = new NetworkManager();
        }
        return mManager;
    }





    private <T> void successResponseWithErrorCode(ApiResponseLiveDataWithErrorCode<T> responseLiveData, Response<T> response) {
        com.diljith.mvvm.livedatas.Response<T> res = new com.diljith.mvvm.livedatas.Response<>();
        res.body = response.body();
        res.status = response.code();
        res.success = res.body != null;

        //res.message=response.message();
        JSONObject obj = null;

        try {
            if (!res.success) {

                obj = new JSONObject(response.errorBody().string());
                if(obj!=null) {
                    if (obj.has("Error")) {
                        res.errorBody = obj.getString("Error");
                    }

                    if (obj.has("message")) {
                        res.message = obj.getString("message");
                    }
                    if (res.message == null) {
                        res.message = "";
                    }


                    JSONObject OutPutParameters = null;

                    if (obj.has("OutPutParameters")) {
                        OutPutParameters = obj.getJSONObject("OutPutParameters");
                        res.OuterParamErrorMessage = OutPutParameters.getString("ErrorMessage");
                    }

                }

                try {
                    res.rowMessage = response.message()+"";

                    res.errorCode = response.code();
                }catch (Exception e)
                {
                    res.rowMessage="";
                    e.getMessage();
                }



            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        responseLiveData.postValue(res);
    }
    private <T> void failureResponseWithErrorCode(Context context, ApiResponseLiveDataWithErrorCode<T> responseLiveData, Throwable t) {
        com.diljith.mvvm.livedatas.Response<T> res = new com.diljith.mvvm.livedatas.Response<>();
        res.body = null;
        res.success = false;
        if (checkInternetConnectivity(context) ) {
            res.errorBody = NO_INTERNET_CONNECTION;
            res.status = CONNECTION_PROBLEM;
        } else if (t instanceof SocketTimeoutException || t instanceof UnknownHostException) {
            res.errorBody = NO_INTERNET_CONNECTION;
            res.status = CONNECTION_PROBLEM;
        }
        else if (t instanceof ConnectException)
        {
            res.errorBody = SERVER_NOT_REACHABLE;
            res.status = CONNECTION_PROBLEM;
        }
        else {
            res.status = 0;
            res.errorBody = t.toString();
        }
        responseLiveData.postValue(res);
    }
    private <T> void successResponse(ApiResponseLiveData<T> responseLiveData, Response<T> response) {
        com.diljith.mvvm.livedatas.Response<T> res = new com.diljith.mvvm.livedatas.Response<>();
        res.body = response.body();
        res.status = response.code();
        res.success = res.body != null;

        //res.message=response.message();
        JSONObject obj = null;

        try {
            if (!res.success) {

                obj = new JSONObject(response.errorBody().string());
                if(obj!=null) {
                    if (obj.has("Error")) {
                        res.errorBody = obj.getString("Error");
                    }

                    if (obj.has("message")) {
                        res.message = obj.getString("message");
                    }
                    if (res.message == null) {
                        res.message = "";
                    }


                    JSONObject OutPutParameters = null;

                    if (obj.has("OutPutParameters")) {
                        OutPutParameters = obj.getJSONObject("OutPutParameters");
                        res.OuterParamErrorMessage = OutPutParameters.getString("ErrorMessage");
                    }

                }

                try {
                    res.rowMessage = response.message()+"";

                    res.errorCode = response.code();
                }catch (Exception e)
                {
                    res.rowMessage="";
                    e.getMessage();
                }



            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        responseLiveData.postValue(res);
    }
    private <T> void failureResponse(Context context, ApiResponseLiveData<T> responseLiveData, Throwable t) {
        com.diljith.mvvm.livedatas.Response<T> res = new com.diljith.mvvm.livedatas.Response<>();
        res.body = null;
        res.success = false;
        if (checkInternetConnectivity(context) ) {
            res.errorBody = NO_INTERNET_CONNECTION;
            res.status = CONNECTION_PROBLEM;
        } else if (t instanceof SocketTimeoutException || t instanceof UnknownHostException) {
            res.errorBody = NO_INTERNET_CONNECTION;
            res.status = CONNECTION_PROBLEM;
        }
        else if (t instanceof ConnectException)
        {
            res.errorBody = SERVER_NOT_REACHABLE;
            res.status = CONNECTION_PROBLEM;
        }
        else {
            res.status = 0;
            res.errorBody = t.toString();
        }
        responseLiveData.postValue(res);
    }
    private boolean checkInternetConnectivity(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnected();
        }
        return false;
    }

/*  Leave Summery details Api call*/


    private Map<String, String> formHeader(String token,Context context) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        if (token != null)
            headers.put("Authorization", "Bearer " + token);
        headers.put("Accept-Language", PreferenceController.getInstance().getKeyString(context,"LANGUAGE"));
        return headers;
    }







}


