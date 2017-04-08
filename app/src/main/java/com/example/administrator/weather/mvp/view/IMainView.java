package com.example.administrator.weather.mvp.view;

import com.example.administrator.weather.mvp.model.weatherbean.HeAllWeather5Bean;

/**
 * Created by Administrator on 2017/03/25
 */

public interface IMainView {
    void stopRefresh();

    void updateUI(HeAllWeather5Bean heAllWeather5Bean);

    void showLoading();

    void showAddressLoadFail();

    void showAddressLoadSuccess();

    void showWeatherLoadFail();

    void showWeatherLoadSuccess();

    void toggleNotification();

}