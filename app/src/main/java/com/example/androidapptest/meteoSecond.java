package com.example.androidapptest;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
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

public class meteoSecond extends AppCompatActivity {
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
        setContentView(R.layout.activity_meteo_second);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarFIRST);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tmp = (TextView) findViewById(R.id.tmp2);
        min = (TextView) findViewById(R.id.min2);
        max = (TextView) findViewById(R.id.max2);
        City = (TextView) findViewById(R.id.City2);
        dayshort = (TextView) findViewById(R.id.dayshort2);
        date = (TextView) findViewById(R.id.date2);
        hour = (TextView) findViewById(R.id.hour);
        tmpp2= (TextView) findViewById(R.id.tmpp2);
        Tmin= (TextView) findViewById(R.id.Tmin);
        Tmax= (TextView) findViewById(R.id.Tmax);
        imgIconBig = (ImageView) findViewById(R.id.imgIconBig2);


        requestJSON();

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
            startActivity(new Intent( this,meteoSecond.class));        }
        else if ( id == R.id.item3){
            startActivity(new Intent( this,ListLocation.class));}

        return true;
    }

}
