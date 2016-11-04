package com.example.jimmyhernandez.rinnocompleto.views.rest;

/**
 * Created by Jimmy Hernandez on 27-10-2016.
 */



import com.example.jimmyhernandez.rinnocompleto.views.model.Example;
import com.example.jimmyhernandez.rinnocompleto.views.model.Forecast;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiInterface {


    //http://api.apixu.com/v1/forecast.json?key=4c9c63494dcf49a18b0194907162710&q=Santiago&days=1

    @GET("forecast.json")
    Call<Example> getInfo(@Query("key") String apiKey, @Query("q") String q, @Query("days") int days);

    /*@GET("movie/{id}")
    Call<Example> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);*/

    @GET("forecast.json")
    Call<List<Forecast>> getForecastday(@Query("key") String apiKey, @Query("q") String q, @Query("days") int days);



}