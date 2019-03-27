package com.example.androidapptest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class FcstDay {
    public String date;
    public String day_short	;
    public String day_long	;
    public String tmin	;
    public String tmax	;
    public String condition	;
    public String condition_key	;
    public String icon	;
    public String icon_big;

    public FcstDay() {

    }

    public FcstDay(String date,String day_short,String day_long, String tmin, String tmax,String condition,String condition_key,String icon,String icon_big) {
        this.date = date;
        this.day_short= day_short;
        this.day_long= day_long;
        this.tmin=tmin;
        this.tmax=tmax;
        this.condition=condition;
        this.condition_key=condition_key;
        this.icon=icon;
        this.icon_big=icon_big;
    }
    public FcstDay(JSONObject object) throws JSONException {

        date = object.getString("date");
        day_short = object.getString("day_short");
        day_long = object.getString("day_long");
        tmin = object.getString("tmin");
        tmax = object.getString("tmax");
        condition = object.getString("condition");
        condition_key = object.getString("condition_key");
        icon = object.getString("icon");
        icon_big = object.getString("icon_big");

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay_short() {
        return day_short;
    }

    public void setDay_short(String day_short) {
        this.day_short= day_short;
    }

    public String getDay_long() {
        return day_short;
    }

    public void setDay_long(String day_long) {
        this.day_long= day_long;
    }


    public String getTmin() {
        return tmin;
    }

    public void setTmin(String tmin) {
        this.tmin = tmin;
    }
    public String getTmax() {
        return tmax;
    }

    public void setTmax(String tmax) {
        this.tmax = tmax;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition= condition;
    }
    public String getCondition_key() {
        return condition_key;
    }

    public void setCondition_key(String condition_key) {
        this.condition_key= condition_key;
    }
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon= icon;
    }
    public String getIcon_big() {
        return icon_big;
    }

    public void setIcon_big(String icon_big) {
        this.icon_big= icon_big;
    }

}