package com.je1224.animal;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitService {

    @GET("/WithAnimal/petshop.json")
    Call<ArrayList<ShopInfo>> getShopInfo();

}
