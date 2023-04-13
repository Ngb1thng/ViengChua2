package com.example.viengchua2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;
import pl.droidsonroids.gif.GifImageView;

public class TayPhuongTamThanh extends AppCompatActivity {
    ImageButton btnchuong, btnmo, btndottram;
    TextView tvpc,tv2;
    GifImageView gifimg;
    CountDownTimer cdtm;
    boolean checkht;
    Intent intent3 ;
    Animation chuchay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_tay_phuong_tam_thanh);
        btnmo = findViewById(R.id.btnmo);
        btnchuong = findViewById(R.id.btnchuong);
        btndottram = findViewById(R.id.btnluhuong);
        tvpc = findViewById(R.id.tv1);
        gifimg = findViewById(R.id.gif1);
        gifimg.setVisibility(View.INVISIBLE);
        tvpc.setVisibility(View.INVISIBLE);
        checkht = false;
        //animation
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_in_left);
        tv2 = findViewById(R.id.tv2);
        chuchay = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.anim_chuchay);
        //animation
        btnchuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(TayPhuongTamThanh.this,
                        gochuong.class);
                startService(intent1);
            }
        });
        btnmo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent2 = new Intent(TayPhuongTamThanh.this,
                        gomo.class);
                startService(intent2);
            }
        });

        btndottram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cdtm = new CountDownTimer(600000,1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        tvpc.setText(""+(millisUntilFinished/60000)+":"+(millisUntilFinished/1000)%60+"");
                    }
                    @Override
                    public void onFinish() {
                        gifimg.setVisibility(View.INVISIBLE);
                        tvpc.setText("Đã hết hương !");
                        checkht = false;
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
        intent3= new Intent(TayPhuongTamThanh.this,kinhadida.class);
    }
    @Override
    protected void onStart() {
        super.onStart();
        startService(intent3);
        tv2.startAnimation(chuchay);
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