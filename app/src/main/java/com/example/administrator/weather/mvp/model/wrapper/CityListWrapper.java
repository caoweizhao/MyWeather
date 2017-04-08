package com.example.administrator.weather.mvp.model.wrapper;

import com.example.administrator.weather.mvp.model.locationbean.CityBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017-3-27.
 */

public class CityListWrapper implements Serializable{
    private List<CityBean> mCityBeanList;

    public CityListWrapper(List<CityBean> list) {
        mCityBeanList = list;
    }

    public List<CityBean> getCityBeanList() {
        return mCityBeanList;
    }

    public void setCityBeanList(List<CityBean> cityBeanList) {
        mCityBeanList = cityBeanList;
    }
}
