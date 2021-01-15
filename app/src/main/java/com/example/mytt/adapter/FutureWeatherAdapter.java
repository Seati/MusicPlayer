package com.example.mytt.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mytt.R;
import com.example.mytt.bean.WeatherBean;
import com.example.mytt.utils.AppUtils;
import com.example.mytt.utils.SetWeatherUtils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FutureWeatherAdapter extends RecyclerView.Adapter<FutureWeatherAdapter.ViewHolder> {
    private List<WeatherBean.ResultObj.FutureObj> futureObj;
    private WeatherBean.ResultObj.FutureObj.WidObj mWidObjs;

    public FutureWeatherAdapter(List<WeatherBean.ResultObj.FutureObj> futureObj){
        this.futureObj=futureObj;

    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView future_weather_icon_day,future_weather_icon_night;
        TextView future_week,future_date,future_weather_day,future_temperature,future_weather_night,future_direct;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            future_weather_icon_day=(ImageView)itemView.findViewById(R.id.future_weather_icon_day);
            future_weather_icon_night=(ImageView)itemView.findViewById(R.id.future_weather_icon_night);
            future_week=(TextView)itemView.findViewById(R.id.future_week);
            future_date=(TextView)itemView.findViewById(R.id.future_date);
            future_weather_day=(TextView)itemView.findViewById(R.id.future_weather_day);
            future_temperature=(TextView)itemView.findViewById(R.id.future_temperature);
            future_weather_night=(TextView)itemView.findViewById(R.id.future_weather_night);
            future_direct=(TextView)itemView.findViewById(R.id.future_direct);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.future_weather_item,null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        mWidObjs=futureObj.get(position).getWid();

        SetWeatherUtils.setWeatherIcon(holder.future_weather_icon_day,mWidObjs.getDay());
        SetWeatherUtils.setWeatherIcon(holder.future_weather_icon_night,mWidObjs.getNight());

        holder.future_week.setText("");
        holder.future_date.setText((futureObj.get(position).getDate().substring(5,10)).replace("-","/"));
        holder.future_weather_day.setText(SetWeatherUtils.setWeather(mWidObjs.getDay()));
        holder.future_temperature.setText(futureObj.get(position).getTemperature());
        holder.future_weather_night.setText(SetWeatherUtils.setWeather(mWidObjs.getNight()));
        holder.future_direct.setText(futureObj.get(position).getDirect());

    }

    @Override
    public int getItemCount() {
        return futureObj.size();
    }

}
