package com.example.mytt.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mytt.base.BaseApplication;
import com.example.mytt.bean.LocationInfoBean;

import java.util.Objects;

public class SPAccountInfo {

    public  static final String KEY_LOCAL_ADDR="KEY_LOCAL_ADDR";            //当前详细地址信息
    public  static final String KEY_LOCAL_COUNTRY="KEY_LOCAL_COUNTRY";   //当前国家
    public  static final String KEY_LOCAL_PROVINCE="KEY_LOCAL_PROVINCE"; //当前省份
    public  static final String KEY_LOCAL_CITY="KEY_LOCAL_CITY";          //当前城市
    public  static final String KEY_LOCAL_DISTRICT="KEY_LOCAL_DISTRICT"; //当前区县
    public  static final String KEY_LOCAL_STREET="KEY_LOCAL_STREET";     //当前街道信息
    public  static final String KEY_LOCAL_ADCODE="KEY_LOCAL_ADCODE";     //当前adcode
    public  static final String KEY_LOCAL_TOWN="KEY_LOCAL_TOWN";         //当前乡镇信息

    static final String ACCOUNT_INFO="ACCOUNT_INFO";

    public  static SharedPreferences sharedPreferences= BaseApplication.getContext().getSharedPreferences(ACCOUNT_INFO,Context.MODE_PRIVATE);
    public  static SharedPreferences.Editor editor=sharedPreferences.edit();


    //封装put方法，通过 key-value
    public static void put(String key, Object value){
        if(value==null){
            value="";
        }
        if (value instanceof String){
            editor.putString(key,(String)value);
        }else if (value instanceof Integer){
            editor.putInt(key,(Integer)value);
        }else if (value instanceof Boolean){
            editor.putBoolean(key,(Boolean)value);
        }else if (value instanceof Float){
            editor.putFloat(key,(Float)value);
        }else if (value instanceof Long){
            editor.putLong(key,(Long)value);
        }
        editor.commit();
    }
    public static String getString(String key,String defaultValue){
        if (defaultValue==null){
            defaultValue="";
        }
            return sharedPreferences.getString(key,(String)defaultValue);
    }
    public static Integer getInt(String key,Integer defaultValue){
        return sharedPreferences.getInt(key,defaultValue);
    }
    public static Boolean getBoolean(String key,Boolean defaultValue){
        return sharedPreferences.getBoolean(key,defaultValue);
    }
    public static Float getFloat(String key,Float defaultValue){
        return sharedPreferences.getFloat(key,defaultValue);
    }
    public static Long getLong(String key,Long defaultValue){
        return sharedPreferences.getLong(key,defaultValue);
    }



    //保存当前位置信息
    public static void putLocationInfo(LocationInfoBean locationInfoBean){
        if (locationInfoBean==null){
            return;
        }
        put(KEY_LOCAL_ADDR,locationInfoBean.getAddr());
        put(KEY_LOCAL_COUNTRY,locationInfoBean.getCountry());
        put(KEY_LOCAL_PROVINCE,locationInfoBean.getProvince());
        put(KEY_LOCAL_CITY,locationInfoBean.getCity());
        put(KEY_LOCAL_DISTRICT,locationInfoBean.getDistrict());
        put(KEY_LOCAL_STREET,locationInfoBean.getStreet());
        put(KEY_LOCAL_ADCODE,locationInfoBean.getAdcode());
        put(KEY_LOCAL_TOWN,locationInfoBean.getTown());
    }
}
