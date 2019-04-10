package com.example.androidapptest;

import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
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

import java.util.ArrayList;
import java.util.Calendar;

public class InfosDetails extends AppCompatActivity {
    private FcstDay fcstDay = new FcstDay();
    private ListView customListView;
    private TextView textTest;
    private TextView textcondition;
    private ImageView imgIconBig;
   // private TextView tmp;
    private TextView min;
    private TextView max;
    private TextView City;
    private TextView dayshort;
    private TextView date;
    private TextView hour;
    private String URLstring = "https://www.prevision-meteo.ch/services/json/grenoble";
    HourlyDataAdapter adapter;
    ArrayList<String> cond = new ArrayList<>();
    String name="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarFIRST);
        setSupportActionBar(toolbar);


        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        customListView = (ListView) findViewById(R.id.listviewforDetails);
       // tmp = (TextView) findViewById(R.id.tmp);
        min = (TextView) findViewById(R.id.min);
        max = (TextView) findViewById(R.id.max);
        City = (TextView) findViewById(R.id.City);
        dayshort = (TextView) findViewById(R.id.dayshort);
        date = (TextView) findViewById(R.id.date);
        imgIconBig = (ImageView) findViewById(R.id.imgIconBig);
       String  jsonfcstDay =getIntent().getStringExtra("fcstDay");
      // textTest.setText(jsonfcstDay);
       setTitle(fcstDay.getDay_long());
        String[] splitArray = null;
        splitArray = jsonfcstDay.split("_");
            min.setText(splitArray[4]+"  ");
            max.setText(splitArray[1]);
            dayshort.setText(splitArray[2]+"  ");
            date.setText(splitArray[3]);
            Picasso.get().load(splitArray[5]).into(imgIconBig);

        //tmp.setText(splitArray[4]);
     request(Integer.parseInt(splitArray[6]));


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

   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

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

    private  void request(final int j){
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, URLstring,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONObject city_info = response.getJSONObject("city_info");
                            CityInfo cityinfo = new CityInfo();
                            cityinfo.setName(city_info.getString("name"));
                            name=cityinfo.getName();
                            City.setText(name);
                            cityinfo.setCountry(city_info.getString("country"));
                            cityinfo.setLatitude(city_info.getString("latitude"));
                            cityinfo.setLongtitude(city_info.getString("longitude"));
                            cityinfo.setElevation(city_info.getString("elevation"));
                            cityinfo.setSunrise(city_info.getString("sunrise"));
                            cityinfo.setSunset(city_info.getString("sunset"));



                            ArrayList<FcstDay> list = new ArrayList<>();

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
                                    cond.add(hourlyData.getCONDITION());
                                    hourlyData.setCONDITION_KEY(hourly_data_0H00.getString("CONDITION_KEY"));
                                    hourlyData.setTMP2m(hourly_data_0H00.getDouble("TMP2m"));
                                    hourlyData.setDPT2m(hourly_data_0H00.getDouble("DPT2m"));
                                    hourlyDataArrayList.add(hourlyData);
                                }
                                fcstDay.setHourly_data(hourlyDataArrayList);

                            adapter=new HourlyDataAdapter (fcstDay.getHourly_data(),InfosDetails.this);
                            customListView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                       // Toast.makeText(ListDisplay.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonRequest);

    }


}
