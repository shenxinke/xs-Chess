package com.xswq.chess.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.bean.GuideVideo;

import java.util.List;

public class GuideVideoAdapter extends BaseAdapter {

    private Context context;
    private List<GuideVideo.DataBean.ListBean> listBeans;

    public GuideVideoAdapter(Context context, List<GuideVideo.DataBean.ListBean> listBeans) {
        this.context = context;
        this.listBeans = listBeans;
    }

    @Override
    public int getCount() {
        return listBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return listBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ComViewHolder comHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.guide_video_item_layout, null);
            comHolder = new ComViewHolder();
            comHolder.guide_video_imageid = convertView.findViewById(R.id.guide_video_imageid);
            comHolder.guide_video_textid = convertView.findViewById(R.id.guide_video_textid);
            convertView.setTag(comHolder);
        } else {
            comHolder = (ComViewHolder) convertView.getTag();
        }
        String juniorVideoname = listBeans.get(position).getJuniorVideoname();
        comHolder.guide_video_textid.setText(juniorVideoname.split("_")[1]);
        try {
            String wyyVideoUrl = listBeans.get(position).getWyyVideoUrl();
            Glide.with(context)
                    .load(wyyVideoUrl)
                    .asBitmap()
                    .placeholder(R.mipmap.guide1_bg)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(comHolder.guide_video_imageid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }


    class ComViewHolder {
        ImageView guide_video_imageid;
        TextView guide_video_textid;
    }
}
