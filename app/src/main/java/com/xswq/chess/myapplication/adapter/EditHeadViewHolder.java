package com.xswq.chess.myapplication.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.bean.HeadImager;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

public class EditHeadViewHolder extends BaseViewHolder<HeadImager>  {

    public static final String PATH_HEAD = "file:///android_asset/img/heading/";
    public ImageView headings;
    public  EditHeadViewHolder (ViewGroup parent){
        super(parent,R.layout.personal_head_item_layout);
        headings = $(R.id.headings);

    }

    @Override
    public void setData(HeadImager data) {
        super.setData(data);

        if(data.isSelected()){
            headings.setBackgroundResource(R.drawable.head_shange_bround_selector);
        }else{
            headings.setBackgroundResource(R.color.transparent);
        }
        Glide.with(getContext())
                .load(PATH_HEAD+data.getImageHeading())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(headings);

    }


}
