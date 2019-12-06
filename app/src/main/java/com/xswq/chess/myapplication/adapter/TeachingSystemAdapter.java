package com.xswq.chess.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.bean.TeacherSystemImage;
import com.xswq.chess.myapplication.utils.Util;
import com.xswq.chess.myapplication.widget.ShowImagesDialog;
import java.util.ArrayList;
import java.util.List;

public class TeachingSystemAdapter extends BaseAdapter {

    private Context context;
    private List<TeacherSystemImage.DataBean.ListBean> list;
    private List<String> imageString;


    public TeachingSystemAdapter(Context context, List<TeacherSystemImage.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ComViewHolder comHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.teaching_system_itemrecler_layout, null);
            comHolder = new ComViewHolder();
            comHolder.teacher_outline_id = convertView.findViewById(R.id.teacher_outline_id);
            comHolder.show_big_image_id = convertView.findViewById(R.id.show_big_image_id);
            convertView.setTag(comHolder);
        } else {
            comHolder = (ComViewHolder) convertView.getTag();
        }

        Glide.with(context)
                .load(Util.signString(list.get(position).getImgUrl()))
                .asBitmap()
                .error(R.mipmap.default_xswq)
                .placeholder(R.mipmap.default_xswq)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(comHolder.teacher_outline_id);

        imageString = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            imageString.add(list.get(i).getImgUrl());
        }
        comHolder.show_big_image_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ShowImagesDialog(context, imageString).show();
            }
        });
        return convertView;
    }

    class ComViewHolder {
        ImageView teacher_outline_id;
        ImageView show_big_image_id;
    }



}


