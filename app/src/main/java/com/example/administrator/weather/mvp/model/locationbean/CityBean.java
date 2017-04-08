package com.example.administrator.weather.mvp.model.locationbean;

import com.google.gson.annotations.SerializedName;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017-3-25.
 */

public class CityBean extends DataSupport implements Serializable, IAddress {

    /**
     * mId : 205
     * name : 广州
     */

    @Column(unique = true)
    @SerializedName("id")
    private int mId;
    @Column
    @SerializedName("name")
    private String name;

    @Column
    private List<CountryBean> mCountryBeanList;
    @Column
    private int provinceId;

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

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public List<CountryBean> getCountryBeanList() {
        /*return DataSupport.where("citybean_id=?", String.valueOf(mId)).find(CountryBean.class);*/
        return mCountryBeanList;
    }

    public void setCountryBeanList(List<CountryBean> countryBeanList) {
        mCountryBeanList = countryBeanList;
    }
}
