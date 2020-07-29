package com.je1224.animal;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.util.exception.KakaoException;

public class LoginActivity extends AppCompatActivity {

    TextView tv_empty;
    ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tv_empty=findViewById(R.id.tv_empty);
        progressBar=findViewById(R.id.progress);

        tv_empty.setText("로그인없이 이용하기");
        tv_empty.setPaintFlags(tv_empty.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

        Session.getCurrentSession().addCallback(sessionCallback);
    }

    ISessionCallback sessionCallback=new ISessionCallback() {
        @Override
        public void onSessionOpened() {
            Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            Toast.makeText(LoginActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
        }
    };

    public void noLogin(View view) {
        progressBar.setVisibility(View.VISIBLE);
        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
