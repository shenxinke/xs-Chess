package com.xswq.chess.myapplication.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.bean.CoursewareImage;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;


public class CoursewareListAdapter extends RecyclerArrayAdapter<CoursewareImage>{


    private OnRecyclerviewItemClickListener mOnRecyclerviewItemClickListener;

    public CoursewareListAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {

        return new CoursewareListViewHolder(parent,mOnRecyclerviewItemClickListener);
    }

    @Override
    public void OnBindViewHolder(BaseViewHolder holder, int position) {
        super.OnBindViewHolder(holder, position);
        CoursewareListViewHolder mCoursewareListViewHolder = (CoursewareListViewHolder)holder;
        mCoursewareListViewHolder.itemView.setTag(position);
        mCoursewareListViewHolder.itemView.findViewById(R.id.courseware_video).setTag(position);
        mCoursewareListViewHolder.itemView.findViewById(R.id.courseware_exercises).setTag(position);
    }

    public interface OnRecyclerviewItemClickListener {
        void onItemClick(View view,int position);
    }

    public void setOnItemClickListener(OnRecyclerviewItemClickListener listener) {
        this.mOnRecyclerviewItemClickListener = listener;
    }
}
