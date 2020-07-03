package com.je1224.animal;


import android.content.Context;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


public class Fragment_home extends Fragment {

    Context context;
    RecyclerView recyclerView1,recyclerView2,recyclerView3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        context=container.getContext();

        View view=inflater.inflate(R.layout.frag_home,container,false);
        recyclerView1=view.findViewById(R.id.recycler1);
        recyclerView2=view.findViewById(R.id.recycler2);
        recyclerView3=view.findViewById(R.id.recycler3);

        return view;
    }

}
