package com.example.administrator.weather.mvp.model.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

/**
 * Created by Administrator on 2017-3-26.
 */

public class PreferenceHelper {

    private PreferenceHelper() {
    }

    public static final String ADDRESS_KEY = "address";

    private static class PreferenceHelperHolder {
        private static final PreferenceHelper instance = new PreferenceHelper();
    }

    public static PreferenceHelper getInstance() {
        return PreferenceHelperHolder.instance;
    }

    public void init(@NonNull Context context) {
        if (mSharedPreferences == null) {
            synchronized (this) {
                if (mSharedPreferences == null) {
                    mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                }
            }
        }

    }

    private SharedPreferences mSharedPreferences;

    public void saveWeather(String cityIdentifier, String weather) {
        if (mSharedPreferences == null) {
            throw new RuntimeException("You should call init(Context context) first to init the sharePreferences!");
        }

        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(cityIdentifier, weather);
        editor.apply();
    }

    public String getWeather(String key, String defaultValue) {
        if (mSharedPreferences == null) {
            throw new RuntimeException("You should call init(Context context) first to init the sharePreferences!");
        }
        return mSharedPreferences.getString(key, defaultValue);
    }

    public void saveAddress(String address) {
        if (mSharedPreferences == null) {
            throw new RuntimeException("You should call init(Context context) first to init the sharePreferences!");
        }
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(ADDRESS_KEY, address);
        editor.apply();
    }

    public String getAddress() {
        if (mSharedPreferences == null) {
            throw new RuntimeException("You should call init(Context context) first to init the sharePreferences!");
        }
        return mSharedPreferences.getString(ADDRESS_KEY, null);
    }

    private static String NOTIFICATION_KEY = "notification_key";
    public void saveNotificationStatus(boolean isOpen){
        if (mSharedPreferences == null) {
            throw new RuntimeException("You should call init(Context context) first to init the sharePreferences!");
        }
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(NOTIFICATION_KEY,isOpen);
        editor.apply();
    }

    public boolean getNotificationStatus() {
        if (mSharedPreferences == null) {
            throw new RuntimeException("You should call init(Context context) first to init the sharePreferences!");
        }
        return mSharedPreferences.getBoolean(NOTIFICATION_KEY, false);
    }

}
