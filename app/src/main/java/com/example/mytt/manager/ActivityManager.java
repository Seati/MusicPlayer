package com.example.mytt.manager;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityManager {

   private static ActivityManager activityManager;
   private List<Activity>mMains=new ArrayList<>();
    private List<Activity>mTemps=new ArrayList<>();

    private ActivityManager() {

    }

    public static ActivityManager getInstance(){
        if (activityManager==null){
            activityManager=new ActivityManager();
        }
        return activityManager;
    }

    //添加activity
    public void addActivity(Activity activity){
        mMains.add(activity);
    }
    //移除activity
    public void removeActivity(Activity activity){
        mMains.remove(activity);
    }

    //添加临时activity
    public void addTempActivity(Activity activity){
        mTemps.add(activity);
    }
    //移除临时activity
    public void removeTempActivity(Activity activity){
        mTemps.remove(activity);
    }

    //结束全部AppCompatActivity
    public void finishAllActivity(){
        for(int i=0;i<mMains.size();i++){
            Activity activity=mMains.get(i);
            if(activity!=null/*&&!(activity instanceof LoginActivity)*/){
                activity.finish();
            }
        }
        mMains.clear();
        mTemps.clear();
    }
    //结束全部AppCompatActivity
    public void finishAllTemp(){
        for(int i=0;i<mTemps.size();i++){
            Activity activity=mTemps.get(i);
            if (activity!=null){
                activity.finish();
            }
        }
        mTemps.clear();
    }
}
