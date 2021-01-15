package com.example.mytt.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.example.mytt.R;
import com.example.mytt.activity.discover.music.MyMusicActivity;
import com.example.mytt.utils.MusicUtils;

public class MusicService extends Service {
    private static final String TAG = MusicService.class.getSimpleName();

    //private MusicReceiver musicReceiver;
    private BroadcastReceiver musicReceiver;
    private MediaPlayer mediaPlayer;
    public  RemoteViews remoteViews;
    private Notification notification;
    private NotificationManager manager;
    private MyMusicActivity myMusicActivity;
    public static final String ACTION_PLAY_OR_NOT = "play_or_not";
    public static final String ACTION_LAST = "last";
    public static final String ACTION_NEXT = "next";
    public static final String ACTION_CLOSE = "close";

    @Override
    public void onCreate() {
        super.onCreate();
        myMusicActivity=new MyMusicActivity();
        mediaPlayer=myMusicActivity.mediaPlayer;
        View view = LayoutInflater.from(this).inflate(R.layout.layout_music_service, null);

        musicReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action=intent.getAction();
                String music_name=intent.getStringExtra("musicName");
                if (action.equals(MusicService.ACTION_PLAY_OR_NOT)){
                        mStart_pause();
                }else if (action.equals(MusicService.ACTION_LAST)){
                        mLast();
                }else if (action.equals(MusicService.ACTION_NEXT)){
                        mNext();
                }else if (action.equals(MusicService.ACTION_CLOSE)){
                        mClose();
                }
            }
        };
        IntentFilter filter = new IntentFilter();

        filter.addAction(ACTION_PLAY_OR_NOT);
        filter.addAction(ACTION_LAST);
        filter.addAction(ACTION_NEXT);
        filter.addAction(ACTION_CLOSE);

        registerReceiver(musicReceiver, filter);

        createService();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        stopForeground(true);
        mClose();
        super.onDestroy();
    }

    private void mStart_pause() {
        MusicUtils.PalyOrNot(null,mediaPlayer,remoteViews);
    }

    private void mLast() {
        MusicUtils.playLast(this, mediaPlayer, null,null,"tag_local");
        createService();
    }

    private void mNext() {
        MusicUtils.playNext(this, mediaPlayer, null,null,"tag_local");
        createService();
    }

    public void mClose() {
        if (musicReceiver != null) {
            unregisterReceiver(musicReceiver);
        }
        stopForeground(true);
        if (mediaPlayer!=null){
            mediaPlayer.release();
        }
        mediaPlayer=null;
        myMusicActivity.stopTimer(null);
    }

    private void createService(){
        // 自定义布局
        remoteViews = new RemoteViews(this.getPackageName(), R.layout.layout_music_service);// 获取remoteViews（参数一：包名；参数二：布局资源）
        //remoteViews.setTextViewText(R.id.music_name,(CharSequence) music_name);

        // 上一首
        Intent intentLast = new Intent(ACTION_LAST);
        PendingIntent stopPendingIntent = PendingIntent.getBroadcast(this, 1, intentLast, PendingIntent.FLAG_CANCEL_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.iv_last, stopPendingIntent);

        // 暂停/播放
        Intent intentPlay = new Intent(ACTION_PLAY_OR_NOT);
        PendingIntent playPendingIntent = PendingIntent.getBroadcast(this, 2, intentPlay, PendingIntent.FLAG_CANCEL_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.iv_play_or_pause, playPendingIntent);

        // 下一首
        Intent intentNext = new Intent(ACTION_NEXT);
        PendingIntent nextPendingIntent = PendingIntent.getBroadcast(this, 3, intentNext, PendingIntent.FLAG_CANCEL_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.iv_next, nextPendingIntent);

        // 关闭
        Intent intentclose = new Intent(ACTION_CLOSE);
        PendingIntent closePendingIntent = PendingIntent.getBroadcast(this, 0, intentclose, PendingIntent.FLAG_CANCEL_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.iv_close, closePendingIntent);

        Notification.Builder builder = new Notification.Builder(this.getApplicationContext()).setContent(remoteViews);// 设置自定义的Notification内容
        builder.setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_app);

        // 获取NotificationManager实例
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //修改安卓8.1以上系统报错
            NotificationChannel notificationChannel = new NotificationChannel("100", "channel",
                    NotificationManager.IMPORTANCE_MIN);
            notificationChannel.enableLights(false);//如果使用中的设备支持通知灯，则说明此通知通道是否应显示灯
            notificationChannel.setShowBadge(false);//是否显示角标
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_SECRET);
            manager.createNotificationChannel(notificationChannel);
            builder.setChannelId("100");
        }
        startForeground(100, builder.build());
    }
}
