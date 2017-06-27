package com.jdkgroup.jkshopping.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    //private final String ROOT_URL = "http://192.168.163.2:8080/goodmorning/";
    private final String ROOT_URL = "http://192.168.43.174:25321/goodmorning/";

    private Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create();

    private Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public ApiService getApiService() {
        return getRetrofitInstance().create(ApiService.class);
    }
}
