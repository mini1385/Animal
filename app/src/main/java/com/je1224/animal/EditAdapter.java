package com.je1224.animal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class EditAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<EditItem> editItems;

    public EditAdapter(Context context, ArrayList<EditItem> editItems) {
        this.context = context;
        this.editItems = editItems;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(context).inflate(R.layout.photo_item,parent,false);
        VH holder=new VH(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh=(VH)holder;
        EditItem item=editItems.get(position);

        String imgUrl="http://je1224.dothome.co.kr/WithAnimal/"+item.file;
        Glide.with(context).load(imgUrl).into(vh.iv);
        vh.tv.setText(item.msg);
    }

    @Override
    public int getItemCount() {
        return editItems.size();
    }

    class VH extends RecyclerView.ViewHolder {

        ImageView iv;
        TextView tv;

        public VH(@NonNull View itemView) {
            super(itemView);

            iv=itemView.findViewById(R.id.photo_iv);
            tv=itemView.findViewById(R.id.photo_tv);

        }
    }
}
