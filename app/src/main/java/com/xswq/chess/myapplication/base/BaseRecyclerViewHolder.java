package com.xswq.chess.myapplication.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class BaseRecyclerViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews; //用来存储控件
    private View mConvertView;
    private Context mContext;


    public BaseRecyclerViewHolder(Context context, View itemView) {
        super(itemView);
        this.mContext = context;
        mConvertView = itemView;
        mViews = new SparseArray<View>();
    }


    /**
     * 提供一个获取ViewHolder的方法
     */
    public static BaseRecyclerViewHolder getRecyclerHolder(Context context, ViewGroup parent, int layoutId) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return new BaseRecyclerViewHolder(context, itemView);
    }


    /**
     * 获取控件
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }


    /**
     * 给TextView设置setText方法
     */
    public BaseRecyclerViewHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }


    /**
     * 给ImageView设置setImageResource方法
     */
    public BaseRecyclerViewHolder setImageResource(int viewId, int resId) {
        ImageView view = getView(viewId);
        view.setBackgroundResource(resId);
        return this;
    }

    /**
     * 添加点击事件
     */
    public BaseRecyclerViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }
}
