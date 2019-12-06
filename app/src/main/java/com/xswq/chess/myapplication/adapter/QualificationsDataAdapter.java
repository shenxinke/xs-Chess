package com.xswq.chess.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.bean.PresidentListBean;
import com.xswq.chess.myapplication.utils.Util;

import java.util.List;

public class QualificationsDataAdapter extends BaseAdapter {
    private Context context;
    private List<PresidentListBean.DataBean.ListBean> list;

    public QualificationsDataAdapter(Context context, List<PresidentListBean.DataBean.ListBean> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.qualifications_data_item_layout, null);
            comHolder = new ComViewHolder();

            comHolder.userName = convertView.findViewById(R.id.name);
            comHolder.level = convertView.findViewById(R.id.level);
            comHolder.number = convertView.findViewById(R.id.number);
            comHolder.schedule = convertView.findViewById(R.id.schedule);
            comHolder.topicNumber = convertView.findViewById(R.id.topic_number);


            convertView.setTag(comHolder);
        } else {
            comHolder = (ComViewHolder) convertView.getTag();
        }
        comHolder.userName.setText(Util.signString(list.get(position).getUserName()));
        comHolder.level.setText(Util.getChessLevel(list.get(position).getLevel()));
        comHolder.number.setText(Util.signString(list.get(position).getChessCount()));
        comHolder.schedule.setText(Util.signString(list.get(position).getDayNum()) + "天");
        comHolder.topicNumber.setText(Util.signString(list.get(position).getQuestionNum()));

        return convertView;
    }

    class ComViewHolder {
        TextView userName;
        TextView level;
        TextView number;
        TextView schedule;
        TextView topicNumber;
    }

    public void upData(List<PresidentListBean.DataBean.ListBean> data) {
        this.list = data;
        notifyDataSetChanged();
    }

}
