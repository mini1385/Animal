package com.je1224.animal;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_shop extends Fragment {

    ListView lv;
    ShopAdapter adapter;
    ArrayList<ShopInfo> shops=new ArrayList<>();
    String loadTitle,loadContents,loadUrl;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frag_shop,container,false);

        lv=view.findViewById(R.id.lv);

        RetrofitService retrofitService=RetrofitHelper.getRetrofitInstance().create(RetrofitService.class);
        Call<ArrayList<ShopInfo>> call=retrofitService.getShopInfo();
        call.enqueue((new Callback<ArrayList<ShopInfo>>() {
            @Override
            public void onResponse(Call<ArrayList<ShopInfo>> call, Response<ArrayList<ShopInfo>> response) {
                if(response.isSuccessful()){
                    ArrayList<ShopInfo> items=response.body();
                    for(ShopInfo item:items){
                        loadTitle=item.title;
                        loadContents=item.contents;
                        loadUrl=item.url;

                        shops.add(new ShopInfo(loadTitle,loadContents,loadUrl));
                        adapter.notifyDataSetChanged();

                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ShopInfo>> call, Throwable t) {

            }
        }));

        adapter=new ShopAdapter(shops,getActivity());
        lv.setAdapter(adapter);

        return view;
    }
}
