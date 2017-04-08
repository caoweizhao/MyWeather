package com.example.administrator.weather.mvp.model.helper;

import android.util.Log;

import com.example.administrator.weather.AddressLoadError;
import com.example.administrator.weather.mvp.model.Contract;
import com.example.administrator.weather.mvp.model.locationbean.CityBean;
import com.example.administrator.weather.mvp.model.locationbean.CountryBean;
import com.example.administrator.weather.mvp.model.locationbean.ProvinceBean;
import com.example.administrator.weather.mvp.model.wrapper.CityListWrapper;
import com.example.administrator.weather.mvp.model.wrapper.CountryListWrapper;
import com.example.administrator.weather.mvp.model.wrapper.ProvinceListWrapper;
import com.example.administrator.weather.util.JsonUtil;

import org.greenrobot.eventbus.EventBus;
import org.litepal.crud.DataSupport;
import org.litepal.crud.callback.FindMultiCallback;
import org.litepal.crud.callback.SaveCallback;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static org.litepal.crud.DataSupport.where;

/**
 * Created by Administrator on 2017-3-27.
 * 处理地址类的数据获取
 */

public class AddressHelper {

    private void getProvinceListFromNetWork(String url) {
        Callback callback = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("AddressHelper",e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("AddressHelper","success");
                if (response.isSuccessful()) {
                    String jsonResult = response.body().string();
                    //解析获取province数据
                    final List<ProvinceBean> provinceBeenList = JsonUtil.parseProvinceJsonArray(jsonResult);
                    //保存数据库
                    DataSupport.saveAllAsync(provinceBeenList).listen(new SaveCallback() {
                        @Override
                        public void onFinish(boolean success) {
                            if (success) {
                                EventBus.getDefault().post(new ProvinceListWrapper(provinceBeenList));
                            }
                        }
                    });
                } else {
                    EventBus.getDefault().post(new AddressLoadError());
                }

            }
        };
        OkHttpHelper.getInstance().execute(getAddressRequest(url), callback);
    }

    private void getCityListFromNetwork(final int provinceId) {
        Callback callback = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonResult = response.body().string();
                //解析获取city数据
                final List<CityBean> cityBeanList = JsonUtil.parsCityJsonArray(jsonResult);
                for (CityBean cityBean : cityBeanList) {
                    cityBean.setProvinceId(provinceId);
                }
                //保存数据库
                DataSupport.saveAllAsync(cityBeanList).listen(new SaveCallback() {
                    @Override
                    public void onFinish(boolean success) {
                        //TODO
                        if (success) {
                            //设置province的城市数据
                            ProvinceBean provinceBean = DataSupport
                                    .where("mid=?", String.valueOf(provinceId))
                                    .findFirst(ProvinceBean.class);
                            if (provinceBean != null) {
                                provinceBean.setCityBeanList(cityBeanList);
                                provinceBean.save();
                            }
                            EventBus.getDefault().post(new CityListWrapper(cityBeanList));
                        } else {
                            EventBus.getDefault().post(new AddressLoadError());
                        }

                    }
                });
            }
        };
        String url = Contract.ADDRESS_PROVINCES + "/" + provinceId;
        OkHttpHelper.getInstance().execute(getAddressRequest(url), callback);
    }

    private void getCountryListFromNetwork(final int provinceId, final int cityId) {
        OkHttpClient client = new OkHttpClient();
        Callback callback = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonResult = response.body().string();
                //解析获取country数据
                final List<CountryBean> countryBeanList = JsonUtil.parsCountryJsonArray(jsonResult);
                for (CountryBean countryBean : countryBeanList) {
                    countryBean.setCityId(cityId);
                }

                //保存数据库
                DataSupport.saveAllAsync(countryBeanList).listen(new SaveCallback() {
                    @Override
                    public void onFinish(boolean success) {
                        if (success) {
                            //TODO
                            //设置上级city的country数据
                            CityBean cityBean = DataSupport
                                    .where("mid=?", String.valueOf(cityId))
                                    .findFirst(CityBean.class);
                            cityBean.setCountryBeanList(countryBeanList);
                            cityBean.save();

                            EventBus.getDefault().post(new CountryListWrapper(countryBeanList));
                        } else {
                            //保存失败，则清空所有数据，发送失败信息
                            EventBus.getDefault().post(new AddressLoadError());
                        }
                    }
                });
            }
        };
        String url = Contract.ADDRESS_PROVINCES + "/" + provinceId + "/" + cityId;
        OkHttpHelper.getInstance().execute(getAddressRequest(url), callback);
    }

    private void getProvinceListFromDB() {
        DataSupport.findAllAsync(ProvinceBean.class).listen(new FindMultiCallback() {
            @Override
            public <T> void onFinish(List<T> t) {
                List<ProvinceBean> provinceBeanList = (List<ProvinceBean>) t;
                EventBus.getDefault().post(new ProvinceListWrapper(provinceBeanList));
            }
        });
    }

    private void getCityListFromDB(int provinceId) {
        where("provincebean_id=?", String.valueOf(provinceId))
                .findAsync(CityBean.class).listen(new FindMultiCallback() {
            @Override
            public <T> void onFinish(List<T> t) {
                List<CityBean> cityBeanList = (List<CityBean>) t;
                EventBus.getDefault().post(new CityListWrapper(cityBeanList));
            }
        });
    }

    private void getCountryListFromDB(int cityId) {
        where("citybean_id=?", String.valueOf(cityId))
                .findAsync(CountryBean.class).listen(new FindMultiCallback() {
            @Override
            public <T> void onFinish(List<T> t) {
                List<CountryBean> countryBeanList = (List<CountryBean>) t;
                EventBus.getDefault().post(new CountryListWrapper(countryBeanList));
            }
        });
    }

    private Request getAddressRequest(String url) {
        return new Request.Builder() //
                .url(url)   //
                .build();
    }

    public void getProvinceList(String url) {
        ProvinceBean provinceBean = DataSupport.findFirst(ProvinceBean.class);
        if (provinceBean == null) {
            getProvinceListFromNetWork(url);
        } else {
            getProvinceListFromDB();
        }
    }

    public void getCityList(int provinceId) {
        CityBean cityBean = DataSupport
                .where("provincebean_id=?", String.valueOf(provinceId))
                .findFirst(CityBean.class);
        if (cityBean == null) {
            getCityListFromNetwork(provinceId);
        } else {
            getCityListFromDB(provinceId);
        }
    }

    public void getCountryList(int provinceId, int cityId) {
        CountryBean countryBean = DataSupport
                .where("citybean_id=?", String.valueOf(cityId))
                .findFirst(CountryBean.class);
        if (countryBean == null) {
            getCountryListFromNetwork(provinceId, cityId);
        } else {
            getCountryListFromDB(cityId);
        }
    }


}
