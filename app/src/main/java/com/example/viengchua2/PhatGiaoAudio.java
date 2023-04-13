package com.example.viengchua2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;

public class PhatGiaoAudio extends AppCompatActivity {
    private ArrayList<Music> arrayList;
    private CustomMusicAdapter adapter;
    private ListView ListOfSongs;
    private MediaPlayer mediaPlayer;
    TextView txtName,txtNameSong,txtTime,txtTimeSong;
    ImageView ivPlay,ivNext,ivBack;
    SeekBar seekBar;

    int position = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_phat_giao_audio);


        anhXa();
        addSong();

        ListOfSongs = (ListView) findViewById(R.id.listOfSongs);
        adapter = new CustomMusicAdapter(this,R.layout.custom_music_items,arrayList);
        ListOfSongs.setAdapter(adapter);

        ListOfSongs.setOnItemClickListener(((parent, view, position1, id) -> {
            txtNameSong.setText(arrayList.get(position1).getName());
            position = position1;
            for(int i = 0;i < parent.getChildCount();i++){
                parent.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
            };
            view.setBackgroundColor(Color.LTGRAY);
            mediaPlayer.release();
            KhoiTaoMediaPlayer();
            mediaPlayer.start();
            ivPlay.setImageResource(R.drawable.pause);
        }));
        KhoiTaoMediaPlayer();


        ivNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position++;


                if(position > arrayList.size()-1){
                    position = 0;
                }

                mediaPlayer.release();
                KhoiTaoMediaPlayer();
                txtNameSong.setText(arrayList.get(position).getName());
                ivPlay.setImageResource(R.drawable.pause);
                mediaPlayer.start();
                handelChangeColorNext();

                setTimeTotal();
            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position--;
                if(position < 0){
                    position = arrayList.size()-1;
                }if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                mediaPlayer.release();
                KhoiTaoMediaPlayer();
                txtNameSong.setText(arrayList.get(position).getName());
                ivPlay.setImageResource(R.drawable.pause);
                mediaPlayer.start();
                setTimeTotal();
                handelChangeColorBack();

            }
        });

        ivPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();

                    ivPlay.setImageResource(R.drawable.play);
                }else{
                    mediaPlayer.start();
                    ivPlay.setImageResource(R.drawable.pause);
                }
                ListOfSongs.getChildAt(position).setBackgroundColor(Color.LTGRAY);
                setTimeTotal();
                updateTimeSong();
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });

    }
    private void handelChangeColorNext(){
        if(mediaPlayer.isPlaying()){
            ListOfSongs.getChildAt(position).setBackgroundColor(Color.LTGRAY);
            if(position>0){
                ListOfSongs.getChildAt(position-1).setBackgroundColor(Color.TRANSPARENT);
            }
            else{
                ListOfSongs.getChildAt(0).setBackgroundColor(Color.LTGRAY);
                ListOfSongs.getChildAt(arrayList.size()-1).setBackgroundColor(Color.TRANSPARENT);
            }
        }
    }
    private void handelChangeColorBack(){
        if(mediaPlayer.isPlaying()){
            ListOfSongs.getChildAt(position).setBackgroundColor(Color.LTGRAY);
            if(position < 0){
                ListOfSongs.getChildAt(0).setBackgroundColor(Color.TRANSPARENT);
                ListOfSongs.getChildAt(arrayList.size()-1).setBackgroundColor(Color.LTGRAY);

            }
            else if(position == arrayList.size()-1){
                ListOfSongs.getChildAt(0).setBackgroundColor(Color.TRANSPARENT);
            }else{
                ListOfSongs.getChildAt(position+1).setBackgroundColor(Color.TRANSPARENT);
            }
        }
    }
    private void updateTimeSong(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat timeLine = new SimpleDateFormat("HH:mm:ss");
                timeLine.setTimeZone(TimeZone.getTimeZone("UTC"));
                txtTimeSong.setText(timeLine.format(mediaPlayer.getCurrentPosition()));
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                handler.postDelayed(this,500);
            }
        },100);
    }
    private void setTimeTotal(){
        SimpleDateFormat timeLine = new SimpleDateFormat("HH:mm:ss");
        timeLine.setTimeZone(TimeZone.getTimeZone("UTC"));
        txtTime.setText(timeLine.format(mediaPlayer.getDuration()));
    }
    private void KhoiTaoMediaPlayer(){
        mediaPlayer = MediaPlayer.create(PhatGiaoAudio.this,arrayList.get(position).getSong());
        seekBar.setMax(mediaPlayer.getDuration());
    }
    private void addSong(){
        arrayList = new ArrayList<>();
        arrayList.add(new Music("1, Kinh Vu Lan",R.raw.kinhvulan));
        arrayList.add(new Music("2, Kinh Lăng Nghiêm",R.raw.kinhlangnghiem));
        arrayList.add(new Music("3, Kinh Dược Sư",R.raw.kinhduocsu));
        arrayList.add(new Music("4, Kinh Phổ Môn",R.raw.kinhphomon));
        arrayList.add(new Music("5, Kinh Tám Điều Từ Tâm",R.raw.kinhtamdieututam));
        arrayList.add(new Music("6, Kinh Từ Bi Thủy Sám",R.raw.kinhtubithuysam));
        arrayList.add(new Music("7, Kinh A Di Đà",R.raw.kinhadida));
        arrayList.add(new Music("8, Kinh Từ Bi Sám Hối",R.raw.kinhtubisamhoi));
    }
    private void anhXa(){
        txtName = (TextView) findViewById(R.id.txtName);
        txtTime = (TextView) findViewById(R.id.txtTime);
        txtTimeSong = (TextView) findViewById(R.id.txtTimeSong);
        ivPlay = (ImageView) findViewById(R.id.ivPlay);
        ivBack = (ImageView) findViewById(R.id.ivBack);
        ivNext = (ImageView) findViewById(R.id.ivNext);
        txtNameSong = (TextView) findViewById(R.id.txtNameSong);
        seekBar = (SeekBar) findViewById(R.id.seekbar);
    }
}