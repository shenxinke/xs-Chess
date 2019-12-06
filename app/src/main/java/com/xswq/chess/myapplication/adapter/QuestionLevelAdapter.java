package com.xswq.chess.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.bean.QuestionLevelStringBean;

import java.util.List;

public class QuestionLevelAdapter extends BaseAdapter{
    private Context context;
    private List<QuestionLevelStringBean> questionLevelList;

    public QuestionLevelAdapter(Context context, List<QuestionLevelStringBean> questionLevelList) {
        this.context = context;
        this.questionLevelList = questionLevelList;
    }

    @Override
    public int getCount() {
        return questionLevelList.size();
    }

    @Override
    public Object getItem(int position) {
        return questionLevelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ComViewHolder comHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_question_level_item_layout, null);
            comHolder = new ComViewHolder();
            comHolder.imageView = convertView.findViewById(R.id.level_bg);
            comHolder.level = convertView.findViewById(R.id.text_level);
            convertView.setTag(comHolder);
        } else {
            comHolder = (ComViewHolder) convertView.getTag();
        }
        comHolder.imageView.setBackgroundResource(questionLevelList.get(position).getmImage());
        comHolder.level.setText(questionLevelList.get(position).getmLevel());
        return convertView;
    }

    class ComViewHolder {
        ImageView imageView;
        TextView level;
    }
}
