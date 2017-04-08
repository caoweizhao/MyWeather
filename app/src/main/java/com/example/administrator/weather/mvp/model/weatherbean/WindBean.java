package com.example.administrator.weather.mvp.model.weatherbean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Administrator on 2017-3-25.
 */

public class WindBean implements Serializable {
    /**
     * deg : 342
     * dir : 北风
     * sc : 3-4
     * spd : 10
     */

    @SerializedName("deg")
    private String deg;
    @SerializedName("dir")
    private String dir;
    @SerializedName("sc")
    private String sc;
    @SerializedName("spd")
    private String spd;

    public String getDeg() {
        return deg;
    }

    public void setDeg(String deg) {
        this.deg = deg;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getSc() {
        return sc;
    }

    public void setSc(String sc) {
        this.sc = sc;
    }

    public String getSpd() {
        return spd;
    }

    public void setSpd(String spd) {
        this.spd = spd;
    }
}
