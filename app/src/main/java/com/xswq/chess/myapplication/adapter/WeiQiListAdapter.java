package com.xswq.chess.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.bean.WeiQiStoryListBean;

import java.util.List;

public class WeiQiListAdapter extends BaseAdapter {
    private Context context;
    private List<WeiQiStoryListBean.DataBean.ListBean> list;

    public WeiQiListAdapter(Context context, List<WeiQiStoryListBean.DataBean.ListBean> list) {
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
        ComViewHolder comHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.story_list_item, null);
            comHolder = new ComViewHolder();
            comHolder.userName = convertView.findViewById(R.id.story_name_text);
            convertView.setTag(comHolder);
        } else {
            comHolder = (ComViewHolder) convertView.getTag();
        }
        comHolder.userName.setText(list.get(position).getStoryName());
        return convertView;
    }

    class ComViewHolder {
        TextView userName;
    }
}
