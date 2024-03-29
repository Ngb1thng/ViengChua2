package com.example.viengchua2;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class GoMoTungKinh extends AppCompatActivity {
    ImageButton kchuong,mo,chuong;
    Chronometer cnmt;
    ImageView dui1,dui2,dui3,dui4;
    Button auto;
    Handler handler = new Handler();
    Runnable runnable;
        SeekBar skb;
    TextView tvw;
    MediaPlayer mp,mp1,mp2;
    String date;
    DBHelper DB;
    int delay = 800;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_go_mo_tung_kinh);

        DB = new DBHelper(this);
        cnmt =  findViewById(R.id.simpleChronometer);
        cnmt.start();

        Date currentTime = Calendar.getInstance().getTime();
        date = currentTime.toString();

        dui1 = findViewById(R.id.dui1);
        dui1.setVisibility(View.INVISIBLE);
        dui2 = findViewById(R.id.dui2);
        dui2.setVisibility(View.INVISIBLE);
        dui3 = findViewById(R.id.dui3);
        dui3.setVisibility(View.INVISIBLE);

        mo = findViewById(R.id.mo);
        mp = MediaPlayer.create(this,R.raw.soundmo);
        mo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                if (mp.isPlaying()) mp = MediaPlayer.create(GoMoTungKinh.this,R.raw.soundmo);
                dui1.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dui1.setVisibility(View.INVISIBLE);
                    }
                }, 100);
            }
        });

        chuong = findViewById(R.id.chuong);
        mp1 = MediaPlayer.create(this,R.raw.chuong1);
        chuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp1.start();
                if (mp1.isPlaying()) mp1 = MediaPlayer.create(GoMoTungKinh.this,R.raw.chuong1);
                dui2.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dui2.setVisibility(View.INVISIBLE);
                    }
                }, 100);
            }
        });

        kchuong = findViewById(R.id.kchuong);
        mp2 = MediaPlayer.create(this,R.raw.kchuong);
        kchuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp2.start();
                if (mp2.isPlaying()) mp2 = MediaPlayer.create(GoMoTungKinh.this,R.raw.kchuong);
                dui3.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dui3.setVisibility(View.INVISIBLE);
                    }
                }, 100);
            }
        });

        dui4 = findViewById(R.id.dui4);
        dui4.setVisibility(View.INVISIBLE);
        skb = (SeekBar) findViewById(R.id.seekBar);
        auto = findViewById(R.id.auto);
        auto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation ani = AnimationUtils.loadAnimation(GoMoTungKinh.this, R.anim.rotate);
                ani.setDuration(delay);
                if(dui4.getVisibility() == View.INVISIBLE) {
                    dui4.setVisibility(View.VISIBLE);
                    handler.postDelayed(runnable = new Runnable() {
                        @Override
                        public void run() {
                            dui4.startAnimation(ani);
                            mp.start();
                            handler.postDelayed(this, delay);
                        }
                    },delay);

                }
                else{
                    handler.removeCallbacksAndMessages(null);
                    dui4.setVisibility(View.INVISIBLE);
                }
            }
        });



        tvw = findViewById(R.id.textView);
        tvw.setText("1");

        skb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressValue =1;
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    progressValue = i;
                    tvw.setText(String.valueOf(skb.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                delay = 1000-200*progressValue;
                tvw.setText(String.valueOf(skb.getProgress()));
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacksAndMessages(null);
        String time = cnmt.getText().toString();
        DB.insertuserdata(this.getClass().getSimpleName(),time,date);

    }
}