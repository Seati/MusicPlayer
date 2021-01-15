package com.example.mytt.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.util.DisplayMetrics;

import java.util.Locale;

public class LanguageUtils {
   private static final String SP_LOCAL="SP_LOCAL";
    private static final String SP_LOCAL_KEY="SP_LOCAL_KEY";
    private static final Locale[]localList={Locale.CHINA,Locale.ENGLISH};
    public static int getLocal(Context context){
        SharedPreferences spLocal=context.getSharedPreferences(SP_LOCAL,Context.MODE_PRIVATE);
        int local=spLocal.getInt(SP_LOCAL_KEY,0);
        return local;
    }
    public static void setLocal(Context context,int userLocal){
        SharedPreferences spLocal=context.getSharedPreferences(SP_LOCAL,Context.MODE_PRIVATE);
        SharedPreferences.Editor edit=spLocal.edit();
        edit.putInt(SP_LOCAL_KEY,userLocal);
        edit.apply();
    }
    public static Boolean updateLocal(Context context,int userLocal){
        if (needUpdateLocal(context,localList[userLocal])){
            Configuration configuration=context.getResources().getConfiguration();
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN_MR1){
                configuration.setLocale(localList[userLocal]);
            }else {
                configuration.locale=localList[userLocal];
            }
            DisplayMetrics displayMetrics=context.getResources().getDisplayMetrics();
            context.getResources().updateConfiguration(configuration,displayMetrics);
            setLocal(context,userLocal);
            return true;
        }
        return false;
    }
    public static Boolean needUpdateLocal(Context context,Locale newUserLocal){
        return newUserLocal!=null && !newUserLocal.equals(getCurrentLocal(context));
    }
    private static   Locale getCurrentLocal(Context context){
        Locale locale;
        if (Build.VERSION.SDK_INT>Build.VERSION_CODES.N){
            locale=context.getResources().getConfiguration().getLocales().get(0);
        }else{
            locale=context.getResources().getConfiguration().locale;
        }
        return locale;
    }
}
