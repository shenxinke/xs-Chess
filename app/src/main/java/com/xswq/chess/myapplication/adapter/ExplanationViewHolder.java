package com.xswq.chess.myapplication.adapter;


import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.application.XSWQApplication;
import com.xswq.chess.myapplication.bean.ExplanationSnapshot;
import com.xswq.chess.myapplication.utils.Util;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

public class ExplanationViewHolder  extends BaseViewHolder<ExplanationSnapshot>{

    private TextView explanation_index;
    private ImageView thumbnail_url;
    public ExplanationViewHolder(ViewGroup parent) {
        super(parent, R.layout.explanation_adapteritem_layout);

        explanation_index =$(R.id.explanation_index);
        thumbnail_url = $(R.id.thumbnail_url);
    }

    @Override
    public void setData(ExplanationSnapshot data) {
        super.setData(data);
        try {
            explanation_index.setText(data.getIx());
            String snapshotImage = data.getSnapshotImage();
            if(snapshotImage.indexOf("http") != -1){
                Glide.with(XSWQApplication.getmContext())
                        .load(snapshotImage)
                        .asBitmap()
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .into(thumbnail_url);
            }else {
                thumbnail_url.setImageBitmap(Util.stringToBitmap(snapshotImage));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
