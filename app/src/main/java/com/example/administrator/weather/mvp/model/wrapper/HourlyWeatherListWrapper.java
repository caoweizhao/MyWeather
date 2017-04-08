package com.example.administrator.weather.mvp.model.wrapper;

import com.example.administrator.weather.mvp.model.weatherbean.HourlyForecastBean;

import java.util.List;

/**
 * Created by Administrator on 2017-3-27.
 */

public class HourlyWeatherListWrapper {
    private List<HourlyForecastBean> mHourlyForecastBeanList;

    public HourlyWeatherListWrapper(List<HourlyForecastBean> list) {
        mHourlyForecastBeanList = list;
    }

    public List<HourlyForecastBean> getHourlyForecastBeanList() {
        return mHourlyForecastBeanList;
    }

    public void setHourlyForecastBeanList(List<HourlyForecastBean> hourlyForecastBeanList) {
        mHourlyForecastBeanList = hourlyForecastBeanList;
    }
}
