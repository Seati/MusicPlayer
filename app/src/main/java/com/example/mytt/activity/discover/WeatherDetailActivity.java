package com.example.mytt.activity.discover;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mytt.R;
import com.example.mytt.SharedPreferences.SPAccountInfo;
import com.example.mytt.adapter.FutureWeatherAdapter;
import com.example.mytt.adapter.WeatherDetailAdapter;
import com.example.mytt.bean.LocationInfoBean;
import com.example.mytt.bean.WeatherBean;
import com.example.mytt.okhttp.HttpApi;
import com.example.mytt.okhttp.OkHttpWrapper;
import com.example.mytt.service.MusicService;
import com.example.mytt.utils.SetWeatherUtils;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class WeatherDetailActivity extends AppCompatActivity {
    private RecyclerView recycler_view, recycler_view_future;
    private TextView tv_district, tv_date, tv_temperature, tv_info, tv_temperature_day_night;
    private ImageView wid_img;
    private WeatherBean mWeatherBean;
    private WeatherBean.ResultObj mResultObj;
    private WeatherBean.ResultObj.RealtimeObj mRealtimeObj;
    private List<WeatherBean.ResultObj.FutureObj> mFutureObj;
    private WeatherDetailAdapter weatherDetailAdapter;
    private FutureWeatherAdapter mFutureWeatherAdapter;
    private LocationInfoBean locationInfoBean;
    private String city;
    private SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("MM月dd日 EE");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_detail);
        city = getIntent().getStringExtra("city");
        HttpApi.getWeather(this, HttpApi.Appkey_Weather, city, mHttpResultCallBack);
        init();
    }

    @SuppressLint("WrongConstant")
    private void init() {
        tv_district = (TextView) findViewById(R.id.tv_district);
        tv_date = (TextView) findViewById(R.id.tv_date);
        tv_temperature = (TextView) findViewById(R.id.tv_temperature);
        tv_info = (TextView) findViewById(R.id.tv_info);
        tv_temperature_day_night = (TextView) findViewById(R.id.tv_temperature_day_night);
        wid_img = (ImageView) findViewById(R.id.wid_img);

        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        recycler_view.setOverScrollMode(View.OVER_SCROLL_NEVER);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
        gridLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recycler_view.setLayoutManager(gridLayoutManager);


        recycler_view_future = (RecyclerView) findViewById(R.id.recycler_view_future);
        recycler_view_future.setOverScrollMode(View.OVER_SCROLL_NEVER);
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getApplicationContext(), 5);
        gridLayoutManager1.setOrientation(OrientationHelper.VERTICAL);
        recycler_view_future.setLayoutManager(gridLayoutManager1);


    }

    private OkHttpWrapper.HttpResultCallBack mHttpResultCallBack = new OkHttpWrapper.HttpResultCallBack() {
        @Override
        public void httpResultCallBack(int tag, String result, int progress) {
            switch (tag) {
                case HttpApi.TAG_GET_WEATHER:
                    if (!result.equals("")) {
                        mWeatherBean = new Gson().fromJson(result, WeatherBean.class);
                        if (mWeatherBean.getError_code().equals("0")) {
                            mHandler.sendEmptyMessage(tag);
                        }
                    }
                    break;
            }
        }
    };
    private Handler mHandler = new Handler() {
        @SuppressLint("WrongConstant")
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case HttpApi.TAG_GET_WEATHER:
                    mResultObj = mWeatherBean.getResult();
                    mRealtimeObj = mResultObj.getRealtime();
                    mFutureObj = mResultObj.getFuture();

                    tv_district.setText(SPAccountInfo.getString(SPAccountInfo.KEY_LOCAL_DISTRICT, ""));
                    tv_date.setText(mSimpleDateFormat.format(new Date()));
                    tv_info.setText(mRealtimeObj.getInfo());
                    tv_temperature.setText(mRealtimeObj.getTemperature());
                    tv_temperature_day_night.setText(mFutureObj.get(0).getTemperature());
                    SetWeatherUtils.setWeatherIcon(wid_img, mRealtimeObj.getWid());

                    weatherDetailAdapter = new WeatherDetailAdapter(mRealtimeObj);
                    recycler_view.setAdapter(weatherDetailAdapter);

                    mFutureWeatherAdapter = new FutureWeatherAdapter(mFutureObj);
                    recycler_view_future.setAdapter(mFutureWeatherAdapter);
                    break;
            }
        }
    };
}
