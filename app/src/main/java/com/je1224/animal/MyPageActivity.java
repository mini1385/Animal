package com.je1224.animal;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.api.UserApi;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.callback.UnLinkResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.usermgmt.response.model.Profile;
import com.kakao.usermgmt.response.model.User;
import com.kakao.usermgmt.response.model.UserAccount;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyPageActivity extends AppCompatActivity {

    CircleImageView cv;
    TextView tv;
    Button btn1,btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        cv=findViewById(R.id.cv);
        tv=findViewById(R.id.tv);
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);

        Toolbar tb=findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        actionBar.setDisplayHomeAsUpEnabled(true);

        if(K.userAccount!=null){
            btn1.setVisibility(View.VISIBLE);
            btn2.setVisibility(View.VISIBLE);

            tv.setText(K.email);
            Glide.with(MyPageActivity.this).load(K.profileUrl).into(cv);
        }

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

    public void logout(View view) {
        UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
            @Override
            public void onCompleteLogout() {

            }
        });
        Toast.makeText(MyPageActivity.this, "로그아웃되었습니다.", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(MyPageActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void dataDelete(View view) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this,R.style.dialog_theme);
        LayoutInflater inflater=this.getLayoutInflater();
        builder.setTitle("알림");
        View v=inflater.inflate(R.layout.deletedialog,null);
        builder.setView(v);
        builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                UserManagement.getInstance().requestUnlink(new UnLinkResponseCallback() {
//                    @Override
//                    public void onSessionClosed(ErrorResult errorResult) {
//
//                    }
//
//                    @Override
//                    public void onSuccess(Long result) {
                        Toast.makeText(MyPageActivity.this, "데이터가 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                        UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
                            @Override
                            public void onCompleteLogout() {

                            }
                        });
                        Toast.makeText(MyPageActivity.this, "로그아웃되었습니다.", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(MyPageActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();

//                    }
//                });
            }
        });
        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MyPageActivity.this, "취소하였습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog dialog=builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
}
