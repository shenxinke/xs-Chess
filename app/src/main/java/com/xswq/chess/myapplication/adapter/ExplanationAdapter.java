package com.xswq.chess.myapplication.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.xswq.chess.myapplication.bean.ExplanationSnapshot;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

public class ExplanationAdapter extends RecyclerArrayAdapter<ExplanationSnapshot> {


    public ExplanationAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ExplanationViewHolder(parent);
    }


}
