package com.example.mytt.base;


import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.mytt.utils.LanguageUtils;
import com.example.mytt.utils.MyLocationListener;

import java.lang.ref.WeakReference;


public class BaseApplication extends Application {
    private static Context sContext;
    public static LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();
    @Override
    public void onCreate() {
        super.onCreate();
        sContext=getApplicationContext();
        LanguageUtils.updateLocal(this,LanguageUtils.getLocal(this));
        init();



    }

    private void init() {
        //初始化百度地图
         mLocationClient = new LocationClient(sContext);
         mLocationClient.registerLocationListener(myListener);

        LocationClientOption option = new LocationClientOption();
        option.setIsNeedAddress(true);
        option.setNeedNewVersionRgc(true);
        mLocationClient.setLocOption(option);


    }

    public static void startLocate(){
        mLocationClient.start();
    }
    public static Context getContext(){
        return sContext;
    }

    public interface MessageCallBack{
        public void messageCallBack(Message message);
    }

    public static class  CommonHandler extends Handler {
        private WeakReference<Activity>weakReference;
        private MessageCallBack messageCallBack;

        public CommonHandler(Activity activity,MessageCallBack mMessageCallBack){
            weakReference=new WeakReference<Activity>(activity);
            messageCallBack=mMessageCallBack;
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            BaseActivity activity=(BaseActivity)weakReference.get();
            if (activity!=null){
                try {
                    messageCallBack.messageCallBack(msg);
                }catch (Exception e){

                }
            }
        }
    };

}
