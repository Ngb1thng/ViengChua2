package com.example.viengchua2;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class gochuong extends Service {
    //Khai báo đối tượng mà Service quản lý
    MediaPlayer gochuong;
    @Override
    public IBinder onBind(Intent intent) {

        throw new UnsupportedOperationException("Not yet implemented");
    }
    //Gọi hàm OnCreate để tạo đối tượng mà Service quản lý
    @Override
    public void onCreate() {
        super.onCreate();
        gochuong = MediaPlayer.create(gochuong.this, R.raw.soundgochuong);
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int
            startId) {
        if(gochuong.isPlaying()){
//          mymedia.stop();
            //gochuong.release();
            gochuong = null;
            gochuong= MediaPlayer.create(gochuong.this, R.raw.soundgochuong);}
        gochuong.start();
      return super.onStartCommand(intent, flags, startId);
    }
    //Gọi Hàm onDestroy để dừng đối tượng mà Service quản lý
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        mymedia.stop();
//    }
}