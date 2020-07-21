package com.je1224.animal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Fragment_photo extends Fragment {

    Context context;
    RecyclerView recyclerView;
    FloatingActionButton fb;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        context=container.getContext();

        View view=inflater.inflate(R.layout.frag_photo,container,false);
        recyclerView=view.findViewById(R.id.photo_rv);

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
}
