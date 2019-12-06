package com.xswq.chess.myapplication.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import java.util.List;

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewHolder> {
    private Context mContext;
    private int mLayoutId;
    private List<T> mData;


    public BaseRecyclerAdapter(Context mContext, int mLayoutId, List<T> mData) {
        this.mContext = mContext;
        this.mLayoutId = mLayoutId;
        this.mData = mData;
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return BaseRecyclerViewHolder.getRecyclerHolder(mContext, parent, mLayoutId);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        convert(holder, position,mData.get(position));

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    /**
     * 对外提供的方法
     */
    public abstract void convert(BaseRecyclerViewHolder holder,int position, T t);

}