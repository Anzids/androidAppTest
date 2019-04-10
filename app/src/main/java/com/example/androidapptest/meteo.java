package com.example.androidapptest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

public class meteo  extends AppCompatActivity {
    private String URLstring = "https://www.prevision-meteo.ch/services/json/grenoble";
    private FcstDay fcstDay = new FcstDay();
    private ListView customListView;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarFIRST);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        customListView = (ListView) findViewById(R.id.list_view);
      //  tmp = (TextView) findViewById(R.id.tmp);
        min = (TextView) findViewById(R.id.min);
        max = (TextView) findViewById(R.id.max);
        City = (TextView) findViewById(R.id.City);
        dayshort = (TextView) findViewById(R.id.dayshort);
        date = (TextView) findViewById(R.id.date);
        imgIconBig = (ImageView) findViewById(R.id.imgIconBig);


        requestJSON();


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
                            City.setText(cityinfo.getName());
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
                            date.setText(currentCondition.getDate());
                            currentCondition.setHour(current_condition.getString("hour"));
                            currentCondition.setTmp(current_condition.getString("tmp"));
                            tmp.setText(currentCondition.getTmp());
                            currentCondition.setWnd_spd(current_condition.getString("wnd_spd"));
                            currentCondition.setWnd_gust(current_condition.getString("wnd_gust"));
                            currentCondition.setWnd_dir(current_condition.getString("wnd_dir"));
                            currentCondition.setPressure(current_condition.getString("pressure"));
                            currentCondition.setHumidity(current_condition.getString("humidity"));
                            currentCondition.setCondition(current_condition.getString("condition"));
                            currentCondition.setCondition_key(current_condition.getString("condition_key"));
                            currentCondition.setIcon(current_condition.getString("icon"));
                            currentCondition.setIcon_big(current_condition.getString("icon_big"));
                            Picasso.get().load(currentCondition.getIcon()).into(imgIconBig);

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
