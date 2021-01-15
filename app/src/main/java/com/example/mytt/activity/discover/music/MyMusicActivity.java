package com.example.mytt.activity.discover.music;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.mytt.R;
import com.example.mytt.adapter.LocalMusicListAdapter;
import com.example.mytt.adapter.NetMusicListAdapter;
import com.example.mytt.base.BaseActivity;
import com.example.mytt.bean.SearchMusicBean;
import com.example.mytt.dialog.CommonDialog;
import com.example.mytt.okhttp.HttpApi;
import com.example.mytt.okhttp.OkHttpWrapper;
import com.example.mytt.service.MusicService;
import com.example.mytt.utils.MusicUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.mytt.utils.MusicUtils.MUSIC_PATH;

public class MyMusicActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    Intent intent;
    @BindView(R.id.music_list)
    ListView musicList;
    @BindView(R.id.way)
    ImageView way;
    @BindView(R.id.iv_last)
    ImageView ivLast;
    @BindView(R.id.iv_start)
    ImageView ivStart;
    @BindView(R.id.iv_next)
    ImageView ivNext;
    @BindView(R.id.playSeekBar)
    SeekBar playSeekBar;
    @BindView(R.id.louder)
    ImageView louder;
    @BindView(R.id.iv_like)
    ImageView ivLike;
    @BindView(R.id.iv_like2)
    ImageView ivLike2;
    @BindView(R.id.playing_music_name)
    TextView playingMusicName;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    private int currentMusicPosition = 0;
    public static MediaPlayer mediaPlayer;
    private List<String> myMusicList = new ArrayList<String>();
    private List<String> myMusicList2 = new ArrayList<String>();
    private LocalMusicListAdapter localMusicListAdapter;
    private NetMusicListAdapter netMusicListAdapter;
    private static Timer timer;
    public static Boolean isSeekBarChanging;

    private SearchMusicBean searchMusicBean;
    private List<SearchMusicBean.DataObj.SongObj>songObjs;
    private boolean is_like=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_music);
        ButterKnife.bind(this);
        //读取本地音乐文件
        musicList();
        init();
    }

    private void init() {
        mediaPlayer = new MediaPlayer();
        musicList.setOnItemClickListener(this);
        mediaPlayer.setOnErrorListener(errorListener);
        mediaPlayer.setOnCompletionListener(musicListener);
        intent = new Intent(this, MusicService.class);
        playSeekBar.setOnSeekBarChangeListener(new MySeekBar());

    }

    private void musicList() {
        myMusicList = MusicUtils.getMusicList();
        localMusicListAdapter = new LocalMusicListAdapter(this, myMusicList);
        musicList.setAdapter(localMusicListAdapter);
        localMusicListAdapter.notifyDataSetChanged();
        netMusicListAdapter=null;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        currentMusicPosition = i;
        System.out.println("播放");
        if (netMusicListAdapter!=null){
            netMusicListAdapter.setUpdateView(i);
            MusicUtils.playMusic(MyMusicActivity.this, mediaPlayer,"https://music.163.com/song/media/outer/url?id="+myMusicList2.get(currentMusicPosition)+".mp3", playSeekBar);
        }else {
            localMusicListAdapter.setUpdateView(i);
            MusicUtils.playMusic(MyMusicActivity.this, mediaPlayer, MUSIC_PATH+myMusicList.get(currentMusicPosition), playSeekBar);
        }
    }

    private MediaPlayer.OnErrorListener errorListener = new MediaPlayer.OnErrorListener() {
        @Override
        public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
            return true;
        }
    };
    private MediaPlayer.OnCompletionListener musicListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            if (netMusicListAdapter!=null){
                MusicUtils.playNext(MyMusicActivity.this, mediaPlayer, playSeekBar,myMusicList2,"tag_net");
            }else {
                MusicUtils.playNext(MyMusicActivity.this, mediaPlayer, playSeekBar,null,"tag_local");
            }
        }
    };

    @OnClick({R.id.way, R.id.iv_last, R.id.iv_start, R.id.iv_next, R.id.louder, R.id.iv_like,R.id.tv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.louder:
                break;
            case R.id.way:
                MusicUtils.changePlayMode(MyMusicActivity.this, way);
                break;
            case R.id.iv_last:
                if (netMusicListAdapter!=null){
                    MusicUtils.playLast(MyMusicActivity.this, mediaPlayer, playSeekBar,myMusicList2,"tag_net");
                }else {
                    MusicUtils.playLast(MyMusicActivity.this, mediaPlayer, playSeekBar,null,"tag_loacl");
                }
                break;
            case R.id.iv_start:
                if (mediaPlayer.getCurrentPosition()>1){
                    MusicUtils.PalyOrNot(ivStart, mediaPlayer, null);
                }else {
                        String musicRes;
                    if (netMusicListAdapter!=null){
                        musicRes="https://music.163.com/song/media/outer/url?id="+myMusicList2.get(0)+".mp3";
                        MusicUtils.playMusic(MyMusicActivity.this, mediaPlayer,musicRes,playSeekBar);
                    }else {
                        musicRes=MUSIC_PATH+myMusicList.get(0);
                        MusicUtils.playMusic(MyMusicActivity.this, mediaPlayer,musicRes,playSeekBar);
                    }
                    ivStart.setImageResource(R.mipmap.ic_playing);
                   Animation animation = AnimationUtils.loadAnimation(this,R.anim.dvd_rotate);
                   ivStart.startAnimation(animation);
                }

                break;
            case R.id.iv_next:
                if (netMusicListAdapter!=null){
                    MusicUtils.playNext(MyMusicActivity.this, mediaPlayer, playSeekBar,myMusicList2,"tag_net");
                }else {
                    MusicUtils.playNext(MyMusicActivity.this, mediaPlayer, playSeekBar,null,"tag_local");
                }
                break;
            case R.id.iv_like:
                if (is_like==false){
                    Animation animation = AnimationUtils.loadAnimation(this, R.anim.like_anim);
                    animation.setAnimationListener(animationListener);
                    ivLike2.setVisibility(View.VISIBLE);
                    ivLike2.startAnimation(animation);
                    is_like=true;
                }else {
                    ivLike.setImageResource(R.mipmap.like1);
                    is_like=false;
                }

                break;
            case R.id.tv_search:
                if (!etSearch.getText().toString().equals("")){
                    mCommonLoadDialog.show();
                    HttpApi.searchMusic(this,etSearch.getText().toString(),httpResultCallBack);
                }
                break;
        }
    }
    private OkHttpWrapper.HttpResultCallBack httpResultCallBack=new OkHttpWrapper.HttpResultCallBack() {
        @Override
        public void httpResultCallBack(int tag, String result, int progress) {
            if (!checkResult(tag, result)) {
                return;
            }
            switch (tag){
                case HttpApi.TAG_SEARCH_MUSIC:
                    searchMusicBean= new Gson().fromJson(result, SearchMusicBean.class);
                    if (searchMusicBean.getData()!=null){
                        commonHandle.sendEmptyMessage(tag);
                    }
                    break;
            }
        }
    };

    @Override
    public void messageCallBack(Message message) {
        super.messageCallBack(message);
        switch (message.what){
            case HttpApi.TAG_SEARCH_MUSIC:
                songObjs=searchMusicBean.getData().getSongs();
                if (songObjs!=null){
                    myMusicList.clear();
                    myMusicList2.clear();
                    for (int i=0;i<songObjs.size();i++){
                        myMusicList.add(songObjs.get(i).getName());
                        myMusicList2.add(songObjs.get(i).getId());
                    }
                    netMusicListAdapter = new NetMusicListAdapter(getApplication(),songObjs);
                    musicList.setAdapter(netMusicListAdapter);
                    netMusicListAdapter.notifyDataSetChanged();
                    localMusicListAdapter=null;
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //  updateProgress(mediaPlayer,playSeekBar);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        stopService(intent);
        stopTimer(playSeekBar);
    }

    //设置seekBar进度条
    private class MySeekBar implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            System.out.println("onProgressChanged" + i + "%");
            seekBar.setProgress(i);
        }

        /*滚动时,应当暂停后台定时器*/
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            isSeekBarChanging = true;
        }

        /*滑动结束后，重新设置值*/
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            isSeekBarChanging = false;
            System.out.println("onStopTrackingTouch" + seekBar.getProgress() + "%");
            mediaPlayer.seekTo(seekBar.getProgress());

        }
    }

    //更新进度条
    public static void updateProgress(MediaPlayer mediaPlayer, SeekBar playSeekBar) {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //获取歌曲的进度
                int progress = mediaPlayer.getCurrentPosition();
                //将获取歌曲的进度赋值给seekbar
                playSeekBar.setProgress(progress);
            }
        }, 0, 1000);
    }

    public static void stopTimer(SeekBar playSeekBar) {
        // mediaPlayer.release();
        timer.cancel();
        if (playSeekBar != null) {
            playSeekBar.setProgress(0);
        }
    }

    private Animation.AnimationListener animationListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            ivLike2.setVisibility(View.GONE);
            ivLike.setImageResource(R.mipmap.like2);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };
}
