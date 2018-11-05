package com.example.fudo.services;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceBuilder {


    private static final String BASE_URL="https://developers.zomato.com/api/v2.1/";
//private static final String BASE_URL="https://api.myjson.com/bins/";


    private static Retrofit.Builder builder= new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create());
    private static Retrofit retrofit = builder.build();
    public static <S> S buildService(Class<S> serviceType){
        return retrofit.create(serviceType);
    }

}
