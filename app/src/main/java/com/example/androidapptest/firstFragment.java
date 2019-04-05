package com.example.androidapptest;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.Nullable;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class firstFragment extends AppCompatActivity{

    Intent intent=new Intent();
    List<FcstDay> m_lsStrings=new ArrayList<FcstDay>();
    ListAdapter m_adapter;
    private TextView textView;
    private TextView textView2;
    private ListView customListView;
    private ImageView photo;
    private SwipeRefreshLayout swiperefreshLayout;
    private FcstDay fcstDay = new FcstDay();

    View myview;
    @android.support.annotation.Nullable

    public View onCreateView(LayoutInflater inflater, @android.support.annotation.Nullable ViewGroup container, Bundle savedInstanceState) {
        myview=inflater.inflate(R.layout.first_layout, container, false);
        return myview;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);


        //customListView= findViewById(R.id.list_view);
        swiperefreshLayout=findViewById(R.id.swiperefreshLayout);
        m_adapter= new ListAdapter(this,0);
        customListView.setAdapter(m_adapter);


        swiperefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                informationCity();
            }
        });

        swiperefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                informationCity();
            }
        },0);
        // requestJSON();

        /*customListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ListDisplay.this,InfosDetails.class);
                intent.putExtra("fcstDay", m_adapter.getItem(i).toJSONText());
                startActivity(intent);
            }
        });*/

    }

    private  void informationCity(){
        swiperefreshLayout.setRefreshing(true);
        m_lsStrings.clear();

       /* JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, URLstring,null,
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

                                //list.add(fcstDay);
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
                        Toast.makeText(firstFragment.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonRequest);*/

      // intent.getDate();
    }

}
