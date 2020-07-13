package com.je1224.animal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_activity);

        handler.sendEmptyMessageDelayed(0,2000);

    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            Intent intent=new Intent(IntroActivity.this,LoginActivity.class);
            startActivity(intent);
            overridePendingTransition(0, 2);
            finish();
        }
    };
}
