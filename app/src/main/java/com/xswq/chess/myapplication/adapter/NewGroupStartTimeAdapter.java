package com.xswq.chess.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.bean.NewGroupStartTimeBean;
import com.xswq.chess.myapplication.widget.CustomDatePicker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NewGroupStartTimeAdapter extends BaseAdapter {

    private String[] strRoundsNumber = new String[]{"第一轮:", "第二轮:","第三轮:", "第四轮:", "第五轮:", "第六轮:", "第七轮:", "第八轮:", "第九轮:", "第十轮:", "第十一轮:", "第十二轮:", "第十三轮:"};
    private List<NewGroupStartTimeBean> stringList;
    private Context context;
    private String now;
    private CustomDatePicker startCustomDatePicker;
    private int indext;

    public NewGroupStartTimeAdapter(List<NewGroupStartTimeBean> stringList, Context context) {
        this.stringList = stringList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return stringList.size();
    }

    @Override
    public Object getItem(int position) {
        return stringList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    class ViewHolder {
        TextView textName;
        TextView textTime;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        initDatePicker();

        ViewHolder holder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.new_group_start_time_item_layout, null, false);
            holder.textName = convertView.findViewById(R.id.text_name);
            holder.textTime = convertView.findViewById(R.id.edit_text_time);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textName.setText(strRoundsNumber[position]);
        holder.textTime.setText(stringList.get(position).getTime());
        holder.textTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCustomDatePicker.show(now);
                indext = position;
            }
        });
        return convertView;
    }

    //日历控件调用
    private void initDatePicker() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        now = sdf.format(new Date());

        startCustomDatePicker = new CustomDatePicker(context, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                stringList.get(indext).setTime(time);
                notifyDataSetChanged();
            }
        }, now, "2030-01-01 00:00"); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        startCustomDatePicker.showSpecificTime(true); // 显示时和分
        startCustomDatePicker.setIsLoop(false); // 不允许循环滚动
    }

    public List<NewGroupStartTimeBean>  getList(){
        return stringList;
    }
}
