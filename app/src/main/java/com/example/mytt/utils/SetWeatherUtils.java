package com.example.mytt.utils;

import android.widget.ImageView;

import com.example.mytt.R;

public class SetWeatherUtils {
    public static void setWeatherIcon(ImageView img, String wid){
        switch (wid){
            case "00":
                img.setImageResource(R.mipmap.wid_00);
                break;
            case "01":
                img.setImageResource(R.mipmap.wid_01);
                break;
            case "02":
                img.setImageResource(R.mipmap.wid_02);
                break;
            case "03":
                img.setImageResource(R.mipmap.wid_03);
                break;
            case "04":
                img.setImageResource(R.mipmap.wid_04);
                break;
            case "05":
                img.setImageResource(R.mipmap.wid_05);
                break;
            case "06":
                img.setImageResource(R.mipmap.wid_06);
                break;
            case "07": case "21":
                img.setImageResource(R.mipmap.wid_07_21);
                break;
            case "08":case "22":
                img.setImageResource(R.mipmap.wid_08_22);
                break;
            case "09":case "23":
                img.setImageResource(R.mipmap.wid_09_23);
                break;
            case "10":case "24":
                img.setImageResource(R.mipmap.wid_10_24);
                break;
            case "11":case "25":
                img.setImageResource(R.mipmap.wid_11_25);
                break;
            case "12":
                img.setImageResource(R.mipmap.wid_12);
                break;
            case "13":
                img.setImageResource(R.mipmap.wid_13);
                break;
            case "14":case "26":
                img.setImageResource(R.mipmap.wid_14_26);
                break;
            case "15":case "27":
                img.setImageResource(R.mipmap.wid_15_27);
                break;
            case "16":case "28":
                img.setImageResource(R.mipmap.wid_16_28);
                break;
            case "17":
                img.setImageResource(R.mipmap.wid_17);
                break;
            case "18":
                img.setImageResource(R.mipmap.wid_18);
                break;
            case "19":
                img.setImageResource(R.mipmap.wid_19);
                break;
            case "20":case "31":
                img.setImageResource(R.mipmap.wid_20_31);
                break;
            case "29":
                img.setImageResource(R.mipmap.wid_29);
                break;
            case "30":
                img.setImageResource(R.mipmap.wid_30);
                break;
            case "53":
                img.setImageResource(R.mipmap.wid_53);
                break;
            default:
                break;
        }
    }
    public static String setWeather(String wid){
        String weather = "";
        switch (wid){
            case "00":
                weather="晴";
                break;
            case "01":
                weather="多云";
                break;
            case "02":
                weather="阴";
                break;
            case "03":
                weather="阵雨";
                break;
            case "04":
                weather="雷阵雨";
                break;
            case "05":
                weather="雷阵雨伴有冰雹";
                break;
            case "06":
                weather="雨夹雪";
                break;
            case "07": case "21":
                weather="小雨";
                break;
            case "08":case "22":
                weather="中雨";
                break;
            case "09":case "23":
                weather="大雨";
                break;
            case "10":case "24":
                weather="暴雨";
                break;
            case "11":case "25":
                weather="大暴雨";
                break;
            case "12":
                weather="特大暴雨";
                break;
            case "13":
                weather="阵雪";
                break;
            case "14":case "26":
                weather="小雪";
                break;
            case "15":case "27":
                weather="中雪";
                break;
            case "16":case "28":
                weather="大雪";
                break;
            case "17":
                weather="暴雪";
                break;
            case "18":
                weather="雾";
                break;
            case "19":
                weather="冻雨";
                break;
            case "20":case "31":
                weather="沙尘暴";
                break;
            case "29":
                weather="浮尘";
                break;
            case "30":
                weather="扬沙";
                break;
            case "53":
                weather="霾";
                break;
            default:
                break;
        }
        return weather;
    }
}
