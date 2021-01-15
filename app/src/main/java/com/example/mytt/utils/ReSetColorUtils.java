package com.example.mytt.utils;

import android.annotation.SuppressLint;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mytt.R;

//重置字体颜色
public class ReSetColorUtils {

    @SuppressLint("ResourceAsColor")
    public static ViewGroup resetView(ViewGroup viewGroup){
        for (int i=0;i<viewGroup.getChildCount();i++){
            TextView textView=(TextView) viewGroup.getChildAt(i);
            textView.setTextColor(R.color.grey);
        }
        return viewGroup;
    };

}
