package com.example.administrator.weather.mvp.model.weatherbean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017-3-25.
 */

public class HourlyForecastBean {
    /**
     * cond : {"code":"100","txt":"晴"}
     * date : 2016-08-31 12:00
     * hum : 21
     * pop : 0
     * pres : 998
     * tmp : 33
     * wind : {"deg":"40","dir":"东北风","sc":"4-5","spd":"24"}
     */

    @SerializedName("cond")
    private SimpleCondBean cond;
    @SerializedName("date")
    private String date;
    @SerializedName("hum")
    private String hum;
    @SerializedName("pop")
    private String pop;
    @SerializedName("pres")
    private String pres;
    @SerializedName("tmp")
    private String tmp;
    @SerializedName("wind")
    private WindBean wind;

    public SimpleCondBean getCond() {
        return cond;
    }

    public void setCond(SimpleCondBean cond) {
        this.cond = cond;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHum() {
        return hum;
    }

    public void setHum(String hum) {
        this.hum = hum;
    }

    public String getPop() {
        return pop;
    }

    public void setPop(String pop) {
        this.pop = pop;
    }

    public String getPres() {
        return pres;
    }

    public void setPres(String pres) {
        this.pres = pres;
    }

    public String getTmp() {
        return tmp;
    }

    public void setTmp(String tmp) {
        this.tmp = tmp;
    }

    public WindBean getWind() {
        return wind;
    }

    public void setWind(WindBean wind) {
        this.wind = wind;
    }

}
