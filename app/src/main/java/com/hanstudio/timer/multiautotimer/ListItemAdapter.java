package com.hanstudio.timer.multiautotimer;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by L on 2015-10-07.
 */
public class ListItemAdapter extends BaseAdapter {

    ArrayList<Items> items = new ArrayList<>();
    Context mContext;


    public ListItemAdapter(Context context) {
        mContext = context;
    }


    @Override
    public int getCount() {
        return items.size();
    }

    public void addItem(Items item){
        items.add(item);
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ListItemView itemView = new ListItemView(mContext);

        itemView.setBlueIcon(items.get(position).getBlue());
        itemView.setDataIcon(items.get(position).getData());
        itemView.setSoundIcon(items.get(position).getSound());
        itemView.setAirIcon(items.get(position).getAir());
        itemView.setWifiIcon(items.get(position).getWifi());

        itemView.setTime(items.get(position).getSettingTime());

        return itemView;
    }
}
