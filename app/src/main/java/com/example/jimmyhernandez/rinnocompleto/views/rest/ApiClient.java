package com.example.jimmyhernandez.rinnocompleto.views.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jimmy Hernandez on 27-10-2016.
 */

public class ApiClient {

    //http://api.apixu.com/v1/forecast.json?key=4c9c63494dcf49a18b0194907162710&q=Santiago&days=1


    public static final String BASE_URL = "http://api.apixu.com/v1/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}