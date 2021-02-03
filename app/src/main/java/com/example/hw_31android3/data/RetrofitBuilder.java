package com.example.hw_31android3.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {
    private RetrofitBuilder() {
    }

    private static AndroidApi instance;

    public static AndroidApi getInstance() {
        if (instance == null) {
            instance = createService();
        }
        return instance;
    }

    private static AndroidApi createService() {
    return new Retrofit.Builder()
            .baseUrl("http://android-3-mocker.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AndroidApi.class);
    }
}
