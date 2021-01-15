package com.example.mytt.okhttp;

import android.content.Context;

import okhttp3.CacheControl;
import okhttp3.FormBody;

public class HttpApi {
    public  static  String Appkey="65081bb3f32193e43ee176fb2ade3211";
    public  static  String Appkey_Weather="51d7b98034a32eda4af66774d4eb9fec";
    public  static final int TAG_NET_ERR = -1;
    public  static final int TAG_REQ_ERR = -2;

    public  static final int TAG_GET_NEW = 20000;
    public  static final int TAG_GET_WEATHER = 20001;
    public  static final int TAG_SEARCH_MUSIC = 20002;
    public  static final int TAG_CHECK_MUSIC_PERMISSION = 20003;
    //public  static final int TAG_CHECK_MUSIC_PERMISSION = 20004;

    public static void getNew(Context context, String key, String type, OkHttpWrapper.HttpResultCallBack httpResultCallBack) {
        String api = "http://v.juhe.cn/toutiao/index";
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("key",key);
        builder.add("type", type);
        OkHttpWrapper.post(context,api, builder.build(), CacheControl.FORCE_NETWORK, "获取新闻", httpResultCallBack,
                TAG_GET_NEW);
        }
    public static void getWeather(Context context, String key, String city, OkHttpWrapper.HttpResultCallBack httpResultCallBack) {
        String api = "http://apis.juhe.cn/simpleWeather/query";
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("key",key);
        builder.add("city", city);
        OkHttpWrapper.post(context,api, builder.build(), CacheControl.FORCE_NETWORK, "获取天气", httpResultCallBack,
                TAG_GET_WEATHER);
    }
    public static void searchMusic(Context context, String keywords,OkHttpWrapper.HttpResultCallBack httpResultCallBack) {
        String api = "https://v1.alapi.cn/api/music/search?keyword="+keywords;
        OkHttpWrapper.get(context,api, CacheControl.FORCE_NETWORK, "", httpResultCallBack,
                TAG_SEARCH_MUSIC);
    }

}
