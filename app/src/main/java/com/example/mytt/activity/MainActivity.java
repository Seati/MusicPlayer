package com.example.mytt.activity;



import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.mytt.R;
import com.example.mytt.base.BaseActivity;
import com.example.mytt.utils.LanguageUtils;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity implements View.OnClickListener{

    private Fragment firstFragment,hotFragment,discoverFragment,mineFragment;
    private LinearLayout ll_first,ll_hot,ll_discover,ll_mine;
    private TextView  tv_first,tv_hot,tv_discover,tv_mine;
    private ImageView img_first,img_hot,img_discover,img_mine;
    private int REQUEST_CODE_ACCESS_COARSE_LOCATION=1;
    private int REQUEST_READ_EXTERNAL_STORAGE=2;
    private int REQUEST_WRITE_EXTERNAL_STORAGE=3;

   /* List<Fragment> fragments=new ArrayList<>();
    String title[]={"首页","热搜","发现","我的"};*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        LanguageUtils.updateLocal(this, LanguageUtils.getLocal(this));
        setSelect(0);
    }

    private void init(){
        /*fragments.add(new FirstFragment());
        fragments.add(new HotFragment());
        fragments.add(new DiscoverFragment());
        fragments.add(new MineFragment());
        */
        tv_first=(TextView)findViewById(R.id.tv_first);
        tv_hot=(TextView)findViewById(R.id.tv_hot);
        tv_discover=(TextView)findViewById(R.id.tv_discover);
        tv_mine=(TextView)findViewById(R.id.tv_mine);
        img_first=(ImageView)findViewById(R.id.img_first);
        img_hot=(ImageView)findViewById(R.id.img_hot);
        img_discover=(ImageView)findViewById(R.id.img_discover);
        img_mine=(ImageView)findViewById(R.id.img_mine);
        findViewById(R.id.ll_first).setOnClickListener(this);
        findViewById(R.id.ll_hot).setOnClickListener(this);
        findViewById(R.id.ll_discover).setOnClickListener(this);
        findViewById(R.id.ll_mine).setOnClickListener(this);
    }

    private void setSelect(int i){
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        hideFragment(ft);

        switch (i){
            case 0:
                if (firstFragment==null){
                    firstFragment=new FirstFragment();
                    ft.add(R.id.main_content,firstFragment);
                }else {
                    ft.show(firstFragment);
                }
                tv_first.setTextColor(getResources().getColor(R.color.main_red));
                img_first.setImageResource(R.mipmap.first_selected);
                break;
            case 1:
                if (hotFragment==null){
                    hotFragment=new HotFragment();
                    ft.add(R.id.main_content,hotFragment);
                }else {
                    ft.show(hotFragment);
                }
                tv_hot.setTextColor(getResources().getColor(R.color.main_red));
                img_hot.setImageResource(R.mipmap.hot_selected);
                break;
            case 2:
                if (discoverFragment==null){
                    discoverFragment=new DiscoverFragment();
                    ft.add(R.id.main_content,discoverFragment);
                }else {
                    ft.show(discoverFragment);
                }
                tv_discover.setTextColor(getResources().getColor(R.color.main_red));
                img_discover.setImageResource(R.mipmap.discover_selected);
                break;
            case 3:
                if (mineFragment==null){
                    mineFragment=new MineFragment();
                    ft.add(R.id.main_content,mineFragment);
                }else {
                    ft.show(mineFragment);
                }
                tv_mine.setTextColor(getResources().getColor(R.color.main_red));
                img_mine.setImageResource(R.mipmap.mine_selected);
                break;

        }
        ft.commit();
    }


    private void hideFragment(FragmentTransaction ft){
        if (firstFragment!=null){
            ft.hide(firstFragment);
        }
        if (hotFragment!=null){
            ft.hide(hotFragment);
        }
        if (discoverFragment!=null){
            ft.hide(discoverFragment);
        }
        if (mineFragment!=null){
            ft.hide(mineFragment);
        }
    }
    private void reset(){
        tv_first.setTextColor(getResources().getColor(R.color.grey));
        tv_hot.setTextColor(getResources().getColor(R.color.grey));
        tv_discover.setTextColor(getResources().getColor(R.color.grey));
        tv_mine.setTextColor(getResources().getColor(R.color.grey));
        img_first.setImageResource(R.mipmap.first);
        img_hot.setImageResource(R.mipmap.hot);
        img_discover.setImageResource(R.mipmap.discover);
        img_mine.setImageResource(R.mipmap.mine);
    }

    @Override
    public void onClick(View view) {
        reset();
        switch (view.getId()) {
            case R.id.ll_first:
                setSelect(0);
                break;
            case R.id.ll_hot:
                setSelect(1);
                break;
            case R.id.ll_discover:
                setSelect(2);
                break;
            case R.id.ll_mine:
                setSelect(3);
                break;
            default:
                break;
        }
    }
}
