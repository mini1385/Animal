package com.je1224.animal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Fragment_photo extends Fragment {

    Context context;
    RecyclerView recyclerView;
    EditAdapter adapter;
    ArrayList<EditItem> editItems=new ArrayList<>();
    FloatingActionButton fb;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        context=container.getContext();
        View view=inflater.inflate(R.layout.frag_photo,container,false);

        recyclerView=view.findViewById(R.id.photo_rv);
        adapter=new EditAdapter(context,editItems);
        recyclerView.setAdapter(adapter);

        GridLayoutManager gridLayoutManager=new GridLayoutManager(context,2);
        recyclerView.setLayoutManager(gridLayoutManager);

        RecyclerDecoration spaceDecoration = new RecyclerDecoration(2,16,true);
        recyclerView.addItemDecoration(spaceDecoration);

        fb=view.findViewById(R.id.fb);

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,EditActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        loadData();
    }

    void loadData(){
        Retrofit retrofit=RetrofitHelper.getRetrofitInstance();
        RetrofitService retrofitService=retrofit.create(RetrofitService.class);
        Call<ArrayList<EditItem>> call=retrofitService.loadPhoto();
        call.enqueue(new Callback<ArrayList<EditItem>>() {
            @Override
            public void onResponse(Call<ArrayList<EditItem>> call, Response<ArrayList<EditItem>> response) {
                ArrayList<EditItem> items=response.body();
                editItems.clear();
                adapter.notifyDataSetChanged();

                for(EditItem item:items){
                    editItems.add(0,item);
                    adapter.notifyItemInserted(0);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<EditItem>> call, Throwable t) {

            }
        });
    }
}
