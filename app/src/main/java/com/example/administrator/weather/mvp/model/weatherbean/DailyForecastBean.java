package com.example.administrator.weather.mvp.model.weatherbean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017-3-25.
 */

public class DailyForecastBean {
    /**
     * astro : {"mr":"04:19","ms":"18:07","sr":"05:41","ss":"18:47"}
     * cond : {"code_d":"100","code_n":"104","txt_d":"晴","txt_n":"阴"}
     * date : 2016-08-31
     * hum : 17
     * pcpn : 0.0
     * pop : 1
     * pres : 997
     * tmp : {"max":"33","min":"19"}
     * vis : 10
     * wind : {"deg":"342","dir":"北风","sc":"3-4","spd":"10"}
     */
    @SerializedName("cond")
    private DailyCondBean cond;
    @SerializedName("date")
    private String date;
    @SerializedName("hum")
    private String hum;
    @SerializedName("pcpn")
    private String pcpn;
    @SerializedName("pop")
    private String pop;
    @SerializedName("pres")
    private String pres;
    @SerializedName("tmp")
    private TmpBean tmp;
    @SerializedName("vis")
    private String vis;
    @SerializedName("wind")
    private WindBean wind;

    public DailyCondBean getCond() {
        return cond;
    }

    public void setCond(DailyCondBean cond) {
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

    public String getPcpn() {
        return pcpn;
    }

    public void setPcpn(String pcpn) {
        this.pcpn = pcpn;
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

    public TmpBean getTmp() {
        return tmp;
    }

    public void setTmp(TmpBean tmp) {
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
