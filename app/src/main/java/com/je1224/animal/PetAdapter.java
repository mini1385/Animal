package com.je1224.animal;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

class PetInfo{
    String name;
    String gender;
    String birth;
    Uri img;

    public PetInfo() {
    }

    public PetInfo(String name, String gender, String birth, Uri img) {
        this.name = name;
        this.gender = gender;
        this.birth = birth;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public Uri getImg() {
        return img;
    }

    public void setImg(Uri img) {
        this.img = img;
    }
}

public class PetAdapter extends BaseAdapter {

    ArrayList<PetInfo> items;
    Context context;


    public PetAdapter(ArrayList<PetInfo> items, Context context) {
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
            convertView=inflater.inflate(R.layout.petlist_item,parent,false);

            ViewHolder holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        ViewHolder holder=(ViewHolder) convertView.getTag();
        holder.cv.setImageURI(items.get(position).getImg());
        holder.tvName.setText(items.get(position).getName());
        holder.tvGender.setText(items.get(position).getGender());
        holder.tvBirth.setText(items.get(position).getBirth());

        return convertView;
    }

    class ViewHolder{
        CircleImageView cv;
        TextView tvName;
        TextView tvGender;
        TextView tvBirth;

        public ViewHolder(View itemView) {
            cv=itemView.findViewById(R.id.cv);
            tvName=itemView.findViewById(R.id.tv_name);
            tvGender=itemView.findViewById(R.id.tv_gender);
            tvBirth=itemView.findViewById(R.id.tv_birth);
        }
    }
}
