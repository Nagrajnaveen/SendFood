package com.example.fudo.services;

//import com.example.fudo.models.Example;

import com.example.fudo.models.Example;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface RestaurantService {


    @Headers(
//            "Accept: application/json"+
            "user-key: 96a4049c890c2bdc4dc0aaf4eba975c8")
    @GET("geocode?lat=13.003523&lon=77.533007")

//    @GET("yhvic")

//    @GET("91uqq")
    Call<Example> getData();



}
