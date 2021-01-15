package com.example.mytt.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.mytt.okhttp.HttpApi;
import com.example.mytt.adapter.NewAdapter;
import com.example.mytt.bean.NewBean;
import com.example.mytt.okhttp.OkHttpWrapper;
import com.example.mytt.R;
import com.google.gson.Gson;

import java.util.List;

public class Fragment1 extends Fragment {
    private View tab1view;
    private NewBean newBean;
    private List<NewBean.ResultBean.DataBean> dataBeans;
    private ListView new_list;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        tab1view=inflater.inflate(R.layout.activity_fragment1, container, false);

        HttpApi.getNew(tab1view.getContext(), HttpApi.Appkey, "top", new OkHttpWrapper.HttpResultCallBack() {
            @Override
            public void httpResultCallBack(int tag, String result, int progress) {
                newBean=new Gson().fromJson(result,NewBean.class);
                if (newBean!=null&&newBean.getReason().equals("成功的返回")){
                    dataBeans=newBean.getResult().getData();
                    handler.sendEmptyMessage(tag);
                }
            }
        });
        return tab1view;
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            switch (msg.what){
                case HttpApi.TAG_GET_NEW :
                    new_list=(ListView) tab1view.findViewById(R.id.new_list);
                    NewAdapter newAdapter=new NewAdapter(tab1view.getContext(),dataBeans);
                    new_list.setAdapter(newAdapter);
                    break;
            }
        }
    };
}
