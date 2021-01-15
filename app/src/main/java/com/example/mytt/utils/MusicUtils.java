package com.example.mytt.utils;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.mytt.R;
import com.example.mytt.activity.discover.music.MusicFilter;
import com.example.mytt.activity.discover.music.MyMusicActivity;
import com.example.mytt.service.MusicService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


import static android.content.ContentValues.TAG;

public class MusicUtils {

    public static final String MUSIC_PATH = new String("/storage/emulated/0/Music/");
    private static List<String> myMusicList = new ArrayList<String>();
    public static final int ORDER_PLAY = 1;//顺序播放
    public static final int RANDOM_PLAY = 2;//随机播放
    public static final int SINGLE_PLAY = 3;//单曲循环
    private static int play_mode = ORDER_PLAY;//播放模式  默认为顺序播放
    private MediaPlayer myMediaPlayer;
    public static Timer timer;
    public static Animation animation;
    private static int currentMusicPosition = 0;
    public static String current_music = "";
    public static String current_music_path = "";


    public static List getMusicList(){
        myMusicList.clear();
        File home = new File(MUSIC_PATH);
        Log.i(TAG, "home: " + home);
        Log.i(TAG, "home: " + home.listFiles());
        if (home.listFiles(new MusicFilter())!=null) {
            if (home.listFiles(new MusicFilter()).length > 0) {
                for (File file : home.listFiles(new MusicFilter())) {
                    myMusicList.add(file.getName());
                }
            }
        }
        return myMusicList;
    }


    public static void playMusic(Context context,MediaPlayer mediaPlayer,String musicName,SeekBar playSeekBar) {
        if (mediaPlayer==null){
            return;
        }
        try {
            System.out.println("进来了播放");
            mediaPlayer.reset();
           // mediaPlayer.setDataSource(MUSIC_PATH+musicName);
            mediaPlayer.setDataSource(musicName);
            mediaPlayer.prepare();
            mediaPlayer.start();
            Intent intent= new Intent(context, MusicService.class);
            intent.putExtra("musicName",musicName);
            context.startService(intent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (playSeekBar!=null){
            playSeekBar.setMax(mediaPlayer.getDuration());
            MyMusicActivity myMusicActivity=new MyMusicActivity();
            myMusicActivity.updateProgress(mediaPlayer,playSeekBar);
        }

    }
    //上一首
    public static void playLast(Context context,MediaPlayer mediaPlayer,SeekBar playSeekBar,List<String> myMusicList2,String tag_type){
        getCurrentMusic("tag_last",tag_type,myMusicList2);
        if (tag_type.equals("tag_local")){
            current_music_path=MUSIC_PATH+myMusicList.get(currentMusicPosition);
            playMusic(context, mediaPlayer,current_music_path,playSeekBar);
        }else{
            current_music_path="https://music.163.com/song/media/outer/url?id="+myMusicList2.get(currentMusicPosition)+".mp3";
            playMusic(context, mediaPlayer,current_music_path,playSeekBar);
        }
    }
    //下一首
    public static void playNext(Context context,MediaPlayer mediaPlayer,SeekBar playSeekBar,List<String> myMusicList2,String tag_type){
        getCurrentMusic("tag_next",tag_type,myMusicList2);


        if (tag_type.equals("tag_local")){
            current_music_path=MUSIC_PATH+myMusicList.get(currentMusicPosition);
            playMusic(context, mediaPlayer,current_music_path,playSeekBar);
        }else{
            current_music_path="https://music.163.com/song/media/outer/url?id="+myMusicList2.get(currentMusicPosition)+".mp3";
            playMusic(context, mediaPlayer,current_music_path,playSeekBar);
        }
    }
    //获取当前音乐
    public static void getCurrentMusic(String tag_order,String tag_type,List<String> myMusicList2){//tag_order 上一首：tag_last  下一首：tag_next
        switch (tag_order){
            case "tag_last":
                if (play_mode == ORDER_PLAY || play_mode == SINGLE_PLAY) {
                    if (tag_type.equals("tag_local")){
                        if (currentMusicPosition == 0) {
                            currentMusicPosition = myMusicList.size() - 1;
                        } else {
                            currentMusicPosition -= 1;
                        }
                    }else {
                        if (currentMusicPosition == 0) {
                            currentMusicPosition = myMusicList2.size() - 1;
                        } else {
                            currentMusicPosition -= 1;
                        }
                    }

                } else if (play_mode == RANDOM_PLAY) {
                    if (tag_type.equals("tag_local")){
                        currentMusicPosition = (int) Math.floor(Math.random() * myMusicList.size());
                    }else {
                        currentMusicPosition = (int) Math.floor(Math.random() * myMusicList2.size());
                    }

                }
                break;
            case "tag_next":
                if (play_mode == ORDER_PLAY || play_mode == SINGLE_PLAY) {
                    if (tag_type.equals("tag_local")){
                        if (currentMusicPosition == myMusicList.size() - 1) {
                            currentMusicPosition = 0;
                        } else {
                            currentMusicPosition += 1;
                        }
                    }else{
                        if (currentMusicPosition == myMusicList2.size() - 1) {
                            currentMusicPosition = 0;
                        } else {
                            currentMusicPosition += 1;
                        }
                    }

                } else if (play_mode == RANDOM_PLAY) {
                    if (tag_type.equals("tag_local")) {
                        currentMusicPosition = (int) Math.floor(Math.random() * myMusicList.size());
                    }else {
                        currentMusicPosition = (int) Math.floor(Math.random() * myMusicList2.size());
                    }
                }
                break;
        }
    }
    //播放 和 暂停
    public static void PalyOrNot(ImageView img,MediaPlayer mediaPlayer, RemoteViews remoteViews){

        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            if (img!=null){
                img.setImageResource(R.mipmap.ic_pause);
                 //if (animation!=null){
                     img.clearAnimation();
                 //}
            }
            if (remoteViews!=null){
                remoteViews.setImageViewResource(R.id.iv_play_or_pause,R.mipmap.ic_pause);
            }
        } else {
           /* if (!mediaPlayer.isPlaying() && mediaPlayer.getCurrentPosition() > 1){
                playMusic(context, mediaPlayer,myMusicList.get(currentMusicPosition),playSeekBar);
            }*/
            mediaPlayer.start();
            if (img!=null){
                img.setImageResource(R.mipmap.ic_playing);
                if (animation==null){
                    animation = AnimationUtils.loadAnimation(img.getContext(),R.anim.dvd_rotate);
                }
                img.startAnimation(animation);
            }
            if (remoteViews!=null){
                remoteViews.setImageViewResource(R.id.iv_play_or_pause,R.mipmap.ic_playing);
            }
        }
    }

    //
    public static void changePlayMode(Context context,ImageView way) {//点击改变播放模式
        if (play_mode < 3) {
            play_mode += 1;
        } else {
            play_mode = 1;
        }
        switch (play_mode) {
            case ORDER_PLAY://顺序播放
                way.setImageResource(R.mipmap.ic_cycle);
                Toast.makeText(context, "顺序播放", Toast.LENGTH_SHORT).show();
                break;
            case RANDOM_PLAY://随机播放
                way.setImageResource(R.mipmap.ic_random);
                Toast.makeText(context, "随机播放", Toast.LENGTH_SHORT).show();
                break;
            case SINGLE_PLAY://单曲循环
                way.setImageResource(R.mipmap.ic_single);
                Toast.makeText(context, "单曲循环", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }


}
