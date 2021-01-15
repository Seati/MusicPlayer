package com.example.mytt.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mytt.R;
import com.example.mytt.activity.mine.MultilingualActivity;
import com.example.mytt.fragment.Fragment1;
import com.example.mytt.fragment.Fragment10;
import com.example.mytt.fragment.Fragment2;
import com.example.mytt.fragment.Fragment3;
import com.example.mytt.fragment.Fragment4;
import com.example.mytt.fragment.Fragment5;
import com.example.mytt.fragment.Fragment6;
import com.example.mytt.fragment.Fragment7;
import com.example.mytt.fragment.Fragment8;
import com.example.mytt.fragment.Fragment9;

public class MineFragment extends Fragment implements View.OnClickListener{

    private View view_mine;
    RelativeLayout user_info,rl_language;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view_mine=View.inflate(getActivity(), R.layout.activity_mine_fragment,null);
        init();
        return view_mine;
    }

    private void init() {
        view_mine.findViewById(R.id.rl_language).setOnClickListener(this);
        //setStatusBarColor(Color.TRANSPARENT);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rl_language:
                startActivity(new Intent(view_mine.getContext(), MultilingualActivity.class));
                break;
        }
    }
    /**
     * 设置状态栏
     *
     * @param color
     */
    private void setStatusBarColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);
        }
    }
}
