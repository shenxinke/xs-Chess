package com.xswq.chess.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.xswq.chess.myapplication.R;

import java.util.List;

public class LevelUpOrDownAdapter extends BaseAdapter {

    private Context context;
    private List<Integer> mList;

    public LevelUpOrDownAdapter(List<Integer> integersList, Context mContext) {

        this.context = mContext;
        this.mList = integersList;

    }

    @Override
    public int getCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        mList.get(position);
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(R.layout.levelupdown_item_layout, null);
            holder.iv = convertView.findViewById(R.id.updown_image);
            convertView.setTag(holder);
        } else {

            holder = (Holder) convertView.getTag();
        }
        holder.iv.setBackgroundResource(mList.get(position));
        return convertView;
    }

    static class Holder {

        ImageView iv;

    }
}
