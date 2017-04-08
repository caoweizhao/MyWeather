package com.example.administrator.weather.mvp.model.weatherbean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017-3-25.
 */

public class TmpBean {
    /**
     * max : 33
     * min : 19
     */

    @SerializedName("max")
    private String max;
    @SerializedName("min")
    private String min;

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }
}
