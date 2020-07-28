package com.je1224.animal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TaskActivity extends AppCompatActivity {

    TextView tvDate;
    EditText etMsg;
    Switch alarm;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        context=getBaseContext();

        tvDate=findViewById(R.id.tv_date);
        etMsg=findViewById(R.id.et_msg);
        alarm=findViewById(R.id.alarm);

        Intent intent = getIntent();
        String day=intent.getExtras().getString("day");
        tvDate.setText(day+"일");

    }

    public void addDate(View view) {
        finish();

//        alarm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(isChecked==true){
//                    NotificationManager notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//                    NotificationCompat.Builder builder=null;
//
//                    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
//                        NotificationChannel channel=new NotificationChannel("ch01","channer01",NotificationManager.IMPORTANCE_HIGH);
//                        notificationManager.createNotificationChannel(channel);
//
//                        builder=new NotificationCompat.Builder(context,"ch01");
//                    }else{
//                        builder=new NotificationCompat.Builder(context,null);
//                    }
//
//                    builder.setSmallIcon(R.drawable.ic_notifications_gray_24dp);
//                    builder.setContentTitle("오늘 일정이 있어요!");
//                    builder.setContentText("스케줄러에서 확인해보세요:)");
//
//                    Resources res=getResources();
//                    Bitmap bm= BitmapFactory.decodeResource(res,R.mipmap.ic_cat);
//                    builder.setLargeIcon(bm);
//                    builder.setVibrate(new long[]{0,2000,1000,3000});
//
//                    Intent intent2=new Intent(TaskActivity.this,Fragment_calendar.class);
//                    PendingIntent pendingIntent=PendingIntent.getActivity(TaskActivity.this,10,intent2,PendingIntent.FLAG_UPDATE_CURRENT);
//                    builder.setContentIntent(pendingIntent);
//                    builder.setAutoCancel(true);
//
//                    Notification notification=builder.build();
//                    notificationManager.notify(1,notification);
//                }
//            }
//        });

    }

    public void datePicker(View view) {
        Calendar c = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(TaskActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                try {
                    Date d = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
                    tvDate.setText(year+"년 "+(monthOfYear+1)+"월 "+dayOfMonth+"일");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.getDatePicker().setCalendarViewShown(false);
        datePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        datePickerDialog.show();

    }


    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

}
