package com.example.androidapptest;
import org.json.JSONException;
import org.json.JSONObject;

public class ForecastInfo {
    public String latitude;
    public String longtitude;
    public String elevation;

    public ForecastInfo() {

    }

    public ForecastInfo(String latitude, String longtitude, String elevation) {
        this.latitude= latitude;
        this.longtitude = longtitude;
        this.elevation= elevation;
    }
    public ForecastInfo(JSONObject object) throws JSONException {

        latitude = object.getString("latitude");
        longtitude = object.getString("longtitude");
        elevation = object.getString("elevation");

    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude= latitude;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude= longtitude;
    }

    public String getElevation() {
        return elevation;
    }

    public void setElevation(String elevation) {
        this.elevation= elevation;
    }
}