package com.xswq.chess.myapplication.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.xswq.chess.myapplication.bean.HeadImager;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

public class EditHeadadapter extends RecyclerArrayAdapter<HeadImager> {

    public EditHeadadapter(Context context){
        super(context);

    }
    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        EditHeadViewHolder holder = new EditHeadViewHolder(parent);

        return holder;
    }

}
