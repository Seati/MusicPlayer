package com.example.mytt.adapter;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mytt.R;
import com.example.mytt.bean.NewBean;
import com.example.mytt.utils.WebActivity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class NewAdapter extends BaseAdapter {
    private NewBean newBean;

    private List<NewBean.ResultBean.DataBean>dataBeans;
    private Context context;
    private ImageView img;
    private TextView tv_title,tv_time;

    public NewAdapter(Context context,List<NewBean.ResultBean.DataBean>dataBeans){
        this.context=context;
        this.dataBeans=dataBeans;
    }

    @Override
    public int getCount() {
        return dataBeans.size();
    }

    @Override
    public Object getItem(int i) {
        return dataBeans.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=LayoutInflater.from(context).inflate(R.layout.new_list_item,null);
        img=(ImageView) view.findViewById(R.id.img);
        tv_title=(TextView)view.findViewById(R.id.tv_title);
        tv_time=(TextView)view.findViewById(R.id.tv_time);
        NewBean.ResultBean.DataBean data=(NewBean.ResultBean.DataBean)getItem(i);
        final String url=data.getUrl();

        if (data.getTitle().length()>25){
            tv_title.setText(data.getTitle().substring(0,25)+"......");
        }else {
            tv_title.setText(data.getTitle());
        }
        tv_time.setText(data.getDate());
        Glide.with(view.getContext()).load(data.getThumbnail_pic_s()).into(img);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.getContext().startActivity(new Intent(view.getContext(), WebActivity.class)
                        .putExtra("key_url", url));
            }
        });

        return view;
    }
    //加载本地图片
    public static Bitmap getLoacalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);  ///把流转化为Bitmap图片
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    //加载网络图片
    public static Bitmap getHttpBitmap(String url) {
        URL myFileUrl = null;
        Bitmap bitmap = null;
        try {
            myFileUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
            conn.setConnectTimeout(6000);
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
