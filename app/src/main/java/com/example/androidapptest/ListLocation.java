package com.example.androidapptest;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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


public class ListLocation extends AppCompatActivity {
    List<FcstDay> m_lsStrings=new ArrayList<FcstDay>();
    ListAdapter m_adapter;

    private String URLstring = "https://www.prevision-meteo.ch/services/json/";
    private EditText edittextVille;
    public Button buttonVille;
    private SwipeRefreshLayout swiperefreshLayout2;
    private ListView LocationListView;
    private FcstDay fcstDay = new FcstDay();
    private TextView titre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_location);
        /*Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        LocationListView= findViewById(R.id.list_view2);
        swiperefreshLayout2=findViewById(R.id.swiperefreshLayout2);
        titre=findViewById(R.id.titre);

        edittextVille= (EditText)findViewById(R.id.editTextVille);
        //buttonVille=findViewById(R.id.buttonVille);
        m_adapter= new ListAdapter(ListLocation.this,0);
        LocationListView.setAdapter(m_adapter);

        buttonVille = (Button)findViewById(R.id.buttonVille);

        buttonVille.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String urlVille="https://www.prevision-meteo.ch/services/json/"+edittextVille.getText();
                JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, urlVille,null,
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
                                    swiperefreshLayout2.setRefreshing(false);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                //displaying the error in toast if occurrs
                                Toast.makeText(ListLocation.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                RequestQueue requestQueue = Volley.newRequestQueue(ListLocation.this);
                requestQueue.add(jsonRequest);
                Toast.makeText(getApplicationContext(),"bienn !!!",Toast.LENGTH_SHORT).show();
                // final Intent intent = new Intent().setClass(this, Main2Activity.class);
                //  startActivity(intent);
                buttonVille.setVisibility(View.INVISIBLE);
                edittextVille.setVisibility(View.INVISIBLE);
                titre.setText(edittextVille.getText());



            }
        });


    }



    private  void PopulateSimpleListItem1(){
        swiperefreshLayout2.setRefreshing(true);
        m_lsStrings.clear();

        buttonVille.setOnClickListener(new View.OnClickListener() {
            String urlVille=URLstring+edittextVille.getText();
            @Override
            public void onClick(View v) {
                JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, urlVille,null,
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
                                    swiperefreshLayout2.setRefreshing(false);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                //displaying the error in toast if occurrs
                                Toast.makeText(ListLocation.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                RequestQueue requestQueue = Volley.newRequestQueue(ListLocation.this);
                requestQueue.add(jsonRequest);
                    Toast.makeText(getApplicationContext(),"bienn !!!",Toast.LENGTH_SHORT).show();
                    // final Intent intent = new Intent().setClass(this, Main2Activity.class);
                  //  startActivity(intent);

            }
        });




    }





}
