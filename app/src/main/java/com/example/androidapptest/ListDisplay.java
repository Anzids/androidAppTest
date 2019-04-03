package com.example.androidapptest;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
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
import java.util.List;

import android.view.View;
import android.widget.AdapterView;

public class ListDisplay  extends AppCompatActivity {
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
                intent.putExtra("fcstDay", m_adapter.getItem(i).toJSONText());
                startActivity(intent);
            }
        });

        }

        private void requestJSON(){

            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, URLstring,null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONObject obj = response.getJSONObject("city_info");
                                        CityInfo cityinfo = new CityInfo();
                                        cityinfo.setName(obj.getString("name"));
                                        cityinfo.setCountry(obj.getString("country"));
                                        cityinfo.setLatitude(obj.getString("latitude"));
                                textView.setText(cityinfo.getName());
                                textView2.setText(cityinfo.getCountry());
                                CurrentCondition current = new CurrentCondition();
                                current.setIcon(obj.getString("icon"));
                                //Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(photo);
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


    }