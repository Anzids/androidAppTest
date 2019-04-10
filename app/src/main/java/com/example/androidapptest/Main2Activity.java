package com.example.androidapptest;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String URLstring = "https://www.prevision-meteo.ch/services/json/grenoble";
    private FcstDay fcstDay = new FcstDay();
    private TextView textTest;
    private TextView textcondition;
    private ImageView imgIconBig;
    private TextView tmp;
    private TextView min;
    private TextView max;
    private TextView City;
    private TextView dayshort;
    private TextView date;
    private TextView hour;
    private TextView tmpp2;
    private TextView Tmin;
    private TextView Tmax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tmp = (TextView) findViewById(R.id.tmp3);
        min = (TextView) findViewById(R.id.min3);
        max = (TextView) findViewById(R.id.max3);
        City = (TextView) findViewById(R.id.City3);
        dayshort = (TextView) findViewById(R.id.dayshort3);
        date = (TextView) findViewById(R.id.date3);
        hour = (TextView) findViewById(R.id.hour3);
        tmpp2= (TextView) findViewById(R.id.tmpp3);
        Tmin= (TextView) findViewById(R.id.Tmin3);
        Tmax= (TextView) findViewById(R.id.Tmax3);
        imgIconBig = (ImageView) findViewById(R.id.imgIconBig3);

        requestJSON();

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager= getFragmentManager();

        if (id == R.id.nav_first_layout) {
            // Handle the camera action
            startActivity(new Intent( this,ListDisplay.class));
            //fragmentManager.beginTransaction().replace(R.id.content_frame,new firstFragment()).commit();
        } else if (id == R.id.nav_second_layout) {
            //fragmentManager.beginTransaction().replace(R.id.content_frame,new secondFragment()).commit();
            startActivity(new Intent( this,meteoSecond.class));
        }
        else if (id == R.id.nav_third_layout) {
          //  fragmentManager.beginTransaction().replace(R.id.content_frame,new ThirdFragment()).commit();
            startActivity(new Intent( this,ListLocation.class));
        }  else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void requestJSON(){

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, URLstring,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject city_info = response.getJSONObject("city_info");
                            CityInfo cityinfo = new CityInfo();
                            cityinfo.setName(city_info.getString("name")+"  ");
                            cityinfo.setCountry(city_info.getString("country"));
                            City.setText(cityinfo.getName()+"  "+cityinfo.getCountry());
                            cityinfo.setLatitude(city_info.getString("latitude"));
                            cityinfo.setLongtitude(city_info.getString("longitude"));
                            cityinfo.setElevation(city_info.getString("elevation"));
                            cityinfo.setSunrise(city_info.getString("sunrise"));
                            min.setText(cityinfo.getSunrise());
                            cityinfo.setSunset(city_info.getString("sunset"));
                            max.setText(cityinfo.getSunset());

                            JSONObject forecast_info = response.getJSONObject("forecast_info");
                            ForecastInfo forecastInfo = new ForecastInfo();
                            forecastInfo.setLatitude(forecast_info.getString("latitude"));
                            forecastInfo.setLongtitude(forecast_info.getString("longitude"));
                            forecastInfo.setElevation(forecast_info.getString("longitude"));

                            JSONObject current_condition = response.getJSONObject("current_condition");
                            CurrentCondition currentCondition =new CurrentCondition();
                            currentCondition.setDate(current_condition.getString("date"));
                            date.setText(currentCondition.getDate()+"  ");
                            currentCondition.setHour(current_condition.getString("hour"));
                            hour.setText(currentCondition.getHour());
                            currentCondition.setTmp(current_condition.getString("tmp"));
                            tmp.setText(currentCondition.getTmp()+" ");
                            currentCondition.setWnd_spd(current_condition.getString("wnd_spd"));
                            currentCondition.setWnd_gust(current_condition.getString("wnd_gust"));
                            currentCondition.setWnd_dir(current_condition.getString("wnd_dir"));
                            currentCondition.setPressure(current_condition.getString("pressure"));
                            currentCondition.setHumidity(current_condition.getString("humidity"));
                            currentCondition.setCondition(current_condition.getString("condition"));
                            tmpp2.setText(currentCondition.getCondition()+"°");
                            currentCondition.setCondition_key(current_condition.getString("condition_key"));
                            currentCondition.setIcon(current_condition.getString("icon"));
                            currentCondition.setIcon_big(current_condition.getString("icon_big"));
                            Picasso.get().load(currentCondition.getIcon()).into(imgIconBig);

                            JSONObject fcst_day_j = response.getJSONObject("fcst_day_0");
                            fcstDay = new FcstDay();
                            fcstDay.setTmin(fcst_day_j.getString("tmin"));
                            Tmin.setText(fcstDay.getTmin()+" °C  / ");
                            fcstDay.setTmax(fcst_day_j.getString("tmax"));
                            Tmax.setText(fcstDay.getTmax()+" °C");
                            fcstDay.setDay_short(fcst_day_j.getString("day_short"));
                            dayshort.setText(fcstDay.getDay_short()+" ");


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


}
