package com.hanstudio.timer.multiautotimer;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Calendar;


public class ListItemView extends RelativeLayout {

    ImageView img1, img2, img3, img4, img5;
    TextView time;

    long Time;

    public ListItemView(Context context) {
        super(context);

        init(context);
    }

    public void init(Context context) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.list_item_view, this);

        img1 = (ImageView) findViewById(R.id.blue);
        img2 = (ImageView) findViewById(R.id.data);
        img3 = (ImageView) findViewById(R.id.sound);
        img4 = (ImageView) findViewById(R.id.air);
        img5 = (ImageView) findViewById(R.id.wifi);

        time = (TextView) findViewById(R.id.time);

        Typeface type = Typeface.createFromAsset(context.getAssets(),"BMJUA_ttf.ttf");
        time.setTypeface(type);

    }

    public void setTime(long time) {

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);

        Time = time;

        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);

        if(hour==0|| hour==1 ||hour==2 ||hour==3 ||hour==4 ||hour==5 ||hour==6 ||hour==7 ||hour==8 ||hour==9) {
            if(min==0|| min==1 ||min==2 ||min==3 ||min==4 ||min==5 ||min==6 ||min==7 ||min==8 ||min==9){
                this.time.setText("0"+hour + ":0" + min);
            }else{
                this.time.setText("0"+hour + ":" + min);
            }

        }else if(min==0|| min==1 ||min==2 ||min==3 ||min==4 ||min==5 ||min==6 ||min==7 ||min==8 ||min==9){
            this.time.setText(hour + ":0" + min);
        }else{
            this.time.setText(hour + ":" + min);
        }

    }

    public void setBlueIcon(String bool) {

        if (bool.length() == 5) {
            img1.setImageResource(R.drawable.blue_off);
        } else {
            img1.setImageResource(R.drawable.blue_on);
        }


    }

    public void setDataIcon(String bool) {

        if (bool.length() == 5) {
            img2.setImageResource(R.drawable.data_off);
        } else {
            img2.setImageResource(R.drawable.data_on);
        }

    }

    public void setSoundIcon(String bool) {
        if (bool.length() == 5) {
            img3.setImageResource(R.drawable.system_off);
        } else {
            img3.setImageResource(R.drawable.system_on);
        }
    }

    public void setAirIcon(String bool) {
        if (bool.length() == 5) {
            img4.setImageResource(R.drawable.air_off);
        } else {
            img4.setImageResource(R.drawable.air_on);
        }
    }

    public void setWifiIcon(String bool) {

        if (bool.length() == 5) {
            img5.setImageResource(R.drawable.wifi_off);
        } else {
            img5.setImageResource(R.drawable.wifi_on);
        }

    }


    public long getTime(){


        return Time;
    }
}
