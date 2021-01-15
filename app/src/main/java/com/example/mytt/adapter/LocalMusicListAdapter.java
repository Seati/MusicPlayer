package com.example.mytt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mytt.R;

import java.util.ArrayList;
import java.util.List;

public class LocalMusicListAdapter extends BaseAdapter {
    private Context context;

    private List<String> myMusicList = new ArrayList<String>();
    private int isSelect;
    public LocalMusicListAdapter(Context context, List<String> myMusicList){
        this.context=context;
        this.myMusicList=myMusicList;

    }
    @Override
    public int getCount() {
        return myMusicList.size();
    }

    @Override
    public Object getItem(int i) {
        return myMusicList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1= LayoutInflater.from(context).inflate(R.layout.music_item_layout,null);
        ImageView iv_isplaying=(ImageView)view1.findViewById(R.id.iv_isplaying);
        TextView tv_music_name=(TextView)view1.findViewById(R.id.tv_music_name);
        TextView tv_music_artist=(TextView)view1.findViewById(R.id.tv_music_artist);

        tv_music_name.setText(myMusicList.get(i));
        //tv_music_artist.setText(myMusicList.get(i));
        if (getUpdateView()==i){
            iv_isplaying.setVisibility(View.VISIBLE);
        }else {
            iv_isplaying.setVisibility(View.GONE);
        }
        return view1;
    }
    private int getUpdateView(){
        return isSelect;
    }
    public void setUpdateView(int i){
        this.isSelect=i;
        notifyDataSetChanged();
    }
}
