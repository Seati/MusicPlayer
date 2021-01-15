package com.example.mytt.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.mytt.R;
import com.example.mytt.base.BaseApplication;
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
import com.example.mytt.utils.AppUtils;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FirstFragment extends Fragment implements View.OnClickListener {
    private View view_frist;
    private Unbinder uBind;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.vpager)
    ViewPager vpager;


    List<Fragment> fragments = new ArrayList<>();
    private String title[] = {AppUtils.getRes(R.string.nav_toutiao), AppUtils.getRes(R.string.nav_shehui),
            AppUtils.getRes(R.string.nav_guonei), AppUtils.getRes(R.string.nav_guoji),
            AppUtils.getRes(R.string.nav_yule), AppUtils.getRes(R.string.nav_tiyu),
            AppUtils.getRes(R.string.nav_junshi), AppUtils.getRes(R.string.nav_keji),
            AppUtils.getRes(R.string.nav_caijing), AppUtils.getRes(R.string.nav_shishang),};


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        //view_frist = LayoutInflater.from(getActivity()).inflate(R.layout.activity_frist_fragment, null);
        view_frist = inflater.inflate(R.layout.activity_frist_fragment, null);
        uBind=ButterKnife.bind(this,view_frist);
        init();
        return view_frist;
    }


    private void init() {
        fragments.add(new Fragment1());
        fragments.add(new Fragment2());
        fragments.add(new Fragment3());
        fragments.add(new Fragment4());
        fragments.add(new Fragment5());
        fragments.add(new Fragment6());
        fragments.add(new Fragment7());
        fragments.add(new Fragment8());
        fragments.add(new Fragment9());
        fragments.add(new Fragment10());

        //tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        vpager.setOffscreenPageLimit(1);
        vpager.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return title[position];
            }
        });
        tablayout.setupWithViewPager(vpager);
    }

    @Override
    public void onResume() {
        super.onResume();
        initPermission();
    }
    @Override
    public void onClick(View view) {

    }

    private void initPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            BaseApplication.startLocate();
        }
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 2);
        }
      /*  if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 3);
        }*/
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
       uBind.unbind();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getActivity(), "请授予权限", Toast.LENGTH_SHORT).show();
                } else {
                    BaseApplication.startLocate();
                }
                break;
            case 2:
                if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getActivity(), "请授予权限", Toast.LENGTH_SHORT).show();
                }
                break;
          /*  case 3:
                if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getActivity(), "请授予权限", Toast.LENGTH_SHORT).show();
                }
                break;*/
            default:
                break;
        }
    }
}
