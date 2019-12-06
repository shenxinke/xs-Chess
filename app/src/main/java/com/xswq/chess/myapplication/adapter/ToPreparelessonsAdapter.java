package com.xswq.chess.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.bean.LookPrepareLessonsBean;
import com.xswq.chess.myapplication.bean.ToPreparelessonBean;
import com.xswq.chess.myapplication.utils.Util;

import java.util.List;

public class ToPreparelessonsAdapter extends BaseAdapter {

    private Context context;
    private List<ToPreparelessonBean> toPreparelessonBeans;
    private List<LookPrepareLessonsBean.DataBean> dataList;

    public ToPreparelessonsAdapter(Context context, List<ToPreparelessonBean> data2Beans, List<LookPrepareLessonsBean.DataBean> dataList) {
        this.context = context;
        this.toPreparelessonBeans = data2Beans;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return toPreparelessonBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return toPreparelessonBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ComViewHolder comHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.to_preparelessons_item_layout, null);
            comHolder = new ComViewHolder();
            comHolder.text = convertView.findViewById(R.id.text);
            comHolder.edit_text_play = convertView.findViewById(R.id.edit_text_play);
            comHolder.edit_text_play11 = convertView.findViewById(R.id.edit_text_play11);
            comHolder.image_view = convertView.findViewById(R.id.image_view);
            convertView.setTag(comHolder);
        } else {
            comHolder = (ComViewHolder) convertView.getTag();
        }
        comHolder.text.setText(toPreparelessonBeans.get(position).getGrouptitle());
        comHolder.edit_text_play.setText(Util.signString(toPreparelessonBeans.get(position).getListArray().get(0).getText()));
        comHolder.edit_text_play11.setText(Util.signString(dataList.get(position).getMyHandouts()));
        Glide.with(context)
                .load(toPreparelessonBeans.get(position).getListArray().get(0).getImgUrl())
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(comHolder.image_view);
        return convertView;
    }

    class ComViewHolder {
        TextView text;
        EditText edit_text_play;
        EditText edit_text_play11;
        ImageView image_view;
    }
}
