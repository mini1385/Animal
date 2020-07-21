package com.je1224.animal;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    public static Retrofit getRetrofitInstance(){
        Retrofit.Builder builder=new Retrofit.Builder();
        builder.baseUrl("http://je1224.dothome.co.kr/");
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();

        return retrofit;
    }

    public static Retrofit getRetrofitInstance_kakao(){
        Retrofit.Builder builder=new Retrofit.Builder();
        builder.baseUrl("https://dapi.kakao.com/");
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit_kakao=builder.build();

        return retrofit_kakao;
    }

}
