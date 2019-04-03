package com.example.androidapptest;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListviewActivity extends AppCompatActivity {
    ListView m_Listview=null;
    List<String> m_lsStrings = new ArrayList<String>();
    SwipeRefreshLayout m_refresher;
    ArrayAdapter<String> m_adapter;

    protected void OnCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        m_Listview = findViewById(R.id.listview);
        m_refresher = findViewById(R.id.swiperefreshlayout);

        m_refresher.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                PopulateSimpleListItem1();
            }
        });

        m_refresher.postDelayed(new Runnable() {
            @Override
            public void run(){
                PopulateSimpleListItem1();
            }
        },0);

    }

    private void PopulateSimpleListItem1(){
        m_refresher.setRefreshing(true);
        m_lsStrings.clear();

        for(int i=0; i<20; i++){
            m_lsStrings.add("Element" + i);
        }

        m_adapter.notifyDataSetChanged();
        m_refresher.setRefreshing(false);
    }
}
