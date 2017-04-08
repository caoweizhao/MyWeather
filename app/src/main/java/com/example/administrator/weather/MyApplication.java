package com.example.administrator.weather;

import com.example.administrator.weather.mvp.model.preference.PreferenceHelper;
import com.facebook.stetho.Stetho;

import org.litepal.LitePal;
import org.litepal.LitePalApplication;

/**
 * Created by Administrator on 2017-3-26.
 */

public class MyApplication extends LitePalApplication {

    @Override
    public void onCreate() {
        PreferenceHelper.getInstance().init(this);
        LitePal.initialize(this);
        super.onCreate();
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(
                                Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(
                                Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
}
