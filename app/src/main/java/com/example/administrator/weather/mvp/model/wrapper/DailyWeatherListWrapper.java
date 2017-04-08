package com.example.administrator.weather.mvp.model.wrapper;

import com.example.administrator.weather.mvp.model.weatherbean.DailyForecastBean;

import java.util.List;

/**
 * Created by Administrator on 2017-3-27.
 */

public class DailyWeatherListWrapper {
    private List<DailyForecastBean> mDailyForecastBeenList;

    public DailyWeatherListWrapper(List<DailyForecastBean> list) {
        mDailyForecastBeenList = list;
    }

    public List<DailyForecastBean> getHourlyForecastBeanList() {
        return mDailyForecastBeenList;
    }

    public void setHourlyForecastBeanList(List<DailyForecastBean> hourlyForecastBeanList) {
        mDailyForecastBeenList = hourlyForecastBeanList;
    }
}
