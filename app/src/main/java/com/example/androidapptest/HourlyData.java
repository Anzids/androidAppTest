package com.example.androidapptest;

public class HourlyData {
    private String HEURE;
    private String ICON;
    private String CONDITION;
    private String CONDITION_KEY;
    private double TMP2m;
    private double DPT2m;


    public String getHEURE() {
        return HEURE;
    }

    public void setHEURE(String HEURE) {
        this.HEURE = HEURE;
    }

    public String getICON() {
        return ICON;
    }

    public void setICON(String ICON) {
        this.ICON = ICON;
    }

    public String getCONDITION() {
        return CONDITION;
    }

    public void setCONDITION(String CONDITION) {
        this.CONDITION = CONDITION;
    }

    public String getCONDITION_KEY() {
        return CONDITION_KEY;
    }

    public void setCONDITION_KEY(String CONDITION_KEY) {
        this.CONDITION_KEY = CONDITION_KEY;
    }

    public double getTMP2m() {
        return TMP2m;
    }

    public void setTMP2m(double TMP2m) {
        this.TMP2m = TMP2m;
    }

    public double getDPT2m() {
        return DPT2m;
    }

    public void setDPT2m(double DPT2m) {
        this.DPT2m = DPT2m;
    }
}
