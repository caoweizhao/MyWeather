package com.example.administrator.weather.mvp.model.weatherbean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017-3-25.
 */

public class UpdateBean {
    /**
     * loc : 2016-08-31 11:52
     * utc : 2016-08-31 03:52
     */

    @SerializedName("loc")
    private String loc;
    @SerializedName("utc")
    private String utc;

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getUtc() {
        return utc;
    }

    public void setUtc(String utc) {
        this.utc = utc;
    }
}
