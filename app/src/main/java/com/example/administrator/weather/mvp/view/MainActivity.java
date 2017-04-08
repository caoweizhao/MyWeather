package com.example.administrator.weather.mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.weather.AddressLoadError;
import com.example.administrator.weather.R;
import com.example.administrator.weather.mvp.model.MyService;
import com.example.administrator.weather.mvp.model.preference.PreferenceHelper;
import com.example.administrator.weather.mvp.model.weatherbean.AQIBean;
import com.example.administrator.weather.mvp.model.weatherbean.AirBean;
import com.example.administrator.weather.mvp.model.weatherbean.ComfBean;
import com.example.administrator.weather.mvp.model.weatherbean.CwBean;
import com.example.administrator.weather.mvp.model.weatherbean.DailyForecastBean;
import com.example.administrator.weather.mvp.model.weatherbean.DrsgBean;
import com.example.administrator.weather.mvp.model.weatherbean.FluBean;
import com.example.administrator.weather.mvp.model.weatherbean.HeAllWeather5Bean;
import com.example.administrator.weather.mvp.model.weatherbean.HourlyForecastBean;
import com.example.administrator.weather.mvp.model.weatherbean.NowBean;
import com.example.administrator.weather.mvp.model.weatherbean.SportBean;
import com.example.administrator.weather.mvp.model.weatherbean.SuggestionBean;
import com.example.administrator.weather.mvp.model.weatherbean.TravBean;
import com.example.administrator.weather.mvp.model.weatherbean.UpdateBean;
import com.example.administrator.weather.mvp.model.weatherbean.UvBean;
import com.example.administrator.weather.mvp.presenter.Presenter;
import com.example.administrator.weather.mvp.presenter.PresenterImpl;
import com.example.administrator.weather.util.ToastUtil;
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemClickListener;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemLongClickListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;


public class MainActivity extends AppCompatActivity implements IMainView {

    public static final String DEGREES = "℃";
    //ImageView mCityImg;

    Map<String, Integer> icons = new HashMap<>();
    @BindView(R.id.current_cond_toolbar)
    ImageView mCurrentCondToolbar;
    @BindView(R.id.first_hour_desc)
    TextView mFirstHourDesc;
    @BindView(R.id.second_hour_desc)
    TextView mSecondHourDesc;

    {
        icons.put("100", R.drawable.ic_sunny);
        icons.put("101", R.drawable.ic_cloudy);
        icons.put("102", R.drawable.ic_little_cloudy);
        icons.put("103", R.drawable.ic_sunny_cloudy);
        icons.put("104", R.drawable.ic_overcast);
        icons.put("200", R.drawable.ic_windy);
        icons.put("201", R.drawable.ic_windy);
        icons.put("202", R.drawable.ic_windy);
        icons.put("203", R.drawable.ic_windy);
        icons.put("204", R.drawable.ic_windy);
        icons.put("205", R.drawable.ic_windy);
        icons.put("206", R.drawable.ic_windy);
        icons.put("207", R.drawable.ic_windy);
        icons.put("208", R.drawable.ic_windy);
        icons.put("209", R.drawable.ic_windy);
        icons.put("210", R.drawable.ic_windy);
        icons.put("211", R.drawable.ic_windy);
        icons.put("212", R.drawable.ic_tornado);
        icons.put("213", R.drawable.ic_windy);
        icons.put("300", R.drawable.ic_little_rain);
        icons.put("305", R.drawable.ic_little_rain);
        icons.put("302", R.drawable.ic_thunder_rain);
        icons.put("303", R.drawable.ic_thunder_rain);
        icons.put("304", R.drawable.ic_hail);
        icons.put("305", R.drawable.ic_little_rain);
        icons.put("306", R.drawable.ic_strong_rain);
        icons.put("307", R.drawable.ic_severe_rain);
        icons.put("308", R.drawable.ic_severe_rain);
        icons.put("309", R.drawable.ic_drizzle);
        icons.put("310", R.drawable.ic_severe_rain);
        icons.put("311", R.drawable.ic_ex_strong_rain);
        icons.put("312", R.drawable.ic_ex_strong_rain);
        icons.put("313", R.drawable.ic_hail);
        icons.put("400", R.drawable.ic_snow);
        icons.put("401", R.drawable.ic_shower_snow);
        icons.put("402", R.drawable.ic_strong_snow);
        icons.put("403", R.drawable.ic_strong_snow);
        icons.put("404", R.drawable.ic_sleet);
        icons.put("405", R.drawable.ic_sleet);
        icons.put("406", R.drawable.ic_sleet);
        icons.put("407", R.drawable.ic_snow_flurry);
        icons.put("500", R.drawable.ic_mist);
        icons.put("501", R.drawable.ic_foggy);
        icons.put("502", R.drawable.ic_haze);
        icons.put("503", R.drawable.ic_sand);
        icons.put("504", R.drawable.ic_dust);
        icons.put("507", R.drawable.ic_duststorm);
        icons.put("508", R.drawable.ic_sandstorm);
        icons.put("901", R.drawable.ic_cold);
        icons.put("900", R.drawable.ic_hot);


    }

    @BindView(R.id.toolbar_main)
    Toolbar mToolbar;
    @BindView(R.id.degree_text_view)
    TextView mDegreeTextView;
    @BindView(R.id.cond_txt)
    TextView mCondTxt;
    @BindView(R.id.publish_time)
    TextView mPublishTime;
    @BindView(R.id.current_tmp_text)
    TextView mCurrentTmpText;
    @BindView(R.id.vis_text)
    TextView mVisText;
    @BindView(R.id.hum_text)
    TextView mHumText;
    @BindView(R.id.wind_sc_text)
    TextView mWindScText;
    @BindView(R.id.first_hour_time)
    TextView mFirstHourTime;
    @BindView(R.id.first_hour_img)
    ImageView mFirstHourImg;
    @BindView(R.id.first_hour_tmp)
    TextView mFirstHourTmp;
    @BindView(R.id.second_hour_time)
    TextView mSecondHourTime;
    @BindView(R.id.second_hour_img)
    ImageView mSecondHourImg;
    @BindView(R.id.second_hour_tmp)
    TextView mSecondHourTmp;
    /*@BindView(R.id.third_hour_time)
    TextView mThirdHourTime;
    @BindView(R.id.third_hour_img)
    ImageView mThirdHourImg;
    @BindView(R.id.third_hour_tmp)
    TextView mThirdHourTmp;*/
    @BindView(R.id.first_day_time)
    TextView mFirstDayTime;
    @BindView(R.id.first_day_img)
    ImageView mFirstDayImg;
    @BindView(R.id.first_day_tmp_min)
    TextView mFirstDayTmpMin;
    @BindView(R.id.first_day_tmp_max)
    TextView mFirstDayTmpMax;
    @BindView(R.id.second_day_time)
    TextView mSecondDayTime;
    @BindView(R.id.second_day_img)
    ImageView mSecondDayImg;
    @BindView(R.id.second_day_tmp_min)
    TextView mSecondDayTmpMin;
    @BindView(R.id.second_day_tmp_max)
    TextView mSecondDayTmpMax;
    @BindView(R.id.third_day_time)
    TextView mThirdDayTime;
    @BindView(R.id.third_day_img)
    ImageView mThirdDayImg;
    @BindView(R.id.third_day_tmp_min)
    TextView mThirdDayTmpMin;
    @BindView(R.id.third_day_tmp_max)
    TextView mThirdDayTmpMax;
    @BindView(R.id.quality_text_view)
    TextView mQualityTextView;
    @BindView(R.id.aqi_text)
    TextView mAqiText;
    @BindView(R.id.pm25_text)
    TextView mPm25Text;
    @BindView(R.id.air_point_bref)
    TextView mAirPointBref;
    @BindView(R.id.air_point_txt)
    TextView mAirPointTxt;
    @BindView(R.id.comfortable_point_bref)
    TextView mComfortablePointBref;
    @BindView(R.id.comfortable_point_txt)
    TextView mComfortablePointTxt;
    @BindView(R.id.cw_point_bref)
    TextView mCwPointBref;
    @BindView(R.id.cw_point_txt)
    TextView mCwPointTxt;
    @BindView(R.id.trav_point_bref)
    TextView mTravPointBref;
    @BindView(R.id.trav_point_txt)
    TextView mTravPointTxt;
    @BindView(R.id.sport_point_bref)
    TextView mSportPointBref;
    @BindView(R.id.sport_point_txt)
    TextView mSportPointTxt;
    @BindView(R.id.drsg_point_bref)
    TextView mDrsgPointBref;
    @BindView(R.id.drsg_point_txt)
    TextView mDrsgPointTxt;
    @BindView(R.id.flu_point_bref)
    TextView mFluPointBref;
    @BindView(R.id.flu_point_txt)
    TextView mFluPointTxt;
    @BindView(R.id.uv_point_bref)
    TextView mUvPointBref;
    @BindView(R.id.uv_point_txt)
    TextView mUvPointTxt;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;

    private Presenter mPresenter;
    private String mCountryName;

    private MenuParams menuParams = new MenuParams();
    private List<MenuObject> menuObjects = new ArrayList<>();

    private int actionBarSize;
    private ContextMenuDialogFragment mMenuDialogFragment;

    private boolean notificationOn = false;

    private static final int SELECT_ADDRESS_REQUEST = 0;

    private NowBean mNowBean;
    String localTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initOptionMenu();
        setSupportActionBar(mToolbar);
        mToolbar.post(new Runnable() {
            @Override
            public void run() {
                actionBarSize = mToolbar.getMeasuredHeight();
                menuParams.setActionBarSize(actionBarSize);
            }
        });
        mPresenter = new PresenterImpl();
        mPresenter.onAttach(this);
        initEvent();

        mCountryName = PreferenceHelper.getInstance().getAddress();
        //无历史地址数据,获取地址
        if (mCountryName == null) {
            startSelectActivityForResult();
        }
        //有历史地址数据
        else {
            mCollapsingToolbarLayout.setTitle(mCountryName);
            //有历史天气数据,加载历史数据
            if (PreferenceHelper.getInstance().getWeather(mCountryName, null) != null) {
                mPresenter.onNeedToReloadWeather(mCountryName);
            }
            //没有历史天气数据，重新加载
            else {
                mPresenter.onRefreshAllWeather(mCountryName);
            }
        }
    }

    public void startSelectActivityForResult() {
        Intent intent = new Intent(MainActivity.this, SelectAddressActivity.class);
        startActivityForResult(intent, SELECT_ADDRESS_REQUEST);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MainActivity", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MainActivity", "onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainActivity", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainActivity", "onStop");
    }

    private void initEvent() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (mCountryName == null) {
                    showAddressDialog();
                } else {
                    mPresenter.onRefreshAllWeather(mCountryName);
                }
            }
        });
    }

    private void initOptionMenu() {
        MenuObject close = new MenuObject("关闭");
        close.setResource(R.drawable.ic_close_black_24dp);

        MenuObject manageCountry = new MenuObject("城市管理");
        manageCountry.setResource(R.drawable.ic_manage_city_black_24dp);
        manageCountry.setMenuTextAppearanceStyle(R.style.OptionTextAppearance);

        boolean isNotificationOpen = PreferenceHelper.getInstance().getNotificationStatus();

        MenuObject toggleNotification = new MenuObject("开启通知栏");
        toggleNotification.setResource(R.drawable.ic_notification_off);
        toggleNotification.setMenuTextAppearanceStyle(R.style.OptionTextAppearance);
        if(isNotificationOpen){
            toggleNotification.setTitle("关闭通知栏");
            notificationOn = true;
        }

        menuObjects.add(manageCountry);
        menuObjects.add(toggleNotification);
        menuObjects.add(close);

        menuParams.setMenuObjects(menuObjects);
        menuParams.setClosableOutside(true);
        menuParams.setClipToPadding(false);
        menuParams.setFitsSystemWindow(true);

        // set other settings to meet your needs
        mMenuDialogFragment = ContextMenuDialogFragment.newInstance(menuParams);
        mMenuDialogFragment.setItemClickListener(new OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(View clickedView, int position) {
               mPresenter.onMenuItemClick(clickedView,position);
            }
        });
        mMenuDialogFragment.setItemLongClickListener(new OnMenuItemLongClickListener() {
            @Override
            public void onMenuItemLongClick(View clickedView, int position) {
                mPresenter.onMenuItemLongClick(clickedView,position);
            }
        });
    }

    @Override
    public void toggleNotification() {
        if (notificationOn) {
            closeNotification();
            menuObjects.get(1).setTitle("开启通知栏");
            notificationOn = false;
        } else {
            openNotification();
            menuObjects.get(1).setTitle("关闭通知栏");
            notificationOn = true;
        }

    }

    private void closeNotification() {
        // TODO: 2017-4-1
        Intent intent = new Intent(this,MyService.class);
        stopService(intent);
        PreferenceHelper.getInstance().saveNotificationStatus(false);
    }

    private void openNotification() {
        //// TODO: 2017-4-1
        Intent intent = new Intent(this,MyService.class);
        intent.putExtra("nowbean",mNowBean);
        intent.putExtra("icon",icons.get(mNowBean.getCond().getCode()));
        intent.putExtra("country",mCountryName);
        startService(intent);
    }

    private void showAddressDialog() {
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.NORMAL_TYPE);
        sweetAlertDialog.setTitleText("地址缺失");
        sweetAlertDialog.setContentText("当前未选择地址，是否前往选择？");
        sweetAlertDialog.setConfirmText("是");
        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismissWithAnimation();
                startSelectActivityForResult();
            }
        });
        sweetAlertDialog.setCancelText("下次再说");
        sweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                mPresenter.onAddressLoadError(new AddressLoadError());
                sweetAlertDialog.dismissWithAnimation();
            }
        });
        sweetAlertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mMenuDialogFragment.show(getSupportFragmentManager(), "MenuDialogFragment");
        return true;
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void stopRefresh() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void updateUI(HeAllWeather5Bean heAllWeather5Bean) {
        // TODO: 2017-4-1
        UpdateBean updateBean = heAllWeather5Bean.getBasic().getUpdate();
        localTime = updateBean.getLoc();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf4 = new SimpleDateFormat("MM-dd");

        try {
            Date date1 = sdf.parse(localTime);
            localTime = sdf2.format(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        mPublishTime.setText(localTime + " 发布");

        //AQI
        AQIBean aqiBean = heAllWeather5Bean.getAqi();
        mAqiText.setText(aqiBean.getCity().getAqi());
        mPm25Text.setText(aqiBean.getCity().getPm25());

        //NOW
        mNowBean = heAllWeather5Bean.getNow();
        mCurrentTmpText.setText(mNowBean.getTmp() + DEGREES);
        mVisText.setText("能见度：" + mNowBean.getVis());
        mHumText.setText("相对湿度：" + mNowBean.getHum());
        mWindScText.setText("风力等级：" + mNowBean.getWind().getSc());
        mCondTxt.setText(mNowBean.getCond().getTxt());
        mDegreeTextView.setText(mNowBean.getTmp() + DEGREES);
        mCurrentCondToolbar.setImageResource(icons.get(mNowBean.getCond().getCode()));


        //HOURLY
       try{
           List<HourlyForecastBean> hourlyForecastBeanList = heAllWeather5Bean.getHourlyForecast();
           HourlyForecastBean hour1 = hourlyForecastBeanList.get(0);
           HourlyForecastBean hour2 = hourlyForecastBeanList.get(1);
           String h1 = "", h2 = "";
           try {
               Date date1 = sdf.parse(hour1.getDate());
               h1 = sdf2.format(date1);
               Date date2 = sdf.parse(hour2.getDate());
               h2 = sdf2.format(date2);
           } catch (ParseException e) {
               e.printStackTrace();
           }
           mFirstHourTime.setText(h1);
           mFirstHourTmp.setText(hour1.getTmp() + DEGREES);
           mFirstHourImg.setImageResource(icons.get(hour1.getCond().getCode()));
           mFirstHourDesc.setText(hour1.getCond().getTxt());

           mSecondHourTime.setText(h2);
           mSecondHourTmp.setText(hour2.getTmp() + DEGREES);
           mSecondHourImg.setImageResource(icons.get(hour2.getCond().getCode()));
           mSecondHourDesc.setText(hour2.getCond().getTxt());
       }catch (Exception e){
           e.printStackTrace();
       }


        //DAILY
        try {
            List<DailyForecastBean> dailyForecastBeanList = heAllWeather5Bean.getDailyForecast();
            DailyForecastBean day1 = dailyForecastBeanList.get(0);
            DailyForecastBean day2 = dailyForecastBeanList.get(1);
            DailyForecastBean day3 = dailyForecastBeanList.get(2);
            String d1 = "", d2 = "", d3 = "";
            try {
                Date date1 = sdf3.parse(day1.getDate());
                d1 = sdf4.format(date1);
                Date date2 = sdf3.parse(day2.getDate());
                d2 = sdf4.format(date2);
                Date date3 = sdf3.parse(day3.getDate());
                d3 = sdf4.format(date3);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            mFirstDayTime.setText(d1);
            mFirstDayTmpMax.setText(day1.getTmp().getMax() + DEGREES);
            mFirstDayTmpMin.setText(day1.getTmp().getMin() + DEGREES);
            mFirstDayImg.setImageResource(icons.get(day1.getCond().getCodeD()));

            mSecondDayTime.setText(d2);
            mSecondDayTmpMax.setText(day2.getTmp().getMax() + DEGREES);
            mSecondDayTmpMin.setText(day2.getTmp().getMin() + DEGREES);
            mSecondDayImg.setImageResource(icons.get(day2.getCond().getCodeD()));

            mThirdDayTime.setText(d3);
            mThirdDayTmpMax.setText(day3.getTmp().getMax() + DEGREES);
            mThirdDayTmpMin.setText(day3.getTmp().getMin() + DEGREES);
            mThirdDayImg.setImageResource(icons.get(day3.getCond().getCodeD()));
        } catch (Exception e){
            e.printStackTrace();
        }


        //Suggestion
        SuggestionBean suggestionBean = heAllWeather5Bean.getSuggestion();
        AirBean airBean = suggestionBean.getAir();
        mAirPointBref.setText("空气指数：" + airBean.getBrf());
        mAirPointTxt.setText(airBean.getTxt());

        ComfBean comfBean = suggestionBean.getComf();
        mComfortablePointBref.setText("舒适度：" + comfBean.getBrf());
        mComfortablePointTxt.setText(comfBean.getTxt());

        CwBean cwBean = suggestionBean.getCw();
        mCwPointBref.setText("洗车指数：" + cwBean.getBrf());
        mCwPointTxt.setText(cwBean.getTxt());

        DrsgBean drsgBean = suggestionBean.getDrsg();
        mDrsgPointBref.setText("穿衣指数：" + drsgBean.getBrf());
        mDrsgPointTxt.setText(drsgBean.getTxt());

        FluBean fluBean = suggestionBean.getFlu();
        mFluPointBref.setText("流感指数：" + fluBean.getBrf());
        mFluPointTxt.setText(fluBean.getTxt());

        TravBean travBean = suggestionBean.getTrav();
        mTravPointBref.setText("旅游指数：" + travBean.getBrf());
        mTravPointTxt.setText(travBean.getTxt());

        SportBean sportBean = suggestionBean.getSport();
        mSportPointBref.setText("运动指数：" + sportBean.getBrf());
        mSportPointTxt.setText(sportBean.getTxt());

        UvBean uvBean = suggestionBean.getUv();
        mUvPointBref.setText("紫外线指数：" + uvBean.getBrf());
        mUvPointTxt.setText(uvBean.getTxt());

    }

    @Override
    public void showLoading() {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(true);
        }
    }

    @Override
    public void showAddressLoadFail() {

    }

    @Override
    public void showAddressLoadSuccess() {

    }

    @Override
    public void showWeatherLoadFail() {
        ToastUtil.showToast(this, "Address load fail", Toast.LENGTH_SHORT);
    }

    @Override
    public void showWeatherLoadSuccess() {

    }

    private int pressTimes;

    @Override
    public void onBackPressed() {
        if (pressTimes == 0) {
            ToastUtil.showToast(this, "再按一次退出", Toast.LENGTH_SHORT);
            pressTimes = 1;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        pressTimes = 0;
                    }
                }
            }).start();
        } else {
            finish();
        }
    }

    public void toggleMoreSuggestion(View view) {
        View more = findViewById(R.id.more_suggestion);
        if (more.getVisibility() == View.VISIBLE) {
            ((TextView) view).setText("更多");
            more.setVisibility(View.GONE);
        } else {
            ((TextView) view).setText("收起");
            more.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SELECT_ADDRESS_REQUEST:
                if (resultCode == RESULT_OK) {
                    //// TODO: 2017-4-1  
                    String resultData = data.getStringExtra(SelectAddressActivity.COUNTRY_NAME_KEY);
                    if (resultData != null) {
                        mCountryName = resultData;
                        PreferenceHelper.getInstance().saveAddress(mCountryName);
                        mPresenter.onRefreshAllWeather(mCountryName);
                        mCollapsingToolbarLayout.setTitle(mCountryName);
                    }
                } else {
                    mPresenter.onAddressLoadError(new AddressLoadError());
                }
                break;
        }
    }
}
