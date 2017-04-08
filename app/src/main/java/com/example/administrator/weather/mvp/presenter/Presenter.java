package com.example.administrator.weather.mvp.presenter;

import android.view.View;

import com.example.administrator.weather.AddressLoadError;
import com.example.administrator.weather.WeatherLoadError;
import com.example.administrator.weather.mvp.model.weatherbean.HeAllWeather5Bean;
import com.example.administrator.weather.mvp.model.weatherbean.NowBean;
import com.example.administrator.weather.mvp.model.wrapper.CityListWrapper;
import com.example.administrator.weather.mvp.model.wrapper.CountryListWrapper;
import com.example.administrator.weather.mvp.model.wrapper.DailyWeatherListWrapper;
import com.example.administrator.weather.mvp.model.wrapper.HourlyWeatherListWrapper;
import com.example.administrator.weather.mvp.model.wrapper.ProvinceListWrapper;
import com.example.administrator.weather.mvp.view.IMainView;

/**
 * Created by Administrator on 2017-3-25.
 */

public interface Presenter {

    void onAttach(IMainView iMainView);

    /**
     * Refresh all weather info
     *
     * @param cityIdentifier(北京/beijing/CN101010100/60.194.130.1)
     */
    void onRefreshAllWeather(String cityIdentifier);

    void onRefreshNowWeather(String cityIdentifier);

    void onRefreshHourlyWeather(String cityIdentifier);

    void onRefreshDailyWeather(String cityIdentifier);

    void onNeedToReloadWeather(String cityName);


    /**
     * get the weather
     *
     * @param heAllWeather5Bean
     */
    void onAllWeatherReady(HeAllWeather5Bean heAllWeather5Bean);

    void onNowWeatherReady(NowBean nowBean);

    void onHourlyWeatherReady(HourlyWeatherListWrapper hourlyWeatherListWrapper);

    void onDailyWeatherReady(DailyWeatherListWrapper dailyWeatherListWrapper);

    void onProvinceReady(ProvinceListWrapper provinceListWrapper);

    void onCityReady(CityListWrapper cityListWrapper);

    void onCountryReady(CountryListWrapper countryListWrapper);

    void onPrepareAddressList();
    void onAddressLoadError(AddressLoadError addressLoadError);
    void onWeatherLoadError(WeatherLoadError weatherLoadError);

    void onMenuItemClick(View clickedView,int position);
    void onMenuItemLongClick(View clickedView,int position);

    void onDetach();
}
