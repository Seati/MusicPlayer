package com.example.mytt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mytt.R;
import com.example.mytt.bean.SearchMusicBean;

import java.util.ArrayList;
import java.util.List;

public class NetMusicListAdapter extends BaseAdapter {
    private Context context;

    private List<SearchMusicBean.DataObj.SongObj> netMusicList = new ArrayList<SearchMusicBean.DataObj.SongObj>();
    private List<SearchMusicBean.DataObj.SongObj.ArtistObj> artistList = new ArrayList<SearchMusicBean.DataObj.SongObj.ArtistObj>();
    private int isSelect;
    public NetMusicListAdapter(Context context, List<SearchMusicBean.DataObj.SongObj> netMusicList){
        this.context=context;
        this.netMusicList=netMusicList;
    }
    @Override
    public int getCount() {
        return netMusicList.size();
    }

    @Override
    public Object getItem(int i) {
        return netMusicList.get(i);
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
        artistList=netMusicList.get(i).getArtists();
        tv_music_name.setText(netMusicList.get(i).getName());
        if (artistList.get(0).getName()!=null){
            tv_music_artist.setVisibility(View.VISIBLE);
            tv_music_artist.setText(artistList.get(0).getName());
        }
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
