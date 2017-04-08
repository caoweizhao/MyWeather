package com.example.administrator.weather.mvp.model;


import android.support.annotation.IntDef;

import com.example.administrator.weather.mvp.model.helper.AddressHelper;
import com.example.administrator.weather.mvp.model.helper.WeatherHelper;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Administrator on 2017/03/25
 */

public class ModelImpl implements Model {
    public static final int TYPE_WEATHER_ALL = 0;
    public static final int TYPE_WEATHER_DAILY = 1;
    public static final int TYPE_WEATHER_HOURLY = 2;
    public static final int TYPE_WEATHER_NOW = 3;

    private AddressHelper mAddressHelper = new AddressHelper();
    private WeatherHelper mWeatherHelper = new WeatherHelper();

    @Retention(RetentionPolicy.CLASS)
    @IntDef({
            TYPE_WEATHER_ALL,
            TYPE_WEATHER_DAILY,
            TYPE_WEATHER_HOURLY,
            TYPE_WEATHER_NOW
    })
    public @interface TYPE_WEATHER {
    }

    @Override
    public void updateWeather(String city, @TYPE_WEATHER int type) {
        mWeatherHelper.updateWeather(city, type);
    }

    @Override
    public void reloadWeather(String cityName) {
        mWeatherHelper.reloadWeather(cityName);
    }

    @Override
    public void getProvinceList(String url) {
        mAddressHelper.getProvinceList(url);
    }

    @Override
    public void getCityList(final int provinceId) {
        mAddressHelper.getCityList(provinceId);
    }

    @Override
    public void getCountryList(int provinceId, final int cityId) {
        mAddressHelper.getCountryList(provinceId, cityId);
    }
}