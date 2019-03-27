package com.example.androidapptest;

import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

public class CurrentCondition {
    public String date	;
    public String hour;
    public String tmp	;
    public String wnd_spd	;
    public String wnd_gust	;
    public String wnd_dir;
    public String pressure;
    public String humidity	;
    public String condition	;
    public String condition_key;
    public String icon;
    public String icon_big;


    public CurrentCondition() {

    }

    public CurrentCondition(String date, String hour, String tmp, String wnd_spd,String wnd_gust,String wnd_dir,String pressure,String humidity, String condition,String condition_key,String icon,String icon_big) {
        this.date = date;
        this.hour = hour;
        this.tmp=tmp;
        this.wnd_spd=wnd_spd;
        this.wnd_gust=wnd_gust;
        this.wnd_dir=wnd_dir;
        this.pressure=pressure;
        this.humidity=humidity;
        this.condition=condition;
        this.condition_key=condition_key;
        this.icon=icon;
        this.icon_big=icon_big;
    }

    public CurrentCondition(JSONObject object) throws JSONException {

        date = object.getString("date");
        hour = object.getString("hour");
        tmp = object.getString("tmp");
        wnd_spd = object.getString("wnd_spd");
        wnd_gust = object.getString("wnd_gust");
        wnd_dir = object.getString("wnd_dir");
        pressure = object.getString("pressure");
        humidity = object.getString("humidity");
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

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getTmp() {
        return tmp;
    }

    public void setTmp(String tmp) {
        this.tmp = tmp;
    }

    public String getWnd_spd() {
        return wnd_spd;
    }

    public void setWnd_spd(String wnd_spd) {
        this.wnd_spd = wnd_spd;
    }
    public String getWnd_gust() {
        return wnd_gust;
    }

    public void setWnd_gust(String wnd_gust) {
        this.wnd_gust = wnd_gust;
    }
    public String getWnd_dir() {
        return wnd_dir;
    }

    public void setWnd_dir(String wnd_dir) {
        this.wnd_dir = wnd_dir;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
    public String getCondition_key() {
        return condition_key;
    }

    public void setCondition_key(String condition_key) {
        this.condition_key = condition_key;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getIcon_big() {
        return icon_big;
    }

    public void setIcon_big(String icon_big) {
        this.icon_big = icon_big;
    }

}