package com.example.administrator.weather.mvp.model.locationbean;

import com.google.gson.annotations.SerializedName;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017-3-25.
 */

public class ProvinceBean extends DataSupport implements Serializable, IAddress {

    /**
     * mId : 1
     * name : 北京
     */

    @Column
    @SerializedName("id")
    private int mId;
    @Column
    @SerializedName("name")
    private String name;
    private List<CityBean> mCityBeanList;

    @Override
    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CityBean> getCityBeanList() {
        /*return DataSupport.where("provincebean_id=?", String.valueOf(mId)).find(CityBean.class);*/
        return mCityBeanList;
    }

    public void setCityBeanList(List<CityBean> cityBeanList) {
        mCityBeanList = cityBeanList;
    }
}
