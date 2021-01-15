package com.example.mytt.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.DisplayMetrics;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 校验工具类
 * Created by Administrator on 2017/11/7.
 */

public class VerifyUtils {
    /**
     * 空校验
     */
    public static boolean isNull(String str) {
        if (str == null) {
            return true;
        }
        return str.isEmpty();
    }

    /**
     * 判断该文件是否是一个图片。
     */
    public static boolean isImage(String fileName) {
        return fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png");
    }

    /**
     * 邮箱校验
     */
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))" +
                "([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 手机校验
     */
    public static boolean isCellPhone(String mobilePhone) {
        boolean isMobilePhone = false;
        try {
            String expression = "[1][123456789]\\d{9}";
            CharSequence inputStr = mobilePhone;
            Pattern pattern = Pattern.compile(expression);
            Matcher matcher = pattern.matcher(inputStr);
            if (matcher.matches()) {
                isMobilePhone = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isMobilePhone;
    }
    /***
     * 验证电话号码
     * * @return 如果是符合格式的字符串,
     * 返回 <b>true </b>,否则为 <b>false </b>
     * */
    public static boolean IsTelephone(String str) {
        boolean isMobilePhone = false;
        try {
            String expression = "^(\\d{2,4}-?)?\\d{7,8}$";
            CharSequence inputStr = str;
            Pattern pattern = Pattern.compile(expression);
            Matcher matcher = pattern.matcher(inputStr);
            if (matcher.matches()) {
                isMobilePhone = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isMobilePhone;
    }

    /**
     * 网络校验
     */
    public static boolean isHasNet(Context context) {
        boolean isHasNet = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context
                .CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null) {
            isHasNet = networkInfo.isAvailable();
        }
        return isHasNet;
    }

    /**
     * 判断SD卡是否可用
     */
    public static boolean isSDcardOK() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }


    /**
    * 时间播报
    *
    * */
public static  void as(String time, String content) throws ParseException {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyymmddHHmm");
    Date date = new Date(System.currentTimeMillis());
    Date date1 = null;
    date1 = simpleDateFormat.parse(time);
    long mSurplusTime = date.getTime() - date1.getTime();
    long days = mSurplusTime / (1000 * 60 * 60*24);
    long hour=(mSurplusTime/(60*60*1000)-days*24);
    long min=((mSurplusTime/(60*1000))-days*24*60-hour*60);
    if (hour==0&&min<=3){
       // TTSUtil.getInstance(BaseApplication.getContext()).play(content);
    }
}
    /**
     * 获取SD卡跟路径。SD卡不可用时，返回null
     */
    public static String getSDcardRoot() {
        if (isSDcardOK()) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return null;
    }
/**
* dp转px
* */
    public static int dp2px(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float density = displayMetrics.density;
        // px=dp*density;
        return (int) (dp * density + 0.5f);
    }
    /**
     * px转dp
     * */
    public static int px2dp(Context context, int px) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float density = displayMetrics.density;
        // px=dp*density;---- dp=px/density
        return (int) (px / density + 0.5f);
    }
    /**
     * 将秒数转为天，时，分，秒
     */
    public static String change(long second) {
        long days = 0;
        long h = 0;
        long d = 0;
        long s = 0;
        long temp2 = second % (24*3600);
        if (second>temp2){
            days=second/(24*3600);
            if (temp2!=0){
                long temp = temp2 % 3600;
                if (temp2 > 3600) {
                    h = temp2 / 3600;
                    if (temp != 0) {
                        if (temp > 60) {
                            d = temp / 60;
                            if (temp % 60 != 0) {
                                s = temp % 60;
                            }
                        } else {
                            s = temp;
                        }
                    }
                }
            }
        } else {
            d = second / 60;
            if (second % 60 != 0) {
                s = second % 60;
            }
        }
        return "剩余" + days + "天" + h + "小时" + d;
    }

    public static String getTime(long time){
        long days = time /(60*24*60*1000);
        long hour=(time-(days*60*24*60*1000))/(60*60*1000);
        long minute=(time-(days*60*24*60*1000)-(hour*60*60*1000))/(60*1000);
        if (days>0){
            if (hour>0){
                if (minute>0){
                    return days+"天"+hour+"小时"+minute+"分钟";
                }else{
                    return days+"天"+hour+"小时";
                }
            }else{
                if (minute>0){
                    return days+"天"+minute+"分钟";
                }else{
                    return days+"天";
                }
            }
        }else{
            if (hour>0){
                if (minute>0){
                    return hour+"小时"+minute+"分钟";
                }else{
                    return hour+"小时";
                }
            }else{
                if (minute>0){
                    return minute+"分钟";
                }else{
                    return "0分钟";
                }
            }
        }
    }
/**
* EditText 内容显示和隐藏
* */
    public static  void initChea(final EditText editText, final CheckBox box){
        editText.setTransformationMethod(PasswordTransformationMethod.getInstance());

        box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

    }
public static String setTimeDay(String startTime, String endTime){
     int y=0;
    int tianshu=0;
    String[] split = startTime.split(" ")[0].split("-");
    String[] strings = endTime.split(" ")[0].split("-");
    int n = Integer.parseInt(strings[0]) - Integer.parseInt(split[0])*12;
    if (Integer.parseInt(strings[1])< Integer.parseInt(split[1])){
        y= Integer.parseInt(strings[1]);
    }else{
        y = Integer.parseInt(strings[1]) - Integer.parseInt(split[1]);
    }
    if(n>=2){
        tianshu = (n-1)*12*365+ y * 30 + Integer.parseInt(strings[2]) - Integer.parseInt(split[2]);
    }else{
        tianshu = y * 30 + Integer.parseInt(strings[2]) - Integer.parseInt(split[2]);
    }
    return (tianshu+1)+"";
}


}
