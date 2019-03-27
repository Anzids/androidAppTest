package com.example.androidapptest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Collection;

public class MeteoAdapter extends ArrayAdapter<FcstDay> {

    private  Context m_context;
    private final ArrayList<FcstDay> m_lsItems;

    public MeteoAdapter(@NonNull Context context, int resource){
        super(context,resource);
        this.m_context= context;
        this.m_lsItems= new ArrayList<>();

    }

    public int getPosition(FcstDay item){
        return m_lsItems.indexOf(item);
    }

    public int getCount(){
        return m_lsItems.size();
    }

    public FcstDay getItem(int position){
        return this.m_lsItems.get(position);
    }

    public View getView(int position, View convertView , ViewGroup parent){
        return null;
    }
    public void addAll(@NonNull Collection<? extends FcstDay> collection){

    }
}
