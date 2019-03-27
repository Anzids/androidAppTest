package com.example.androidapptest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;

public class ListAdapter extends ArrayAdapter<FcstDay> {
    private ArrayList<FcstDay> fcstDays;
    private Context context;

    public ListAdapter(@NonNull Context context, int resource){
        super(context,resource);
       this.context=context;
       fcstDays=new ArrayList<FcstDay>();
    }

    @Override
    public int getPosition(FcstDay item){return fcstDays.indexOf(item);}

    @Override
    public int getCount() {
        return fcstDays.size();
    }

    @Override
    public FcstDay getItem(int i) {
        return this.fcstDays.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       View  rowview=layoutInflater.inflate(R.layout.activity_list,viewGroup,false);
       TextView textViewDay=(TextView) rowview.findViewById(R.id.textViewDay);
       TextView textViewDesc=(TextView) rowview.findViewById(R.id.textViewDesc);
        TextView textViewtmax=(TextView) rowview.findViewById(R.id.textViewtmax);
       ImageView imageView =(ImageView) rowview.findViewById(R.id.imgviewMeteo);
       ImageView imageView2 =(ImageView) rowview.findViewById(R.id.imgviewgrande);
        FcstDay dayToShow = getItem(i);
    textViewDay.setText(dayToShow.getDay_short());
    textViewDesc.setText(dayToShow.getTmin()+"°C");
    textViewtmax.setText(dayToShow.getTmax()+"°C");
    Picasso.get().load(dayToShow.getIcon()).into(imageView);
if(i==0){
        Picasso.get().load(dayToShow.getIcon()).into(imageView2);}
        else{
    imageView2.setVisibility(View.GONE);

}
        return rowview;

    }



    @Override
    public void addAll(@NonNull Collection<? extends FcstDay> collection){
       fcstDays.clear();
       fcstDays.addAll(collection);
       notifyDataSetChanged();
    }
}