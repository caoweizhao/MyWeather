package com.example.administrator.weather.mvp.presenter;


import android.view.View;

import com.example.administrator.weather.AddressLoadError;
import com.example.administrator.weather.WeatherLoadError;
import com.example.administrator.weather.mvp.model.Contract;
import com.example.administrator.weather.mvp.model.Model;
import com.example.administrator.weather.mvp.model.ModelImpl;
import com.example.administrator.weather.mvp.model.weatherbean.HeAllWeather5Bean;
import com.example.administrator.weather.mvp.model.weatherbean.NowBean;
import com.example.administrator.weather.mvp.model.wrapper.CityListWrapper;
import com.example.administrator.weather.mvp.model.wrapper.CountryListWrapper;
import com.example.administrator.weather.mvp.model.wrapper.DailyWeatherListWrapper;
import com.example.administrator.weather.mvp.model.wrapper.HourlyWeatherListWrapper;
import com.example.administrator.weather.mvp.model.wrapper.ProvinceListWrapper;
import com.example.administrator.weather.mvp.view.IMainView;
import com.example.administrator.weather.mvp.view.MainActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Administrator on 2017/03/25
 */

public class PresenterImpl implements Presenter {

    private Model mModel;
    private IMainView mIMainView;

    @Override
    public void onAttach(IMainView iMainView) {
        mIMainView = iMainView;
        mModel = new ModelImpl();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onRefreshAllWeather(String cityIdentifier) {
        mModel.updateWeather(cityIdentifier, ModelImpl.TYPE_WEATHER_ALL);
        mIMainView.showLoading();
    }

    @Override
    public void onRefreshNowWeather(String cityIdentifier) {
        mModel.updateWeather(cityIdentifier, ModelImpl.TYPE_WEATHER_NOW);
    }

    @Override
    public void onRefreshHourlyWeather(String cityIdentifier) {
        mModel.updateWeather(cityIdentifier, ModelImpl.TYPE_WEATHER_HOURLY);
    }

    @Override
    public void onRefreshDailyWeather(String cityIdentifier) {
        mModel.updateWeather(cityIdentifier, ModelImpl.TYPE_WEATHER_DAILY);
    }

    @Override
    public void onNeedToReloadWeather(String cityName) {
        mModel.reloadWeather(cityName);
        mIMainView.showLoading();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    @Override
    public void onAllWeatherReady(HeAllWeather5Bean heAllWeather5Bean) {
        mIMainView.stopRefresh();
        mIMainView.updateUI(heAllWeather5Bean);
    }

    @Subscribe
    @Override
    public void onNowWeatherReady(NowBean nowBean) {

    }

    @Subscribe
    @Override
    public void onHourlyWeatherReady(HourlyWeatherListWrapper hourlyWeatherListWrapper) {

    }

    @Subscribe
    @Override
    public void onDailyWeatherReady(DailyWeatherListWrapper dailyWeatherListWrapper) {
    }

    @Subscribe
    @Override
    public void onProvinceReady(ProvinceListWrapper provinceListWrapper) {
    }

    @Subscribe
    @Override
    public void onCityReady(CityListWrapper cityListWrapper) {
    }

    @Subscribe
    @Override
    public void onCountryReady(CountryListWrapper countryListWrapper) {
    }

    @Override
    public void onPrepareAddressList() {
        mModel.getProvinceList(Contract.ADDRESS_PROVINCES);
    }

    @Subscribe
    @Override
    public void onAddressLoadError(AddressLoadError addressLoadError) {
        // TODO: 2017-4-1
        mIMainView.showAddressLoadFail();
        mIMainView.stopRefresh();
    }

    @Override
    public void onWeatherLoadError(WeatherLoadError weatherLoadError) {
        mIMainView.showWeatherLoadFail();
        mIMainView.stopRefresh();
    }

    @Override
    public void onMenuItemClick(View clickedView, int position) {
        switch (position) {
            case 0:
                ((MainActivity)mIMainView).startSelectActivityForResult();
                break;
            case 1:
                mIMainView.toggleNotification();
                break;
        }
    }

    @Override
    public void onMenuItemLongClick(View clickedView, int position) {

    }

    @Override
    public void onDetach() {
        EventBus.getDefault().unregister(this);
        mIMainView = null;
    }

}