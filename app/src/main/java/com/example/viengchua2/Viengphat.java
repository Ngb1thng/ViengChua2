package com.example.viengchua2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
public class Viengphat extends AppCompatActivity {

    ImageButton ttp,tptt,dstt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_viengphat);
        ttp = findViewById(R.id.imageButton1);
        tptt = findViewById(R.id.imageButton2);
        dstt = findViewById(R.id.imageButton3);

        ttp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Viengphat.this, Tamthephat.class);
                startActivity(intent1);
            }
        });

        tptt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(Viengphat.this, TayPhuongTamThanh.class);
                startActivity(intent2);
            }
        });

        dstt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(Viengphat.this, DuocSuTamTon.class);
                startActivity(intent3);
            }
        });
    }
}