package com.xswq.chess.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;

import java.util.List;

public class KnowledgeLevelAdapter extends BaseAdapter {
    private Context context;
    private List<String> data;

    public KnowledgeLevelAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
    }
    public void updata(List<String> data){
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ComViewHolder comHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.knowledge_level_item_layout, null);
            comHolder = new ComViewHolder();
            comHolder.imageView = convertView.findViewById(R.id.level_bg);
            comHolder.level = convertView.findViewById(R.id.text_level);
            convertView.setTag(comHolder);
        } else {
            comHolder = (ComViewHolder) convertView.getTag();
        }

        if (position < 5) {
            comHolder.imageView.setBackgroundResource(R.mipmap.question_level_one);
        } else if (position < 9) {
            comHolder.imageView.setBackgroundResource(R.mipmap.question_level_two);
        } else if (position < 14) {
            comHolder.imageView.setBackgroundResource(R.mipmap.question_level_three);
        } else if (position < 19) {
            comHolder.imageView.setBackgroundResource(R.mipmap.question_level_four);
        } else if (position < 24) {
            comHolder.imageView.setBackgroundResource(R.mipmap.question_level_five);
        } else {
            comHolder.imageView.setBackgroundResource(R.mipmap.question_level_six);
        }
        comHolder.level.setText(data.get(position));
        return convertView;
    }

    class ComViewHolder {
        ImageView imageView;
        TextView level;
    }
}
