package com.example.administrator.weather.mvp.model.weatherbean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Administrator on 2017-3-25.
 */

public class NowBean implements Serializable{
    /**
     * cond : {"code":"104","txt":"阴"}
     * fl : 11
     * hum : 31
     * pcpn : 0
     * pres : 1025
     * tmp : 13
     * vis : 10
     * wind : {"deg":"40","dir":"东北风","sc":"4-5","spd":"24"}
     */

    @SerializedName("cond")
    private SimpleCondBean cond;
    @SerializedName("fl")
    private String fl;
    @SerializedName("hum")
    private String hum;
    @SerializedName("pcpn")
    private String pcpn;
    @SerializedName("pres")
    private String pres;
    @SerializedName("tmp")
    private String tmp;
    @SerializedName("vis")
    private String vis;
    @SerializedName("wind")
    private WindBean wind;

    public SimpleCondBean getCond() {
        return cond;
    }

    public void setCond(SimpleCondBean cond) {
        this.cond = cond;
    }

    public String getFl() {
        return fl;
    }

    public void setFl(String fl) {
        this.fl = fl;
    }

    public String getHum() {
        return hum;
    }

    public void setHum(String hum) {
        this.hum = hum;
    }

    public String getPcpn() {
        return pcpn;
    }

    public void setPcpn(String pcpn) {
        this.pcpn = pcpn;
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

    public String getVis() {
        return vis;
    }

    public void setVis(String vis) {
        this.vis = vis;
    }

    public WindBean getWind() {
        return wind;
    }

    public void setWind(WindBean wind) {
        this.wind = wind;
    }
}
