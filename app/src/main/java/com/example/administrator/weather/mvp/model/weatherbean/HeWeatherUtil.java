package com.example.administrator.weather.mvp.model.weatherbean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

;

/**
 * Created by Administrator on 2017-3-25.
 */

public class HeWeatherUtil {

    @SerializedName("HeWeather5")
    private List<HeAllWeather5Bean> HeWeather;

    public void setHeWeather(List<HeAllWeather5Bean> heWeather) {
        HeWeather = heWeather;
    }

    public List<HeAllWeather5Bean> getHeWeather() {
        return HeWeather;
    }

}
