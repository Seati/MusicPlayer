package com.example.mytt.adapter;


import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytt.R;

import java.io.IOException;

public class MyVideoAdapter extends RecyclerView.Adapter<MyVideoAdapter.ViewHolder> {
    private  String[]video_uri;
    private SurfaceHolder mSurfaceHolder;
    private MediaPlayer player;

    public MyVideoAdapter(String[]video_uri){
        this.video_uri=video_uri;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item_layout,null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        //通过SurfaceView + mediaplayer实现
        player=new MediaPlayer();
        try {
            mSurfaceHolder=holder.surface_view.getHolder();
            mSurfaceHolder.addCallback(new MyCallBack());
            player.setDataSource(holder.itemView.getContext(), Uri.parse(video_uri[position]));
            player.prepare();
            player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    //progressBar.setVisibility(View.INVISIBLE);
                        player.start();
                        player.setLooping(true);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("完成了");
    }

    @Override
    public int getItemCount() {
        return video_uri.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        VideoView videoView;
        SurfaceView surface_view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //SurfaceView+MediaPlayer
            surface_view = (SurfaceView) itemView.findViewById(R.id.surface_view);

        }
    }
    private class MyCallBack implements SurfaceHolder.Callback {
        @Override
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            player.setDisplay(mSurfaceHolder);
        }

        @Override
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

        }
    }
    public void myStart(){
        player.start();
        player.setLooping(true);
    }
    public void myStop(){
        player.pause();
        player.setLooping(false);
    }
}
