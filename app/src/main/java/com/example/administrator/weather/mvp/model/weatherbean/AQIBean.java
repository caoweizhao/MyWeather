package com.example.administrator.weather.mvp.model.weatherbean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017-3-25.
 */

public class AQIBean {
    /**
     * city : {"aqi":"19","co":"1","no2":"30","o3":"37","pm10":"19","pm25":"9","qlty":"优","so2":"7"}
     */

    @SerializedName("city")
    private CityBean city;

    public CityBean getCity() {
        return city;
    }

    public void setCity(CityBean city) {
        this.city = city;
    }
}
