package com.example.mytt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mytt.R;

public class MultilingualAdapter extends BaseAdapter {
    private Context context;
    private String[]datalist;
    private int isSelect=0;
    public MultilingualAdapter(Context context,String[]datalist){
        this.context=context;
        this.datalist=datalist;
    }
    @Override
    public int getCount() {
        return datalist.length;
    }

    @Override
    public Object getItem(int i) {
        return datalist[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1= LayoutInflater.from(context).inflate(R.layout.language_item_layout,null);
        TextView tv_language=(TextView)view1.findViewById(R.id.tv_language);
        ImageView img_checkbox=(ImageView)view1.findViewById(R.id.img_checkbox);
        tv_language.setText(datalist[i]);

        if (isSelect==i){
            img_checkbox.setImageResource(R.mipmap.checked_true);
        }else{
            img_checkbox.setImageResource(R.mipmap.checked_false);
        }
        return view1;
    }
    public void setSelect(int isSelect){
        this.isSelect=isSelect;
        notifyDataSetChanged();
    }
    public int getIsSelect(){
        return isSelect;
    }
}
