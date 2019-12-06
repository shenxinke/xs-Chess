package com.xswq.chess.myapplication.http;

import android.view.View;

public interface OnRecyclerviewItemClickListener {

    enum ViewName {
        ITEM,
        PRACTISE
    }
    void onItemClick(View view,int position);

}
