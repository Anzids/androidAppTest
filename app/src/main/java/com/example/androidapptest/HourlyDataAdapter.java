package com.example.androidapptest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;

public class HourlyDataAdapter  extends BaseAdapter {
    private ArrayList<HourlyData> hourlyDataArrayList;
    private Context context;

    public HourlyDataAdapter(ArrayList<HourlyData> fcstDays, Context context) {
        this.hourlyDataArrayList = fcstDays;
        this.context = context;
    }



    @Override
    public int getCount() {
        return hourlyDataArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=layoutInflater.inflate(R.layout.content_hourly_activity,null);
        ImageView photo;

        HourlyData hourlyData = hourlyDataArrayList.get(i);
        photo=(ImageView)view.findViewById(R.id.imgviewhourly);

        TextView name=(TextView)view.findViewById(R.id.textViewhourly1);
        TextView profession=(TextView)view.findViewById(R.id.textViewhourly2);
      //  TextView temp1=(TextView)view.findViewById(R.id.textViewhourly3);
        name.setText(hourlyData.getCONDITION());
        profession.setText(hourlyData.getHEURE());
        //temp1.setText((int) hourlyData.getDPT2m());
        Picasso.get().load(hourlyData.getICON()).into(photo);
        return view;
    }


    public void addAll(@NonNull Collection<? extends HourlyData> collection){
        hourlyDataArrayList.clear();
        hourlyDataArrayList.addAll(collection);
        notifyDataSetChanged();
    }
}
