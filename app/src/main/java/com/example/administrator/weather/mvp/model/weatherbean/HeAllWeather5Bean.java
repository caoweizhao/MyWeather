package com.example.administrator.weather.mvp.model.weatherbean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2017-3-25.
 */

public class HeAllWeather5Bean {
    /**
     * basic : {"city":"北京","cnty":"中国","id":"CN101010100","lat":"39.904000","lon":"116.391000","prov":"北京","update":{"loc":"2016-08-31 11:52","utc":"2016-08-31 03:52"}}
     * daily_forecast : [{"astro":{"mr":"04:19","ms":"18:07","sr":"05:41","ss":"18:47"},"cond":{"code_d":"100","code_n":"104","txt_d":"晴","txt_n":"阴"},"date":"2016-08-31","hum":"17","pcpn":"0.0","pop":"1","pres":"997","tmp":{"max":"33","min":"19"},"vis":"10","wind":{"deg":"342","dir":"北风","sc":"3-4","spd":"10"}}]
     * status : ok
     */
    @SerializedName("aqi")
    private AQIBean aqi;
    @SerializedName("basic")
    private BasicBean basic;
    @SerializedName("status")
    private String status;
    @SerializedName("daily_forecast")
    private List<DailyForecastBean> dailyForecast;
    @SerializedName("hourly_forecast")
    private List<HourlyForecastBean> hourlyForecast;
    @SerializedName("now")
    private NowBean now;
    @SerializedName("suggestion")
    private SuggestionBean suggestion;

    public BasicBean getBasic() {
        return basic;
    }

    public void setBasic(BasicBean basic) {
        this.basic = basic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DailyForecastBean> getDailyForecast() {
        return dailyForecast;
    }

    public void setDailyForecast(List<DailyForecastBean> dailyForecast) {
        this.dailyForecast = dailyForecast;
    }

    public List<HourlyForecastBean> getHourlyForecast() {
        return hourlyForecast;
    }

    public void setHourlyForecast(List<HourlyForecastBean> hourlyForecast) {
        this.hourlyForecast = hourlyForecast;
    }

    public NowBean getNow() {
        return now;
    }

    public void setNow(NowBean now) {
        this.now = now;
    }

    public SuggestionBean getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(SuggestionBean suggestion) {
        this.suggestion = suggestion;
    }

    public AQIBean getAqi() {
        return aqi;
    }

    public void setAqi(AQIBean aqi) {
        this.aqi = aqi;
    }
}
