package com.example.viengchua2;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomMusicAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Music> arrayList;
    private MediaPlayer mediaPlayer;
    private Boolean flag = true;

    public CustomMusicAdapter(Context context, int layout, ArrayList<Music> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public  class ViewHoler{
        TextView txtName;
        ImageView ivPlay,ivStop,ivNext,ivBack;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHoler  viewHoler;
        //Ánh xạ và gán giá trị
        if(convertView == null){
            viewHoler = new ViewHoler();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = layoutInflater.inflate(layout,null);
            viewHoler.txtName = (TextView) convertView.findViewById(R.id.txtName);
            viewHoler.ivPlay = (ImageView) convertView.findViewById(R.id.ivPlay);
            viewHoler.ivNext = (ImageView) convertView.findViewById(R.id.ivNext);
            viewHoler.ivBack = (ImageView) convertView.findViewById(R.id.ivBack);

            convertView.setTag(viewHoler);
        }
        else{
            viewHoler = (ViewHoler) convertView.getTag();
        }
        final Music music = arrayList.get(position);

        viewHoler.txtName.setText(music.getName());
//        viewHoler.txtSinger.setText(music.getSinger());
        return convertView;
    }
}