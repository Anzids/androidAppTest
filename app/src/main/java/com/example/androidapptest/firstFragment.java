package com.example.androidapptest;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.Nullable;

public class firstFragment extends Fragment{
    View myview;
    @android.support.annotation.Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @android.support.annotation.Nullable ViewGroup container, Bundle savedInstanceState) {
        myview=inflater.inflate(R.layout.first_layout, container, false);
        return myview;
    }
}
