package com.example.viengchua2;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class kinhadida extends Service {

    MediaPlayer kinhadida;
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public void onCreate() {
        super.onCreate();
        kinhadida = MediaPlayer.create(kinhadida.this, R.raw.kinhadida);
        kinhadida.isLooping();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int
            startId) {
//        if(nhacnen.isPlaying()){
////          nhacnen.stop();
////          nhacnen.release();
//            nhacnen = null;
//            nhacnen= MediaPlayer.create(nhacnen.this, R.raw.soundmo);}
        kinhadida.start();
//
        return super.onStartCommand(intent, flags, startId);
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        kinhadida.stop();
    }
}