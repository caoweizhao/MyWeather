package com.example.administrator.weather.mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.administrator.weather.R;
import com.example.administrator.weather.fragment.CityFragment;
import com.example.administrator.weather.fragment.CountryFragment;
import com.example.administrator.weather.fragment.ProvinceFragment;
import com.example.administrator.weather.listener.AddressItemClickListener;
import com.example.administrator.weather.mvp.model.locationbean.CityBean;
import com.example.administrator.weather.mvp.model.locationbean.CountryBean;
import com.example.administrator.weather.mvp.model.locationbean.IAddress;
import com.example.administrator.weather.mvp.model.locationbean.ProvinceBean;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Administrator on 2017-3-28.
 */

public class SelectAddressActivity extends AppCompatActivity implements AddressItemClickListener {

    public static final String TYPE_PROVINCE = "province";
    public static final String TYPE_CITY = "city";
    public static final String TYPE_COUNTRY = "country";

    public static final String COUNTRY_NAME_KEY = "city_name";



    private CollapsingToolbarLayout mCollapsingToolbarLayout;

    private ProvinceBean mProvinceBean;
    private CityBean mCityBean;

    @Override
    public void onItemClick(IAddress iAddress) {
        if (iAddress instanceof ProvinceBean) {
            mProvinceBean = (ProvinceBean) iAddress;
            mCollapsingToolbarLayout.setTitle(mProvinceBean.getName());
            setFragment(TYPE_CITY);
        } else if (iAddress instanceof CityBean) {
            mCityBean = (CityBean) iAddress;
            mCollapsingToolbarLayout.setTitle(mCityBean.getName());
            setFragment(TYPE_COUNTRY);
        } else if (iAddress instanceof CountryBean) {
            CountryBean countryBean = (CountryBean) iAddress;
            Intent intent = new Intent(SelectAddressActivity.this, MainActivity.class);
            intent.putExtra(COUNTRY_NAME_KEY, countryBean.getName());
            setResult(RESULT_OK,intent);
            finish();
        }
    }

    @Retention(RetentionPolicy.CLASS)
    @StringDef({TYPE_PROVINCE, TYPE_CITY, TYPE_COUNTRY})
    @interface TYPE_FRAGMENT {
    }

    private Fragment provinceFragment;
    private Fragment cityFragment;
    private Fragment countryFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d("SelectAddressActivity", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_address);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout_select);
        initToolBar();
        setFragment(TYPE_PROVINCE);
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("SelectAddressActivity","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("SelectActivity", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("SelectActivity","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("SelectActivity","onStop");
    }


    private Toolbar mToolbar;

    private void initToolBar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mCollapsingToolbarLayout.setTitle("中国");
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setFragment(@TYPE_FRAGMENT String tag) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        if (tag.equals(TYPE_PROVINCE)) {
            provinceFragment = ProvinceFragment.newInstance();
            transaction.replace(R.id.m_container, provinceFragment, TYPE_PROVINCE);
            transaction.commit();

        } else if (tag.equals(TYPE_CITY)) {
            cityFragment = CityFragment.newInstance(mProvinceBean);
            transaction.replace(R.id.m_container, cityFragment, TYPE_CITY);
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (tag.equals(TYPE_COUNTRY)) {
            countryFragment = CountryFragment.newInstance(mCityBean);
            transaction.replace(R.id.m_container, countryFragment, TYPE_COUNTRY);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
