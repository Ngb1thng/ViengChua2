package com.example.viengchua2;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class gomo extends Service {
    MediaPlayer gomo;
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    //Gọi hàm OnCreate để tạo đối tượng mà Service quản lý
    @Override
    public void onCreate() {
        super.onCreate();
        gomo = MediaPlayer.create(gomo.this, R.raw.soundmo);
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int
            startId) {
        if(gomo.isPlaying()){
//          gomo.stop();
//            gomo.release();
            gomo = null;
            gomo= MediaPlayer.create(gomo.this, R.raw.soundmo);}
        gomo.start();
        return super.onStartCommand(intent, flags, startId);
    }
}