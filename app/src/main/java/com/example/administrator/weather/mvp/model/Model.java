package com.example.administrator.weather.mvp.model;

/**
 * Created by Administrator on 2017/03/25
 */

public interface Model {

    void updateWeather(String city, @ModelImpl.TYPE_WEATHER int type);

    void reloadWeather(String cityName);

    void getProvinceList(String url);

    void getCityList(int provinceId);

    void getCountryList(int provinceId, int cityId);


}