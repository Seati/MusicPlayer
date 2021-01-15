package com.example.mytt.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MusicReceiver extends BroadcastReceiver {
    private static final String TAG = MusicReceiver.class.getSimpleName();

    public MusicReceiver(){

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action=intent.getAction();

        if (action.equals(MusicService.ACTION_PLAY_OR_NOT)){

        }else if (action.equals(MusicService.ACTION_LAST)){

        }else if (action.equals(MusicService.ACTION_NEXT)){

        }else if (action.equals(MusicService.ACTION_CLOSE)){

        }
    }
}
