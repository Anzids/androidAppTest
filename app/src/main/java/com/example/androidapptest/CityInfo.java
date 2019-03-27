package com.example.androidapptest;
import org.json.JSONObject;
import org.json.JSONException;

public class CityInfo {
    public String name;
    public String country;
    public String latitude;
    public String longtitude;
    public String elevation;
    public String sunrise;
    public String sunset;

    public CityInfo() {

    }

    public CityInfo(String name,String country, String latitude, String longtitude,String elevation, String sunrise,String sunset) {
        this.name = name;
        this.country = country;
        this.latitude = latitude;
        this.longtitude=longtitude;
        this.elevation=elevation;
        this.sunrise=sunrise;
        this.sunset=sunset;
    }
    public CityInfo(JSONObject object) throws JSONException {

        name = object.getString("name");
        country = object.getString("country");
        latitude = object.getString("latitude");
        longtitude = object.getString("longitude");
        elevation = object.getString("elevation");
        sunrise = object.getString("sunrise");
        sunset = object.getString("sunset");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }
    public String getElevation() {
        return elevation;
    }

    public void setElevation(String elevation) {
        this.elevation = elevation;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }
}