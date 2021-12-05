package com.hanstudio.timer.multiautotimer;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Random;

public class InsertActivity extends Activity {


    final String TABLE_NAME = "dataes";
    final String DATABASE_NAME = "database";
    final int VER = 1;
    // 설정 사항
    private boolean bluetooth = false;
    private boolean data = false;
    private boolean deviceOff = false;
    private boolean airplane = false;
    private boolean wifi = false;
    //꺼짐 시간.
    private long settingTime = 0;


    private ImageView box1, box2, box3, box4, box5;
    private TextView text1, text2, text3, text4, text5, text6, time, afterText;

    private Typeface type;

    private Button ok_Button, button1, button2, button3, button4;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);


        Toast toast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);
        View view = View.inflate(getApplicationContext(), R.layout.toast_view, null);
        toast.setView(view);
        toast.show();

        box1 = (ImageView) findViewById(R.id.box1);
        box2 = (ImageView) findViewById(R.id.box2);
        box3 = (ImageView) findViewById(R.id.box3);
        box4 = (ImageView) findViewById(R.id.box4);
        box5 = (ImageView) findViewById(R.id.box5);

        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
        text3 = (TextView) findViewById(R.id.text3);
        text4 = (TextView) findViewById(R.id.text4);
        text5 = (TextView) findViewById(R.id.text5);
        text6 = (TextView) findViewById(R.id.text6);
        afterText = (TextView) findViewById(R.id.after_text);

        time = (TextView) findViewById(R.id.time);

        ok_Button = (Button) findViewById(R.id.ok_button);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);

        type = Typeface.createFromAsset(getAssets(), "BMJUA_ttf.ttf");

        text1.setTypeface(type);
        text2.setTypeface(type);
        text3.setTypeface(type);
        text4.setTypeface(type);
        text5.setTypeface(type);
        text6.setTypeface(type);

        afterText.setTypeface(type);

        time.setTypeface(type);


        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingTime = 0;
                addTime();
            }
        });

        //시간 설정 버튼
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1분 추가.
                settingTime += 60000;
                //  Log.d("settingTime" ,"totalTime : " + settingTime);
                addTime();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //10분 추가.
                settingTime += 600000;
                // Log.d("settingTime" ,"totalTime : " + settingTime);
                addTime();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //30분 추가.
                settingTime += 1800000;
                //  Log.d("settingTime" ,"totalTime : " + settingTime);
                addTime();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1시간 추가.
                settingTime += 3600000;
                // Log.d("settingTime" ,"totalTime : " + settingTime);
                addTime();
            }
        });


        ok_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (settingTime == 0) {

                    Toast toast = Toast.makeText(getApplicationContext(),"", Toast.LENGTH_SHORT);
                    View view = View.inflate(getApplicationContext(),R.layout.toast_view_3,null);
                    toast.setView(view);
                    toast.show();

                } else if (settingTime != 0) {
                    //바로 알람 서비스 시작
                    setDown(settingTime);

                    Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                    startActivity(intent);
                    overridePendingTransition(0, 0);

                    finish();
                }
            }
        });


        //box 별 상태표시
        box1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!bluetooth) {
                    box1.setBackgroundResource(R.drawable.checked);
                    bluetooth = true;
                } else {
                    box1.setBackgroundResource(R.drawable.un_checked);
                    bluetooth = false;
                }
            }
        });
        box2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   if (!data) {
                 box2.setBackgroundResource(R.drawable.checked);
                 data = true;
                 } else {
                 box2.setBackgroundResource(R.drawable.un_checked);
                 data = false;

                 }




            }

        });
        box3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!deviceOff) {
                    box3.setBackgroundResource(R.drawable.checked);
                    deviceOff = true;
                } else {
                    box3.setBackgroundResource(R.drawable.un_checked);
                    deviceOff = false;
                }


            }
        });
        box4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**    if (!airplane) {
                 box4.setBackgroundResource(R.drawable.checked);
                 airplane = true;
                 } else {
                 box4.setBackgroundResource(R.drawable.un_checked);
                 airplane = false;
                 }*/

                Toast toast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);
                View view = View.inflate(getApplicationContext(), R.layout.toast_view_two, null);
                toast.setView(view);
                toast.show();

            }
        });
        box5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!wifi) {
                    box5.setBackgroundResource(R.drawable.checked);
                    wifi = true;
                } else {
                    box5.setBackgroundResource(R.drawable.un_checked);
                    wifi = false;
                }
            }
        });


    }


    private void addTime() {
        int hour = (int) settingTime / 3600000;
        int min = (int) (settingTime % 3600000) / 60000;
        if (hour == 0 || hour == 1 || hour == 2 || hour == 3 || hour == 4 || hour == 5 || hour == 6 || hour == 7 || hour == 8 || hour == 9) {
            if (min == 0 || min == 1 || min == 2 || min == 3 || min == 4 || min == 5 || min == 6 || min == 7 || min == 8 || min == 9) {
                time.setText("0" + hour + ":0" + min);
            } else {
                time.setText("0" + hour + ":" + min);
            }

        } else if (min == 0 || min == 1 || min == 2 || min == 3 || min == 4 || min == 5 || min == 6 || min == 7 || min == 8 || min == 9) {
            time.setText(hour + ":0" + min);
        } else {
            time.setText(hour + ":" + min);
        }

    }

    public void setDown(long time) {


        AlarmManager manager = (AlarmManager) getApplicationContext().getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(getApplicationContext(), ActionAndDeleteService.class);

        long curTime = System.currentTimeMillis() + time;
        intent.putExtra("type", curTime);
        //종료시간 넘겨주기

        PendingIntent pIntent = PendingIntent.getService(getApplicationContext(),(int)System.currentTimeMillis(),intent,0);

        manager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + time, pIntent);
        setData(this.bluetooth, this.data, this.deviceOff, this.airplane, this.wifi, curTime);

    }

    public void setData(boolean bluetooth, boolean data, boolean deviceOff, boolean airplane, boolean wifi, long settingTime) {
        //db 에 데이터 설정

        Database helper = new Database(getApplicationContext(), DATABASE_NAME, null, VER);
        SQLiteDatabase db = helper.getWritableDatabase();

        db.execSQL("insert into " + TABLE_NAME + "(bluetooth,data,deviceOff,airplane,wifi,settingTime) values ('" + bluetooth + "', '" + data + "', '" + deviceOff + "', '" + airplane + "', '" + wifi + "', " + settingTime + ");");

        db.close();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(getApplicationContext(), ListActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);

        finish();


    }



}
