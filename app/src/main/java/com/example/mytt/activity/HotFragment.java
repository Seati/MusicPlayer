package com.example.mytt.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mytt.R;
import com.example.mytt.adapter.MyVideoAdapter;

import java.io.IOException;

public class HotFragment extends Fragment implements View.OnClickListener{

    private View view_hot;
    private VideoView video_view;
    private SurfaceView sv;
    private SurfaceHolder holder;
    private MediaPlayer player;
    private String url[] ={
            "http://vfx.mtime.cn/Video/2019/02/04/mp4/190204084208765161.mp4",
            /*"http://vfx.mtime.cn/Video/2019/03/21/mp4/190321153853126488.mp4",
            "http://vfx.mtime.cn/Video/2019/03/19/mp4/190319222227698228.mp4"*/};
    private MyVideoAdapter myVideoAdapter;
    private RecyclerView recycler_video;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view_hot = View.inflate(getActivity(), R.layout.activity_hot_fragment, null);
        init();
        return view_hot;
    }

    @SuppressLint("WrongConstant")
    private void init() {
        /*video_view = (VideoView) view_hot.findViewById(R.id.video_view);
        MediaController mediaController = new MediaController(video_view.getContext());
        video_view.setVideoURI(Uri.parse(url));
        mediaController.setMediaPlayer(video_view);
        video_view.setMediaController(mediaController);
        video_view.start();*/

        recycler_video=(RecyclerView)view_hot.findViewById(R.id.recycler_video);
        recycler_video.setLayoutManager(new LinearLayoutManager(view_hot.getContext()));
        myVideoAdapter=new MyVideoAdapter(url);
        recycler_video.setAdapter(myVideoAdapter);
    }

    @Override
    public void onClick(View view) {

    }

   @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (hidden==false){
            System.out.println("执行开始");
            myVideoAdapter.myStart();
        }else{
            System.out.println("执行暂停");
            myVideoAdapter.myStop();
        }
    }

}
