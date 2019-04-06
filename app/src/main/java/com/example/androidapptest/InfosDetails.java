package com.example.androidapptest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;

public class InfosDetails extends AppCompatActivity {
    private FcstDay fcstDay = new FcstDay();
    private ListView customListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        customListView = (ListView) findViewById(R.id.list_view);
       String  jsonfcstDay =getIntent().getStringExtra("fcstDay");
        setTitle(fcstDay.getDay_long());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
