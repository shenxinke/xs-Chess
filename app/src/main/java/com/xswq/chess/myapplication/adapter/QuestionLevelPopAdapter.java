package com.xswq.chess.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.xswq.chess.myapplication.R;

import java.util.List;

public class QuestionLevelPopAdapter extends BaseAdapter {
    private Context context;
    private List<Integer> integers;


    public QuestionLevelPopAdapter(Context context, List<Integer> integers) {
        this.context = context;
        this.integers = integers;
    }

    @Override
    public int getCount() {
        return integers.size();
    }

    @Override
    public Object getItem(int position) {
        return integers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ComViewHolder comHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.question_level_pop_item_layout, null);
            comHolder = new ComViewHolder();
            comHolder.imageView = convertView.findViewById(R.id.image);

            convertView.setTag(comHolder);
        } else {
            comHolder = (ComViewHolder) convertView.getTag();
        }
        comHolder.imageView.setBackgroundResource(integers.get(position));
        return convertView;
    }

    class ComViewHolder {
        ImageView imageView;
    }
}
