package com.je1224.animal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyPetActivity extends AppCompatActivity {

    ListView lv;
    CircleImageView cv;
    TextView tvName, tvS, tvBirth;
    RadioGroup rg;
    RadioButton rb_b,rb_g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pet);

        lv=findViewById(R.id.lv);
        cv=findViewById(R.id.cv);
        tvName=findViewById(R.id.tv_name);
        tvS=findViewById(R.id.tv_s);
        tvBirth=findViewById(R.id.tv_birth);
        rg=findViewById(R.id.rg);
        rb_b=findViewById(R.id.rb_b);
        rb_g=findViewById(R.id.rb_g);

        Toolbar tb=findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void addPet(View view) {

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        LayoutInflater inflater=this.getLayoutInflater();
        View v=inflater.inflate(R.layout.petlist_dialog,null);

        builder.setView(v);

        AlertDialog dialog=builder.create();
        dialog.setCancelable(false);
        dialog.show();

    }
}
