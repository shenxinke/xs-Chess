package com.xswq.chess.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.activity.achievement.ContentOfAchievementActivity;
import com.xswq.chess.myapplication.bean.ToAchieveBean;
import java.util.List;

public class UnderachievementAdapter extends BaseAdapter {
    private Context context;
    private List<ToAchieveBean.DataBean> dataBeans;
    private UnderachievementGridAdapter underachievementGridAdapter;
    private String token;
    private String userId;
    private ToAchieveBean.DataBean.TypeAndSumCountBean typeAndSumCount;

    public UnderachievementAdapter(Context context, List<ToAchieveBean.DataBean> dataBeans, String token, String userId) {
        this.context = context;
        this.dataBeans = dataBeans;
        this.token = token;
        this.userId = userId;
    }

    @Override
    public int getCount() {
        return dataBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return dataBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ComViewHolder comHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.succrss_system_list_item, null);
            comHolder = new ComViewHolder();
            comHolder.typeName = convertView.findViewById(R.id.type_text);
            comHolder.gridView = convertView.findViewById(R.id.grideView);
            convertView.setTag(comHolder);
        } else {
            comHolder = (ComViewHolder) convertView.getTag();
        }

        String achieveType = dataBeans.get(position).getTypeAndSumCount().getAchieveType();
        int acquireNum = dataBeans.get(position).getTypeAndSumCount().getAcquireNum();
        int typeNum = dataBeans.get(position).getTypeAndSumCount().getTypeNum();
        comHolder.typeName.setText(achieveType + "(" + acquireNum + "/" + typeNum + ")");
        final List<ToAchieveBean.DataBean.DetailednessBean> detailedness = dataBeans.get(position).getDetailedness();
        typeAndSumCount = dataBeans.get(position).getTypeAndSumCount();
        final int num = typeAndSumCount.getNum();
        underachievementGridAdapter = new UnderachievementGridAdapter(context, detailedness, typeAndSumCount);
        comHolder.gridView.setAdapter(underachievementGridAdapter);
        comHolder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ContentOfAchievementActivity.openActivity(context, token, userId
                        ,detailedness.get(position).getDetailednessType()
                        ,detailedness.get(position).getAchieveName()
                        ,detailedness.get(position).getActualName()
                        ,detailedness.get(position).getImgUrl()
                        ,detailedness.get(position).getMaxValue()
                        ,num
                );
            }
        });
        return convertView;
    }

    class ComViewHolder {
        TextView typeName;
        GridView gridView;
    }
}
