package com.je1224.animal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class ShopAdapter extends BaseAdapter {

    ArrayList<ShopInfo> items;
    Context context;


    public ShopAdapter(ArrayList<ShopInfo> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public int getCount() { return items.size(); }

    @Override
    public Object getItem(int position) { return items.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            LayoutInflater inflater=LayoutInflater.from(context);
            convertView=inflater.inflate(R.layout.shop_item,parent,false);

            ShopAdapter.ViewHolder holder=new ShopAdapter.ViewHolder(convertView);
            convertView.setTag(holder);
        }

        ShopAdapter.ViewHolder holder=(ShopAdapter.ViewHolder) convertView.getTag();
        holder.tvTitle.setText(items.get(position).getTitle());
        holder.tvContents.setText(items.get(position).getContents());
        holder.tvUrl.setText(items.get(position).getUrl());

        return convertView;
    }

    class ViewHolder{
        TextView tvTitle;
        TextView tvContents;
        TextView tvUrl;

        public ViewHolder(View itemView) {
            tvTitle=itemView.findViewById(R.id.tv_title);
            tvContents=itemView.findViewById(R.id.tv_contents);
            tvUrl=itemView.findViewById(R.id.tv_url);
        }
    }

}
