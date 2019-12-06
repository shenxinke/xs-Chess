package com.xswq.chess.myapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.activity.main.CreateSchoolActivity;
import com.xswq.chess.myapplication.bean.SchoolManagementBean;
import com.xswq.chess.myapplication.utils.JumpIntent;
import com.xswq.chess.myapplication.utils.Util;

import java.util.List;

public class SchoolManagementAdapter extends BaseAdapter {
    private Context context;
    private List<SchoolManagementBean.DataBeanX.DataBean.ListBean> schoolManagementList;
    private int dealerRank;

    public SchoolManagementAdapter(Context context, List<SchoolManagementBean.DataBeanX.DataBean.ListBean> schoolManagementList, int dealerRank) {
        this.context = context;
        this.schoolManagementList = schoolManagementList;
        this.dealerRank = dealerRank;
    }

    @Override
    public int getCount() {
        return schoolManagementList.size();
    }

    @Override
    public Object getItem(int position) {
        return schoolManagementList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ComViewHolder comHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.school_management_adapter_layout, null);
            comHolder = new ComViewHolder();
            comHolder.schoolName = convertView.findViewById(R.id.school_name);
            comHolder.teacherNumber = convertView.findViewById(R.id.teacher_number);
            comHolder.classNumber = convertView.findViewById(R.id.class_number);
            comHolder.studentNumber = convertView.findViewById(R.id.student_number);
            comHolder.schedule = convertView.findViewById(R.id.schedule);
            comHolder.butAlter = convertView.findViewById(R.id.but_alter);
            convertView.setTag(comHolder);
        } else {
            comHolder = (ComViewHolder) convertView.getTag();
        }
        comHolder.schoolName.setText(Util.signString(schoolManagementList.get(position).getCustomerName()));
        comHolder.teacherNumber.setText(Util.signString(schoolManagementList.get(position).getTeachNum()));
        comHolder.classNumber.setText(Util.signString(schoolManagementList.get(position).getClassNum()));
        comHolder.studentNumber.setText(Util.signString(schoolManagementList.get(position).getStuNum()));
        if (dealerRank == 1 || dealerRank == 2) {
            comHolder.butAlter.setVisibility(View.VISIBLE);
        } else {
            comHolder.butAlter.setVisibility(View.GONE);
        }
        comHolder.butAlter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpIntent.jump((Activity) context, CreateSchoolActivity.class, schoolManagementList.get(position).getID());
            }
        });
        return convertView;
    }

    class ComViewHolder {
        TextView schoolName;
        TextView teacherNumber;
        TextView classNumber;
        TextView studentNumber;
        TextView schedule;
        Button butAlter;
    }

    public void upListDate(List<SchoolManagementBean.DataBeanX.DataBean.ListBean> data) {
        if (schoolManagementList != null) {
            this.schoolManagementList = data;
            notifyDataSetChanged();
        }
    }

}
