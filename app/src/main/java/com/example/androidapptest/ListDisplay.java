package com.example.androidapptest;

import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;


import org.json.JSONArray;
import org.json.JSONObject;
import android.os.AsyncTask;
import android.util.Log;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.view.View;
import android.widget.AdapterView;

public class ListDisplay  extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {
        List<FcstDay> m_lsStrings=new ArrayList<FcstDay>();
        ListAdapter m_adapter;

    ArrayList<FcstDay> list = new ArrayList<>();


    private String URLstring = "https://www.prevision-meteo.ch/services/json/grenoble";
        private TextView textView;
        private TextView textView2;
        private ListView customListView;
        private ImageView photo;
        private SwipeRefreshLayout swiperefreshLayout;
        private FcstDay fcstDay = new FcstDay();


    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.first_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarFIRST);
        setSupportActionBar(toolbar);


        /*textView = findViewById(R.id.textview277);
        textView2 = findViewById(R.id.textview2);
        photo=findViewById(R.id.photo);*/
       // m_adapter=new ArrayAdapter<FcstDay>(this, android.R.layout.simple_list_item_1,m_lsStrings);
        customListView= findViewById(R.id.list_view);
        swiperefreshLayout=findViewById(R.id.swiperefreshLayout);
        m_adapter= new ListAdapter(this,0);
        customListView.setAdapter(m_adapter);


        swiperefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                PopulateSimpleListItem1();
            }
        });

        swiperefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                PopulateSimpleListItem1();
            }
        },0);
           // requestJSON();

        customListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ListDisplay.this,InfosDetails.class);
                intent.putExtra("fcstDay",  m_adapter.getItem(i).toJSONText(i));
                startActivity(intent);

            }
        });
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int isNight;
        int currentDrawable=R.drawable.blue_gradient;

        if(hour < 6 || hour > 20)
            isNight=1;
        else
        if(hour < 20 || hour > 18)
            isNight=3;
        else
            isNight=2;

        switch(isNight){
            case 1:
                currentDrawable=R.drawable.darknight_gradient;
                break;
            case 2:
                currentDrawable=R.drawable.night_gradient;
                break;
            case 3:
                currentDrawable=R.drawable.blue_gradient;
                break;
        }
        View decorView = getWindow().getDecorView();
        Drawable drawable = ContextCompat.getDrawable(this, currentDrawable);
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN)
            decorView.setBackgroundDrawable(drawable);
        else
            decorView.setBackground(drawable);

        }

        private void requestJSON(){

            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, URLstring,null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONObject city_info = response.getJSONObject("city_info");
                                CityInfo cityinfo = new CityInfo();
                                cityinfo.setName(city_info.getString("name"));
                                cityinfo.setCountry(city_info.getString("country"));
                                cityinfo.setLatitude(city_info.getString("latitude"));
                                cityinfo.setLongtitude(city_info.getString("longitude"));
                                cityinfo.setElevation(city_info.getString("elevation"));
                                cityinfo.setSunrise(city_info.getString("sunrise"));
                                cityinfo.setSunset(city_info.getString("sunset"));

                                JSONObject forecast_info = response.getJSONObject("forecast_info");
                                ForecastInfo forecastInfo = new ForecastInfo();
                                forecastInfo.setLatitude(forecast_info.getString("latitude"));
                                forecastInfo.setLongtitude(forecast_info.getString("longitude"));
                                forecastInfo.setElevation(forecast_info.getString("longitude"));

                                JSONObject current_condition = response.getJSONObject("current_condition");
                                CurrentCondition currentCondition =new CurrentCondition();
                                currentCondition.setDate(current_condition.getString("date"));
                                currentCondition.setHour(current_condition.getString("hour"));
                                currentCondition.setTmp(current_condition.getString("tmp"));
                                currentCondition.setWnd_spd(current_condition.getString("wnd_spd"));
                                currentCondition.setWnd_gust(current_condition.getString("wnd_gust"));
                                currentCondition.setWnd_dir(current_condition.getString("wnd_dir"));
                                currentCondition.setPressure(current_condition.getString("pressure"));
                                currentCondition.setHumidity(current_condition.getString("humidity"));
                                currentCondition.setCondition(current_condition.getString("condition"));
                                currentCondition.setCondition_key(current_condition.getString("condition_key"));
                                currentCondition.setIcon(current_condition.getString("icon"));
                                currentCondition.setIcon_big(current_condition.getString("icon_big"));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //displaying the error in toast if occurrs
                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

            //creating a request queue
            RequestQueue requestQueue = Volley.newRequestQueue(this);

            //adding the string request to request queue
            requestQueue.add(jsonRequest);

        }
        private  void PopulateSimpleListItem1(){
            swiperefreshLayout.setRefreshing(true);
            m_lsStrings.clear();




            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, URLstring,null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {



                                ArrayList<FcstDay> list = new ArrayList<>();
                                for (int j=0;j<5;j++) {
                                    JSONObject fcst_day_j = response.getJSONObject("fcst_day_" + j);
                                    fcstDay = new FcstDay();
                                    fcstDay.setDate(fcst_day_j.getString("date"));
                                    fcstDay.setDay_short(fcst_day_j.getString("day_short"));
                                    fcstDay.setIcon(fcst_day_j.getString("icon"));
                                    fcstDay.setDay_long(fcst_day_j.getString("day_long"));
                                    fcstDay.setTmin(fcst_day_j.getString("tmin"));
                                    fcstDay.setTmax(fcst_day_j.getString("tmax"));
                                    fcstDay.setCondition(fcst_day_j.getString("condition"));
                                    fcstDay.setIcon_big(fcst_day_j.getString("icon_big"));


                                    list.add(fcstDay);


                                    JSONObject hourly_data = fcst_day_j.getJSONObject("hourly_data");
                                    ArrayList<HourlyData> hourlyDataArrayList = new ArrayList<>();
                                    for (int i = 0; i < 24; i++) {
                                        JSONObject hourly_data_0H00 = hourly_data.getJSONObject(i + "H00");
                                        HourlyData hourlyData = new HourlyData();
                                        hourlyData.setHEURE(i + "H00");
                                        hourlyData.setICON(hourly_data_0H00.getString("ICON"));
                                        hourlyData.setCONDITION(hourly_data_0H00.getString("CONDITION"));
                                        hourlyData.setCONDITION_KEY(hourly_data_0H00.getString("CONDITION_KEY"));
                                        hourlyData.setTMP2m(hourly_data_0H00.getDouble("TMP2m"));
                                        hourlyData.setDPT2m(hourly_data_0H00.getDouble("DPT2m"));
                                        hourlyDataArrayList.add(hourlyData);
                                    }
                                    fcstDay.setHourly_data(hourlyDataArrayList);


                                }
                                m_adapter.addAll(list);
                                m_adapter.notifyDataSetChanged();
                                swiperefreshLayout.setRefreshing(false);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //displaying the error in toast if occurrs
                            Toast.makeText(ListDisplay.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(jsonRequest);

        }
    private  void informationCity(){
        swiperefreshLayout.setRefreshing(true);
        m_lsStrings.clear();

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, URLstring,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            for (int j=0;j<1;j++) {
                                JSONObject fcst_day_j = response.getJSONObject("fcst_day_" + j);
                                fcstDay = new FcstDay();
                                fcstDay.setDate(fcst_day_j.getString("date"));
                                fcstDay.setDay_short(fcst_day_j.getString("day_short"));
                                fcstDay.setIcon(fcst_day_j.getString("icon"));
                                fcstDay.setDay_long("Aujourd'hui");
                                fcstDay.setDay_long(fcst_day_j.getString("day_long"));
                                fcstDay.setTmin(fcst_day_j.getString("tmin"));
                                fcstDay.setTmax(fcst_day_j.getString("tmax"));

                                list.add(fcstDay);
                            }
                            m_adapter.addAll(list);
                            m_adapter.notifyDataSetChanged();
                            swiperefreshLayout.setRefreshing(false);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(ListDisplay.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonRequest);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.exemple, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

            if (id == R.id.item1){
                startActivity(new Intent( this,ListDisplay.class));
            }
           else if (id==R.id.item2){
                startActivity(new Intent( this,meteoSecond.class));           }
            else if ( id == R.id.item3){
                startActivity(new Intent( this,ListLocation.class));}

        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }
}