package com.hanstudio.timer.multiautotimer;

import android.app.AlarmManager;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.zip.Inflater;

/**
 * Created by L on 2015-10-08.
 */
public class ActionAndDeleteService extends Service {


    final String TABLE_NAME = "dataes";
    final String DATABASE_NAME = "database";
    final int VER = 1;


    BluetoothAdapter blueAdapter;
    WifiManager wifiManager;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {



        blueAdapter = BluetoothAdapter.getDefaultAdapter();
        wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);

        Database helper = new Database(getApplicationContext(), DATABASE_NAME, null, VER);
        SQLiteDatabase db = helper.getWritableDatabase();
        long settingTime = intent.getLongExtra("type", 0);

        // db 불러오기.
        String args[] = {"" + settingTime};
        Cursor cur = db.rawQuery("select bluetooth, data, deviceOff, airplane, wifi, settingTime from " + TABLE_NAME + " where settingTime = ?", args);
        int count = cur.getCount();

        if (count == 0) {// db가 이미 삭제 되었을 경우

        } else {

            cur.moveToNext();

            // 설정값 불러오기.
            String bluetooth = cur.getString(0);
            String data = cur.getString(1);
            String deviceOff = cur.getString(2);
            String airplane = cur.getString(3);
            String wifi = cur.getString(4);


            //블루투스 작동
            if (bluetooth.length() == 5) {//false
                if (blueAdapter.getState() == BluetoothAdapter.STATE_ON) {// 블루투스가 켜져있을 경우
                    blueAdapter.disable();
                }
            } else if (bluetooth.length() == 4) { //true
                if (blueAdapter.getState() == BluetoothAdapter.STATE_OFF) {// 블루투스가 꺼져있는 경우만.
                    blueAdapter.enable();
                }
            }


            //음소거 작동
            if (deviceOff.length() == 5) {//false
                //소리 그대로.. 작동없음
            } else if (deviceOff.length() == 4) {//true
                AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
                audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);// 진동 모드로 변경
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, AudioManager.FLAG_PLAY_SOUND); //미디어 볼륨 0으로
            }


            //음악재생 정지 J왜 오류??? 머가 문제??
            if (data.length() == 5) {// false
            } else if (data.length() == 4) {//true

                AudioManager am = (AudioManager) getSystemService(AUDIO_SERVICE);


                am.requestAudioFocus(new AudioManager.OnAudioFocusChangeListener() {
                    @Override
                    public void onAudioFocusChange(int focusChange) {

                    }
                }, AudioManager.ADJUST_MUTE, AudioManager.AUDIOFOCUS_GAIN);

            }


            //wifi 작동
            if (wifi.length() == 5) {// false
                if (wifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLED || wifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLING) {
                        wifiManager.setWifiEnabled(false);
                    }

                } else if (wifi.length() == 4) {//true
                    if (wifiManager.getWifiState() == WifiManager.WIFI_STATE_DISABLED || wifiManager.getWifiState() == WifiManager.WIFI_STATE_DISABLING) {
                        wifiManager.setWifiEnabled(true);
                }
            }


            //intent 에서 넘어오는 시간으로 db 에서 찾고 지우기
            db.execSQL("delete from " + TABLE_NAME + " where settingTime =" + settingTime);
            db.close();

            Intent intentt = new Intent(getApplicationContext(),ListActivity.class);
            intentt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intentt);
        }
        return START_NOT_STICKY; // 서비스 자체의 죽음은 상관 없음 .. 알람 메니져만 안죽으면 땡
    }
}
