package com.example.viengchua2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageButton btn1,btn2,btn3,btnH;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.imageButton1);
        btn2 = findViewById(R.id.imageButton2);
        btn3 = findViewById(R.id.imageButton3);
        btnH = findViewById(R.id.histew);
        DB = new DBHelper(this);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity1();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity3();
            }
        });

        btnH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Hoạt động :"+res.getString(1)+"\n");
                    buffer.append("Vào lúc :"+res.getString(3)+"\n");
                    buffer.append("Thời gian sử dụng :"+res.getString(2)+"\n\n");

                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Lịch sử hoạt động");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }

    public void openActivity1(){
        Intent intent = new Intent(this, Viengphat.class);
        startActivity(intent);
    }

    public void openActivity2(){
        Intent intent = new Intent(this, GoMoTungKinh.class);
        startActivity(intent);
    }

    public void openActivity3(){
        Intent intent = new Intent(this, PhatGiaoAudio.class);
        startActivity(intent);
    }
}