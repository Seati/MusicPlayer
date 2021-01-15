package com.example.mytt.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mytt.R;
import com.example.mytt.SharedPreferences.SPAccountInfo;
import com.example.mytt.activity.discover.WeatherDetailActivity;
import com.example.mytt.activity.discover.music.MyMusicActivity;
import com.example.mytt.bean.LocationInfoBean;
import com.example.mytt.bean.WeatherBean;
import com.example.mytt.okhttp.HttpApi;
import com.example.mytt.okhttp.OkHttpWrapper;
import com.example.mytt.utils.SetWeatherUtils;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class DiscoverFragment extends Fragment {

    @BindView(R.id.rl_weather_detail)
    RelativeLayout rlWeatherDetail;
    @BindView(R.id.iv_music_activity)
    ImageView ivMusicActivity;
    @BindView(R.id.wid_img)
    ImageView widImg;
    @BindView(R.id.tv_temperature)
    TextView tvTemperature;
    @BindView(R.id.tv_info)
    TextView tvInfo;
    @BindView(R.id.tv_city)
    TextView tvCity;

    private View view_discover;
    private WeatherBean mWeatherBean;
    private WeatherBean.ResultObj mResultObj;
    private WeatherBean.ResultObj.RealtimeObj mRealtimeObj;
    private LocationInfoBean locationInfoBean = new LocationInfoBean();
    private String city;
    private Unbinder unbinder;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view_discover = View.inflate(getActivity(), R.layout.activity_discover_fragment, null);
        unbinder=ButterKnife.bind(this,view_discover);
        init();
        return view_discover;
    }

    private void init() {
        city = SPAccountInfo.getString(SPAccountInfo.KEY_LOCAL_CITY, "").replace("市", "");
        HttpApi.getWeather(getContext(), HttpApi.Appkey_Weather, city, new OkHttpWrapper.HttpResultCallBack() {
            @Override
            public void httpResultCallBack(int tag, String result, int progress) {
                if (!result.equals("")) {
                    mWeatherBean = new Gson().fromJson(result, WeatherBean.class);
                    if (mWeatherBean.getError_code().equals("0")) {
                        mHandler.sendEmptyMessage(tag);
                    }
                }
            }
        });

    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case HttpApi.TAG_GET_WEATHER:
                    mResultObj = mWeatherBean.getResult();
                    mRealtimeObj = mResultObj.getRealtime();
                    SetWeatherUtils.setWeatherIcon(widImg, mRealtimeObj.getWid());
                    tvTemperature.setText(mRealtimeObj.getTemperature() + "℃");
                    tvInfo.setText(mRealtimeObj.getInfo());
                    tvCity.setText(city + "市");
                    break;
            }
        }
    };


    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @OnClick({R.id.rl_weather_detail, R.id.iv_music_activity})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_weather_detail:
                startActivity(new Intent(getContext(), WeatherDetailActivity.class).putExtra("city", city));
                break;
            case R.id.iv_music_activity:
                Animation animation= AnimationUtils.loadAnimation(getContext(),R.anim.move_anim);
                animation.setAnimationListener(animationListener);
                ivMusicActivity.startAnimation(animation);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    private Animation.AnimationListener animationListener=new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            startActivity(new Intent(getContext(), MyMusicActivity.class));
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };
}
