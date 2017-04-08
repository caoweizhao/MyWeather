package com.example.administrator.weather.mvp.model.helper;

import android.util.Log;

import com.example.administrator.weather.mvp.model.Contract;
import com.example.administrator.weather.mvp.model.ModelImpl;
import com.example.administrator.weather.mvp.model.preference.PreferenceHelper;
import com.example.administrator.weather.mvp.model.weatherbean.HeAllWeather5Bean;
import com.example.administrator.weather.util.JsonUtil;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.administrator.weather.mvp.model.ModelImpl.TYPE_WEATHER_ALL;
import static com.example.administrator.weather.mvp.model.ModelImpl.TYPE_WEATHER_DAILY;
import static com.example.administrator.weather.mvp.model.ModelImpl.TYPE_WEATHER_HOURLY;
import static com.example.administrator.weather.mvp.model.ModelImpl.TYPE_WEATHER_NOW;
import static com.example.administrator.weather.util.JsonUtil.parseWeatherJsonObject;

/**
 * Created by Administrator on 2017-3-27.
 */

public class WeatherHelper {
    public void updateWeather(String city, @ModelImpl.TYPE_WEATHER int type) {
        Log.d("WeatherHelper", city + "---- getWeather from network!");
        final String mCity = city;
        final int mType = type;
        Callback callback = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonResult = response.body().string();
                Log.d("WeatherHelper",jsonResult);
                PreferenceHelper.getInstance().saveWeather(mCity, jsonResult);
                HeAllWeather5Bean heAllWeather5Bean = parseWeatherJsonObject(jsonResult);
                //TODO
                if (heAllWeather5Bean != null) {
                    switch (mType) {
                        case TYPE_WEATHER_ALL:
                            EventBus.getDefault().post(heAllWeather5Bean);
                            break;
                        case TYPE_WEATHER_DAILY:
                            EventBus.getDefault().post(heAllWeather5Bean.getDailyForecast());
                            break;
                        case TYPE_WEATHER_HOURLY:
                            EventBus.getDefault().post(heAllWeather5Bean.getHourlyForecast());
                            break;
                        case TYPE_WEATHER_NOW:
                            EventBus.getDefault().post(heAllWeather5Bean.getNow());
                            break;
                    }
                }
            }
        };
        RequestBody requestBody = new FormBody.Builder()
                .add("city", city)
                .add("key", Contract.KEY)
                .build();
        Request request = new Request.Builder()
                .url(Contract.WEATHER_ALL)
                .post(requestBody)
                .build();

        OkHttpHelper.getInstance().execute(request, callback);
    }

    public void reloadWeather(final String cityName) {
        Log.d("WeatherHelper", cityName + "---- getWeather from local!");
        final String localData = PreferenceHelper.getInstance().getWeather(cityName, null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                HeAllWeather5Bean heAllWeather5Bean = JsonUtil.parseWeatherJsonObject(localData);
                EventBus.getDefault().post(heAllWeather5Bean);
            }
        }).start();
    }
}
