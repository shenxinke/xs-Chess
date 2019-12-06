package com.xswq.chess.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.bean.TeacherDataTaskBean;
import com.xswq.chess.myapplication.bean.TeacherEveryDadyTaskBean;
import com.xswq.chess.myapplication.customview.RecyclerViewItemClickListener;

import java.util.List;

public class TeacherEveryDadyTaskAdapter extends RecyclerView.Adapter<TeacherEveryDadyTaskAdapter.MyViewHolder> {

    private Context context;
    private List<TeacherEveryDadyTaskBean.DataBean.ListBean> list;
    private LayoutInflater layoutInflater;
    private RecyclerViewItemClickListener itemClickListener;
    private TeacherDataTaskBean.DataBean data;

    public TeacherEveryDadyTaskAdapter(Context context, List<TeacherEveryDadyTaskBean.DataBean.ListBean> list, TeacherDataTaskBean.DataBean data) {
        this.context = context;
        this.list = list;
        this.data = data;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.teacher_every_dady_task_item_layout, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        if (itemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onItemClickListener(position);
                }
            });
        }
        TeacherEveryDadyTaskBean.DataBean.ListBean listBean = list.get(position);

        if (!TextUtils.isEmpty(listBean.getLevel())) {
            holder.itemFoot.setVisibility(View.VISIBLE);
            holder.textClass.setVisibility(View.VISIBLE);
            holder.textClass.setText(listBean.getLevel());
        } else {
            holder.itemFoot.setVisibility(View.GONE);
            holder.textClass.setVisibility(View.GONE);
        }
        if (listBean.isSelect()) {
            if (listBean.getDayNum() < data.getDayNum()) {
                holder.imageBg.setBackgroundResource(R.mipmap.teacher_daily_mission_head_select_ok);
            } else {
                int achieveState = data.getAchieveState();
                if (achieveState == 1) {
                    holder.imageBg.setBackgroundResource(R.mipmap.teacher_daily_mission_head_select_ok);
                    holder.imageOk.setVisibility(View.GONE);
                } else {
                    holder.imageBg.setBackgroundResource(R.mipmap.teacher_daily_mission_head_select);
                    holder.imageOk.setVisibility(View.GONE);
                }
            }
        } else {
            int dayNum = listBean.getDayNum();
            int dayNum1 = data.getDayNum();
            if (dayNum > dayNum1) {
                holder.imageBg.setBackgroundResource(R.mipmap.teacher_daily_mission_item_bg);
                holder.imageOk.setVisibility(View.GONE);
            } else if (dayNum < dayNum1) {
                holder.imageBg.setBackgroundResource(R.mipmap.teacher_daily_mission_head_over);
                holder.imageOk.setVisibility(View.VISIBLE);
            } else if (dayNum == dayNum1) {
                if (data.getAchieveState() == 1) {
                    holder.imageBg.setBackgroundResource(R.mipmap.teacher_daily_mission_head_over);
                    holder.imageOk.setVisibility(View.VISIBLE);
                } else {
                    holder.imageBg.setBackgroundResource(R.mipmap.teacher_daily_mission_item_bg);
                    holder.imageOk.setVisibility(View.GONE);
                }
            }
        }
        holder.titleText.setText(String.valueOf(listBean.getDayNum()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titleText;
        TextView textClass;
        ImageView imageBg;
        ImageView imageOk;
        ImageView itemFoot;

        public MyViewHolder(View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.title_text);
            textClass = itemView.findViewById(R.id.text_class);
            imageBg = itemView.findViewById(R.id.image_bg);
            imageOk = itemView.findViewById(R.id.image_ok);
            itemFoot = itemView.findViewById(R.id.teacher_daily_mission_item_foot);
        }
    }

    /**
     * 设置条目点击事件
     *
     * @param onItemClickListener
     */
    public void setOnItemClickListener(RecyclerViewItemClickListener onItemClickListener) {
        itemClickListener = onItemClickListener;
    }
}
