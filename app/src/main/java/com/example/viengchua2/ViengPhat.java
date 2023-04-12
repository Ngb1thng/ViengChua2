package com.example.viengchua2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import android.content.Intent;
import android.media.MediaPlayer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;
import pl.droidsonroids.gif.GifImageView;

public class ViengPhat extends AppCompatActivity {
    ImageButton btnchuong, btnmo, btndottram;
    TextView tvpc;
    GifImageView gifimg;
    CountDownTimer cdtm;
    int index=100;
    boolean checkht;
    Intent intent3 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_vieng_phat);
        btnmo = findViewById(R.id.btnmo);
        btnchuong = findViewById(R.id.btnchuong);
        btndottram = findViewById(R.id.btnluhuong);
        tvpc = findViewById(R.id.tv1);
        gifimg = findViewById(R.id.gif1);
        gifimg.setVisibility(View.INVISIBLE);
        tvpc.setVisibility(View.INVISIBLE);
        checkht = false;
        btnchuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Khai báo Intent công khai để khởi động Service
                Intent intent1 = new Intent(ViengPhat.this,
                        gochuong.class);
                startService(intent1);
            }
        });
        btnmo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Khai báo Intent công khai để khởi động Service
                Intent intent2 = new Intent(ViengPhat.this,
                        gomo.class);
                startService(intent2);
            }
        });

        btndottram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cdtm = new CountDownTimer(600000,6000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        tvpc.setText(""+index+" %");
                        index--;
                    }
                    @Override
                    public void onFinish() {
                        gifimg.setVisibility(View.INVISIBLE);
                        tvpc.setText("Đã hết hương !");
                        checkht = false;
                        index=100;
                        tvpc.setVisibility(View.INVISIBLE);
                    }
                };
                if (checkht == false) {
                    cdtm.start();
                    tvpc.setVisibility(View.VISIBLE);
                    gifimg.setVisibility(View.VISIBLE);
                    checkht=true;
                }
            }
        });
        intent3= new Intent(ViengPhat.this,nhacnen.class);
    }
    @Override
    protected void onStart() {
        super.onStart();
        startService(intent3);
    }
    @Override
    protected void onStop() {
        super.onStop();
        stopService(intent3);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}