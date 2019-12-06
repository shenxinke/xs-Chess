package com.xswq.chess.myapplication.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.bean.CoursewareImage;
import com.xswq.chess.myapplication.utils.ToastUtils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

public class CoursewareListViewHolder extends BaseViewHolder<CoursewareImage> implements View.OnClickListener {

    private ImageView courseware_exercises;
    private ImageView courseware_video;
    private ImageView imageBg;
    private ImageView imageLock;
    private ImageView practices;
    private TextView courseware_video_name;
    private CoursewareListAdapter.OnRecyclerviewItemClickListener mRecyclerviewItemClickListener;

    CoursewareListViewHolder(ViewGroup parent, CoursewareListAdapter.OnRecyclerviewItemClickListener mOnRecyclerviewItemClickListener) {
        super(parent, R.layout.coursewarelist_item_layout);
        mRecyclerviewItemClickListener = mOnRecyclerviewItemClickListener;
        courseware_video = $(R.id.courseware_video);
        courseware_exercises = $(R.id.courseware_exercises);
        practices = $(R.id.practices);
        courseware_video_name = $(R.id.courseware_video_name);
        imageBg = $(R.id.image_bg);
        imageLock = $(R.id.image_lock);
        imageLock.setOnClickListener(this);
        imageBg.setOnClickListener(this);
        courseware_video.setOnClickListener(this);
        courseware_exercises.setOnClickListener(this);
    }


    @Override
    public void setData(CoursewareImage data) {
        super.setData(data);
        if (data.getTitle().indexOf("围棋礼仪") > 0 || data.getTitle().indexOf("棋盘棋子") > 0 || data.getTitle().indexOf("胜负计算") > 0) {
            courseware_exercises.setVisibility(View.GONE);
            practices.setVisibility(View.GONE);
            courseware_video_name.setText(data.getTitle());
            courseware_video.setImageResource(data.getVideo());
        } else {
            courseware_exercises.setVisibility(View.VISIBLE);
            practices.setVisibility(View.VISIBLE);
            courseware_video_name.setText(data.getTitle());
            courseware_video.setImageResource(data.getVideo());
            courseware_exercises.setImageResource(data.getPractice());
        }

        if (data.getCanWatch()) {
            imageBg.setVisibility(View.GONE);
            imageLock.setVisibility(View.GONE);
        } else {
            imageBg.setVisibility(View.VISIBLE);
            imageLock.setVisibility(View.VISIBLE);
        }
    }


    private int dip2px(Context context, float dipValue) {
        Resources r = context.getResources();
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dipValue, r.getDisplayMetrics());
    }


    @Override
    public void onClick(View v) {
        if (mRecyclerviewItemClickListener != null) {
            switch (v.getId()) {
                case R.id.courseware_video:
                case R.id.courseware_exercises:
                    int position = (int) v.getTag();
                    mRecyclerviewItemClickListener.onItemClick(v, position);
                    break;
                case R.id.image_bg:
                case R.id.image_lock:
                    ToastUtils.show("课程尚未解锁");
                    break;
                default:
                    break;
            }
        }
    }


}
