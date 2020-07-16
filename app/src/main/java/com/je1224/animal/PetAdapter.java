package com.je1224.animal;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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

public class PetAdapter extends ArrayAdapter<PetInfo> {
    LayoutInflater inflater;

    public PetAdapter(Context context, int resourceID, List<PetInfo> petInfoList) {
        super(context,resourceID,petInfoList);
        inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        PetInfo petInfo=(PetInfo)getItem(position);

        if(convertView==null){
            convertView=inflater.inflate(R.layout.petlist_item,null);
        }

        CircleImageView cv=convertView.findViewById(R.id.cv);
        TextView tvName=convertView.findViewById(R.id.tv_name);
        TextView tvGender=convertView.findViewById(R.id.tv_gender);
        TextView tvBirth=convertView.findViewById(R.id.tv_birth);

        cv.setImageURI(petInfo.img);
        tvName.setText(petInfo.name);
        tvGender.setText(petInfo.gender);
        tvBirth.setText(petInfo.birth);

        return convertView;
    }
}
