package com.je1224.animal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<Item> thumbnails;

    public HomeAdapter(Context context, ArrayList<Item> thumbnails) {
        this.context = context;
        this.thumbnails = thumbnails;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View itemView= inflater.inflate(R.layout.home_item, parent, false);

        VH holder= new VH(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh= (VH)holder;
        Item item=thumbnails.get(position);
        Glide.with(context).load(item.imgURL).into(vh.thumbnail);
    }

    @Override
    public int getItemCount() {
        return thumbnails.size();
    }

    class VH extends RecyclerView.ViewHolder {
        ImageView thumbnail;

        public VH(@NonNull View itemView) {
            super(itemView);
            thumbnail= itemView.findViewById(R.id.thumbnail);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Item item=thumbnails.get(getLayoutPosition());
                    switch(item.tag){
                        case 1:
                            Intent i1 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=G2H5fEZyx_g"));
                            context.startActivity(i1);
                            break;
                        case 2:
                            Intent i2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=WWQx9HBI7_o"));
                            context.startActivity(i2);
                            break;
                        case 3:
                            Intent i3 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=v_MkYvTTl7A"));
                            context.startActivity(i3);
                            break;
                        case 4:
                            Intent i4 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=j5qOfxXdDf8"));
                            context.startActivity(i4);
                            break;
                        case 5:
                            Intent i5 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=OdC2_-uTKuQ"));
                            context.startActivity(i5);
                            break;
                        case 6:
                            Intent i6 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=d62uZHVToRU"));
                            context.startActivity(i6);
                            break;
                        case 7:
                            Intent i7 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=Ot_ipiWPM6E"));
                            context.startActivity(i7);
                            break;
                        case 8:
                            Intent i8 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=kT7JEO0anLA"));
                            context.startActivity(i8);
                            break;
                        case 9:
                            Intent i9 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=7gW_Jd0O2BM"));
                            context.startActivity(i9);
                            break;
                    }
                }
            });
        }
    }
}
