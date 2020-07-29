package com.je1224.animal;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyPetActivity extends AppCompatActivity {

    ListView lv;
    String name,gender,birth;
    String loadName,loadGender,loadBirth;
    Uri loadImg;
    ArrayList<PetInfo> items=new ArrayList<>();
    PetAdapter adapter;

    TextView tvNoitem;

    CircleImageView cv;
    TextView tvName, tvGender, tvBirth;

    RadioGroup rg;
    RadioButton rb_b,rb_g;
    EditText etName, etBirth;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pet);

        lv=findViewById(R.id.lv);
        cv=findViewById(R.id.cv);
        tvName=findViewById(R.id.tv_name);
        tvGender=findViewById(R.id.tv_gender);
        tvBirth=findViewById(R.id.tv_birth);
        tvNoitem=findViewById(R.id.tv_noitem);

        Toolbar tb=findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        actionBar.setDisplayHomeAsUpEnabled(true);

        String[] permissions=new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if(checkSelfPermission(permissions[0])== PackageManager.PERMISSION_DENIED){
                requestPermissions(permissions,100);
            }
        }
        adapter=new PetAdapter(items,this);
        lv.setAdapter(adapter);

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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==100 && grantResults[0]==PackageManager.PERMISSION_DENIED){
            Toast.makeText(this, "업로드 불가", Toast.LENGTH_SHORT).show();
        }
    }

    public void addPet(View view) {

        AlertDialog.Builder builder=new AlertDialog.Builder(this,R.style.dialog_theme);
        LayoutInflater inflater=this.getLayoutInflater();
        View v=inflater.inflate(R.layout.petlist_dialog,null);

        builder.setView(v);

        rg=v.findViewById(R.id.rg);
        rb_b=v.findViewById(R.id.rb_b);
        rb_g=v.findViewById(R.id.rb_g);
        etName=v.findViewById(R.id.et_name);
        etBirth=v.findViewById(R.id.et_birth);
        iv=v.findViewById(R.id.iv);

        rg.clearCheck();

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb=v.findViewById(checkedId);
                gender=rb.getText().toString();
            }
        });

        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                name=etName.getText().toString();
                birth=etBirth.getText().toString();
                Upload();

            }
        });

        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MyPetActivity.this, "취소되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog dialog=builder.create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {

            }
        });
        dialog.setCancelable(false);
        dialog.show();

    }

    public void dialog_photo(View view) {
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==10 && resultCode==RESULT_OK){
            Uri uri=data.getData();
            if(uri!=null){
                Glide.with(this).load(uri).into(iv);

                imgUri=uri;
            }
        }
    }

    Uri imgUri;

    public void Upload(){

        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference ref=firebaseDatabase.getReference();

        Pet pet=new Pet(name,gender,birth);
        DatabaseReference petRef=ref.child("pets");
        petRef.push().setValue(pet);

        ChildEventListener childEventListener=new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Pet p=dataSnapshot.getValue(Pet.class);
                loadName=p.petName;
                loadGender=p.petGender;
                loadBirth=p.petBirth;

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        petRef.addChildEventListener(childEventListener);
        ImgUpload();
    }

    public void ImgUpload(){
        FirebaseStorage firebaseStorage=FirebaseStorage.getInstance();

        String fileName=name+".png";

        StorageReference imgRef=firebaseStorage.getReference("uploads/petImg/"+fileName);
        UploadTask task=imgRef.putFile(imgUri);
        task.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                loadImg=imgUri;

                items.add(new PetInfo(loadName,loadGender,loadBirth,loadImg));
                adapter.notifyDataSetChanged();
            }
        });
    }



//    public void save(){
//        SharedPreferences pref=getSharedPreferences("Data",MODE_PRIVATE);
//        SharedPreferences.Editor editor=pref.edit();
//        editor.putString("Name",loadName);
//        editor.putString("Gender",loadGender);
//        editor.putString("Birth",loadBirth);
//        editor.putString("Img",loadImg.toString());
//        editor.commit();
//    }
//
//    public void load(){
//        SharedPreferences pref=getSharedPreferences("Data",MODE_PRIVATE);
//        String name=pref.getString("Name","empty");
//        String gender=pref.getString("Gender","empty");
//        String birth=pref.getString("Birth","empty");
//        String img=pref.getString("Img","empty");
//
//        Uri imgUri=Uri.parse(img);
//
//        tvName.setText(name);
//        tvGender.setText(gender);
//        tvBirth.setText(birth);
//        cv.setImageURI(imgUri);
//
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        load();
//    }

}
