package com.je1224.animal;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface RetrofitService {

    @GET("/WithAnimal/petshop.json")
    Call<ArrayList<ShopInfo>> getShopInfo();

    @GET("v2/local/search/category.json")
    Call<MapSearch> getMapSearch(
            @Header("Authorization") String apiKey,
            @Query("x") String x,
            @Query("y") String y,
            @Query("radius") int radius
    );
}
