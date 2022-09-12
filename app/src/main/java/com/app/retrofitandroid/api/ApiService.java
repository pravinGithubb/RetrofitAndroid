package com.app.retrofitandroid.api;

import com.app.retrofitandroid.api.callback.CallbackResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("users?page=2")
    Call<CallbackResponse> getUserList();

}
