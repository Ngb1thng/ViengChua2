package com.example.viengchua2;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;


public class kinhduocsuluulyquangvuongphat extends Service {
    MediaPlayer kinhds;
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public void onCreate() {
        super.onCreate();
        kinhds = MediaPlayer.create(kinhduocsuluulyquangvuongphat.this, R.raw.kinhduocsuluulyquang);
        kinhds.isLooping();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int
            startId) {
//        if(nhacnen.isPlaying()){
////          nhacnen.stop();
////          nhacnen.release();
//            nhacnen = null;
//            nhacnen= MediaPlayer.create(nhacnen.this, R.raw.soundmo);}
        kinhds.start();
//
        return super.onStartCommand(intent, flags, startId);
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        kinhds.stop();
    }
}