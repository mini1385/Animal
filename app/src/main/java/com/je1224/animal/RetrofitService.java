package com.je1224.animal;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;


public interface RetrofitService {

    @GET("/WithAnimal/petshop.json")
    Call<ArrayList<ShopInfo>> getShopInfo();

    @Multipart
    @POST("/WithAnimal/insertDB.php")
    Call<String> postPhoto(@PartMap Map<String, String> dataPart, @Part MultipartBody.Part filePart);

    @GET("/WithAnimal/loadDB.php")
    Call<ArrayList<EditItem>> loadPhoto();

}
