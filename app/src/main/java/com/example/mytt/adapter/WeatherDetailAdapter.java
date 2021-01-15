package com.example.mytt.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytt.R;
import com.example.mytt.bean.WeatherBean;
import com.example.mytt.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;

public class WeatherDetailAdapter extends RecyclerView.Adapter<WeatherDetailAdapter.ViewHolder> {
    private WeatherBean.ResultObj.RealtimeObj mRealtimeObj;
    private String msg[]={};
    public WeatherDetailAdapter(WeatherBean.ResultObj.RealtimeObj mRealtimeObj){
        this.mRealtimeObj=mRealtimeObj;
        String []msg={mRealtimeObj.getTemperature()+"℃",mRealtimeObj.getInfo(),mRealtimeObj.getAqi(),
                mRealtimeObj.getPower(), mRealtimeObj.getDirect(),mRealtimeObj.getHumidity()+"%"};
        this.msg=msg;
    }
    private int []imgRes={R.mipmap.ic_temperature_d,R.mipmap.ic_weather_d,R.mipmap.ic_air_quality,R.mipmap.ic_wind_power,R.mipmap.ic_wind_direction,R.mipmap.ic_humidity};
    private String []tv2={
            AppUtils.getRes(R.string.future_temperature),
            AppUtils.getRes(R.string.future_weather),
            AppUtils.getRes(R.string.future_air_quality),
            AppUtils.getRes(R.string.future_wind_power),
            AppUtils.getRes(R.string.future_wind_direction),
            AppUtils.getRes(R.string.future_humidity)};
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView detail_image_view;
        TextView detail_tv1,detail_tv2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
             detail_image_view=(ImageView)itemView.findViewById(R.id.detail_image_view);
             detail_tv1=(TextView)itemView.findViewById(R.id.detail_tv1);
             detail_tv2=(TextView)itemView.findViewById(R.id.detail_tv2);
        }
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_item,null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.detail_image_view.setImageResource(imgRes[position]);
        holder.detail_tv1.setText(msg[position]);
        holder.detail_tv2.setText(tv2[position]);
    }

    @Override
    public int getItemCount() {
        return msg.length;
    }

}
