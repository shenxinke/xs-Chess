package com.xswq.chess.myapplication.adapter;

import android.annotation.SuppressLint;
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
import com.xswq.chess.myapplication.bean.ToAchieveBean;
import com.xswq.chess.myapplication.customview.MyProgressBar;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.Util;

import java.util.List;

public class UnderachievementGridAdapter extends BaseAdapter {
    private Context context;
    private List<ToAchieveBean.DataBean.DetailednessBean> list;
    private ToAchieveBean.DataBean.TypeAndSumCountBean typeAndSumCount;

    public UnderachievementGridAdapter(Context context, List<ToAchieveBean.DataBean.DetailednessBean> list, ToAchieveBean.DataBean.TypeAndSumCountBean typeAndSumCount) {
        this.context = context;
        this.list = list;
        this.typeAndSumCount = typeAndSumCount;
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

    @SuppressLint("ResourceAsColor")
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
                comHolder.myProgressBar = convertView.findViewById(R.id.my_progress_bar);
                convertView.setTag(comHolder);
            } else {
                comHolder = (ComViewHolder) convertView.getTag();
            }
            comHolder.textName.setText(list.get(position).getAchieveName());
            comHolder.textTime.setTextSize(7);
            comHolder.textTime.setTextColor(R.color.black);
            switch (list.get(position).getDetailednessType()) {
                case 1:
                    comHolder.textTime.setText("登录" + typeAndSumCount.getNum() + "/" + list.get(position).getMaxValue());
                    break;
                case 2:
                    comHolder.textTime.setText("题库" + typeAndSumCount.getNum() + "/" + list.get(position).getMaxValue());
                    break;
                case 3:
                    String getNum = Util.getChessLevel(typeAndSumCount.getNum());
                    String getMaxValue = Util.getChessLevel(list.get(position).getMaxValue());
                    comHolder.textTime.setText("等级" + getNum + "/" + getMaxValue);
                    break;
                case 4:
                    comHolder.textTime.setText("游戏" + typeAndSumCount.getNum() + "/" + list.get(position).getMaxValue());
                    break;
                case 5:
                    comHolder.textTime.setText("对弈" + typeAndSumCount.getNum() + "/" + list.get(position).getMaxValue());
                    break;
                default:
                    break;
            }
            comHolder.myProgressBar.setVisibility(View.VISIBLE);
            if (typeAndSumCount.getNum() <= 5 && list.get(position).getMaxValue() >= 90 && list.get(position).getMaxValue() <= 180 ) {
                comHolder.myProgressBar.setTotalAndCurrentCount(list.get(position).getMaxValue(), typeAndSumCount.getNum() * 5);
            } else if(typeAndSumCount.getNum() <= 10 && list.get(position).getMaxValue() >= 180 ){
                comHolder.myProgressBar.setTotalAndCurrentCount(list.get(position).getMaxValue(), typeAndSumCount.getNum() * 5);
            }else {
                comHolder.myProgressBar.setTotalAndCurrentCount(list.get(position).getMaxValue(), typeAndSumCount.getNum());
            }
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
        MyProgressBar myProgressBar;
    }
}
