package com.xswq.chess.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.xswq.chess.myapplication.R;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.xswq.chess.myapplication.bean.ToAchieveBean;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.DateUtil;

import java.util.List;

public class SuccrssSystemGridAdapter extends BaseAdapter {
    private Context context;
    private List<ToAchieveBean.DataBean.DetailednessBean> list;

    public SuccrssSystemGridAdapter(Context context, List<ToAchieveBean.DataBean.DetailednessBean> list) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        try {
            ComViewHolder comHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.succrss_system_grid_item, null);
                comHolder = new ComViewHolder();
                comHolder.textName = convertView.findViewById(R.id.text_name);
                comHolder.textTime = convertView.findViewById(R.id.text_time);
                comHolder.imageView = convertView.findViewById(R.id.image_view);
                convertView.setTag(comHolder);
            } else {
                comHolder = (ComViewHolder) convertView.getTag();
            }
            comHolder.textName.setText(list.get(position).getAchieveName());
            long createTime = list.get(position).getCreateTime();
            String dateToString = DateUtil.getDateToString(createTime, "yyyy-MM-dd");
            comHolder.textTime.setText(dateToString + "获得");
            String imgUrl = list.get(position).getImgUrl();
            StringBuffer imageUri1 = new StringBuffer();
            imageUri1.append(ContactUrl.BASE_PATH).append("/gobangteach/gobangPc/").append(imgUrl);
            Glide.with(context)
                    .load(imageUri1.toString())
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(comHolder.imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }

    class ComViewHolder {
        TextView textName;
        TextView textTime;
        ImageView imageView;
    }
}


