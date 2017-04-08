package com.example.administrator.weather.mvp.model.locationbean;

import com.google.gson.annotations.SerializedName;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by Administrator on 2017-3-26.
 */

public class CountryBean extends DataSupport implements Serializable,IAddress{

    /**
     * mId : 937
     * name : 苏州
     * weather_id : CN101190401
     */

    @Column
    @SerializedName("id")
    private int mId;
    @Column
    @SerializedName("name")
    private String name;

    @SerializedName("weather_id")
    public String weatherId;

    private int cityId;

    @Override
    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
}
