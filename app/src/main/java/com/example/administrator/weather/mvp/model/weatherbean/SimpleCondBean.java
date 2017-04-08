package com.example.administrator.weather.mvp.model.weatherbean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Administrator on 2017-3-25.
 */

public class SimpleCondBean implements Serializable{
    @SerializedName("code")
    private String code;
    @SerializedName("txt")
    private String txt;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }
}
