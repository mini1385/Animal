package com.je1224.animal;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitHelper {

    public static Retrofit getRetrofitInstance(){
        Gson gson=new GsonBuilder().setLenient().create();

        Retrofit.Builder builder=new Retrofit.Builder();
        builder.baseUrl("http://je1224.dothome.co.kr");
        builder.addConverterFactory(GsonConverterFactory.create(gson));
        return builder.build();
    }

    public static Retrofit getRetrofitInstance2(){
        Retrofit.Builder builder=new Retrofit.Builder();
        builder.baseUrl("http://je1224.dothome.co.kr");
        builder.addConverterFactory(ScalarsConverterFactory.create());
        return builder.build();
    }

}
