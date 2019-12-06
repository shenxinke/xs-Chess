package com.xswq.chess.myapplication.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.activity.main.LookPrepareLessonsActivity;
import com.xswq.chess.myapplication.activity.main.ToPrepareLessonsActivity;
import com.xswq.chess.myapplication.bean.ThemeListBean;
import com.xswq.chess.myapplication.utils.Util;

import java.util.List;

public class PracticalLessonsAdapter extends BaseAdapter {
    private Context context;
    private List<ThemeListBean.DataBean.ListBean> list;
    private String userId;
    private String touken;
    private int prepareLessinStage = 2;
    private int userType;

    public PracticalLessonsAdapter(Context context, List<ThemeListBean.DataBean.ListBean> list, String userId, String touken, int userType) {
        this.context = context;
        this.list = list;
        this.userId = userId;
        this.touken = touken;
        this.userType = userType;
    }

    public void upListDate(List<ThemeListBean.DataBean.ListBean> data) {
        if (list != null) {
            this.list = data;
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ComViewHolder comHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.practical_lessons_item, null);
            comHolder = new ComViewHolder();
            comHolder.userName = convertView.findViewById(R.id.practical_name);
            comHolder.butStart = convertView.findViewById(R.id.start_theme_but);
            comHolder.butLook = convertView.findViewById(R.id.look_theme_but);

            convertView.setTag(comHolder);
        } else {
            comHolder = (ComViewHolder) convertView.getTag();
        }
        if (TextUtils.isEmpty(list.get(position).getId())) {
            comHolder.butStart.setVisibility(View.GONE);
            comHolder.butLook.setVisibility(View.VISIBLE);
        } else {
            comHolder.butLook.setVisibility(View.VISIBLE);
            comHolder.butStart.setVisibility(View.GONE);
        }
        String itemStr = list.get(position).getHeadLine() + ":" + list.get(position).getParaKey();
        comHolder.userName.setText(itemStr);

        comHolder.butStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Util.isClickable()) {
                    return;
                }
                LookPrepareLessonsActivity.openActivity(context, list.get(position).getId(), touken, userId, prepareLessinStage);
            }
        });
        comHolder.butLook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Util.isClickable()) {
                    return;
                }
                ToPrepareLessonsActivity.openActivity(context, list.get(position).getHeadLine(), prepareLessinStage, list.get(position).getId());
            }
        });
        return convertView;
    }

    class ComViewHolder {
        TextView userName;
        Button butStart;
        Button butLook;
    }
}
