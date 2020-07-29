package com.je1224.animal;


import android.content.Context;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class Fragment_home extends Fragment {

    Context context;
    RecyclerView recyclerView1,recyclerView2,recyclerView3;
    HomeAdapter adapter1,adapter2,adapter3;
    ArrayList<Item> thumbnails1=new ArrayList<>();
    ArrayList<Item> thumbnails2=new ArrayList<>();
    ArrayList<Item> thumbnails3=new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        context=container.getContext();

        View view=inflater.inflate(R.layout.frag_home,container,false);

        initDataset();

        recyclerView1=view.findViewById(R.id.recycler1);
        recyclerView2=view.findViewById(R.id.recycler2);
        recyclerView3=view.findViewById(R.id.recycler3);

        adapter1=new HomeAdapter(context,thumbnails1);
        recyclerView1.setAdapter(adapter1);

        adapter2=new HomeAdapter(context,thumbnails2);
        recyclerView2.setAdapter(adapter2);

        adapter3=new HomeAdapter(context,thumbnails3);
        recyclerView3.setAdapter(adapter3);

        return view;
    }

    private void initDataset(){
        thumbnails1.clear();
        thumbnails2.clear();
        thumbnails3.clear();

        thumbnails1.add(new Item("https://img.youtube.com/vi/G2H5fEZyx_g/mqdefault.jpg",1));
        thumbnails1.add(new Item("https://img.youtube.com/vi/WWQx9HBI7_o/mqdefault.jpg",2));
        thumbnails1.add(new Item("https://img.youtube.com/vi/v_MkYvTTl7A/mqdefault.jpg",3));

        thumbnails2.add(new Item("https://img.youtube.com/vi/j5qOfxXdDf8/mqdefault.jpg",4));
        thumbnails2.add(new Item("https://img.youtube.com/vi/OdC2_-uTKuQ/mqdefault.jpg",5));
        thumbnails2.add(new Item("https://img.youtube.com/vi/d62uZHVToRU/mqdefault.jpg",6));

        thumbnails3.add(new Item("https://img.youtube.com/vi/Ot_ipiWPM6E/mqdefault.jpg",7));
        thumbnails3.add(new Item("https://img.youtube.com/vi/kT7JEO0anLA/mqdefault.jpg",8));
        thumbnails3.add(new Item("https://img.youtube.com/vi/7gW_Jd0O2BM/mqdefault.jpg",9));

    }
}
