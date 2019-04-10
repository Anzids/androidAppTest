package com.example.androidapptest;

import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
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
import java.util.Calendar;
import java.util.List;


public class ListLocation extends AppCompatActivity {
    List<FcstDay> m_lsStrings=new ArrayList<FcstDay>();
    ListAdapter m_adapter;
    ArrayList<FcstDay> list = new ArrayList<>();
    int er=0;


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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarFIRST2);
        setSupportActionBar(toolbar);
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

        LocationListView= findViewById(R.id.list_view2);
        swiperefreshLayout2=findViewById(R.id.swiperefreshLayout2);
        titre=findViewById(R.id.titre);

        edittextVille= (EditText)findViewById(R.id.editTextVille);
        //buttonVille=findViewById(R.id.buttonVille);
        m_adapter= new ListAdapter(ListLocation.this,0);
        LocationListView.setAdapter(m_adapter);

        buttonVille = (Button)findViewById(R.id.buttonVille);

        buttonVille.setOnClickListener(new View.OnClickListener() {
            String erreur="";
            @Override
            public void onClick(View v) {
                String ss="samya";
                String urlVille="https://www.prevision-meteo.ch/services/json/"+edittextVille.getText();
                JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, urlVille,null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {

                                    for (int j=0;j<5;j++) {
                                        JSONObject fcst_day_j = response.getJSONObject("fcst_day_" + j);
                                        fcstDay = new FcstDay();
                                        fcstDay.setDate(fcst_day_j.getString("date"));
                                        erreur=fcstDay.getDate();
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
                                 if (m_adapter.getCount()!=0){
                                        titre.setText(edittextVille.getText());
                                    Toast.makeText(getApplicationContext(),"biennnnnn !!!",Toast.LENGTH_SHORT).show();
                                       }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    titre.setText("         City not found \n" +
                                            " Please specify a valid city ");
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


                // final Intent intent = new Intent().setClass(this, Main2Activity.class);
                //  startActivity(intent);
                buttonVille.setVisibility(View.INVISIBLE);
                edittextVille.setVisibility(View.INVISIBLE);
               // titre.setText(edittextVille.getText());



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
                   // Toast.makeText(getApplicationContext(),"bienn !!!",Toast.LENGTH_SHORT).show();
                    // final Intent intent = new Intent().setClass(this, Main2Activity.class);
                  //  startActivity(intent);

            }
        });




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
        FragmentManager fragmentManager= getFragmentManager();

        if (id == R.id.item1){
            startActivity(new Intent( this,ListDisplay.class));
        }
        else if (id==R.id.item2){
            startActivity(new Intent( this,meteoSecond.class)); }
        else if ( id == R.id.item3){
            startActivity(new Intent( this,ListLocation.class));}

        return true;
    }


}
