package com.example.mytt.utils;

import android.content.Context;

import com.example.mytt.base.BaseApplication;

public class AppUtils {
    public static  Context context;
    public static String getRes(int res){
        if(context == null){
            return BaseApplication.getContext().getString(res);
        }
        return context.getString(res);
    }
}
