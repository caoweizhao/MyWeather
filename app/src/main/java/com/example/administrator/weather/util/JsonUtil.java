package com.example.administrator.weather.util;

import com.example.administrator.weather.mvp.model.locationbean.CityBean;
import com.example.administrator.weather.mvp.model.locationbean.CountryBean;
import com.example.administrator.weather.mvp.model.locationbean.ProvinceBean;
import com.example.administrator.weather.mvp.model.weatherbean.HeAllWeather5Bean;
import com.example.administrator.weather.mvp.model.weatherbean.HeWeatherUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by Administrator on 2017-3-25.
 */

public class JsonUtil {


    public static HeAllWeather5Bean parseWeatherJsonObject(String json) {
        Gson gson = new Gson();
        HeWeatherUtil heWeatherUtil = gson.fromJson(json, HeWeatherUtil.class);
        if (heWeatherUtil.getHeWeather() != null && heWeatherUtil.getHeWeather().size() > 0) {
            if (!heWeatherUtil.getHeWeather().get(0).getStatus().equals("ok")) {
                //TODO
                return null;
            }
        }
        return heWeatherUtil.getHeWeather().get(0);
    }

    public static List<ProvinceBean> parseProvinceJsonArray(String jsonArray) {
        Gson gson = new Gson();
        return gson.fromJson(jsonArray, new TypeToken<List<ProvinceBean>>() {
        }.getType());
    }

    public static List<CityBean> parsCityJsonArray(String jsonArray) {
        Gson gson = new Gson();
        return gson.fromJson(jsonArray, new TypeToken<List<CityBean>>() {
        }.getType());
    }

    public static List<CountryBean> parsCountryJsonArray(String jsonArray) {
        Gson gson = new Gson();
        return gson.fromJson(jsonArray, new TypeToken<List<CountryBean>>() {
        }.getType());
    }
}
