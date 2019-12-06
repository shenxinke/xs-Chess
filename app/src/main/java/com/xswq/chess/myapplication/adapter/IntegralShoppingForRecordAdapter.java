package com.xswq.chess.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.bean.IntegralShoppingForRecordBean;
import com.xswq.chess.myapplication.utils.DateUtil;
import java.util.List;

public class IntegralShoppingForRecordAdapter extends BaseAdapter {

    private Context context;
    private List<IntegralShoppingForRecordBean.DataBean.ListBean> list;

    public IntegralShoppingForRecordAdapter(Context context, List<IntegralShoppingForRecordBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    public void upListDate(List<IntegralShoppingForRecordBean.DataBean.ListBean> data) {
        if (list != null && data.size() > 0) {
            this.list = data;
            notifyDataSetChanged();
        }
        notifyDataSetChanged();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ComViewHolder comHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.integral_shopping_record_item_layout, null);
            comHolder = new ComViewHolder();
            comHolder.textName = convertView.findViewById(R.id.text_name);
            comHolder.textTime = convertView.findViewById(R.id.text_time);
            convertView.setTag(comHolder);
        } else {
            comHolder = (ComViewHolder) convertView.getTag();
        }
        comHolder.textName.setText(list.get(position).getProductName());
        long createTime = list.get(position).getCreateTime();
        String dateToString = DateUtil.getDateToString(createTime, "yyyy-MM-dd HH:mm");
        comHolder.textTime.setText(dateToString);
        return convertView;
    }

    class ComViewHolder {
        TextView textName;
        TextView textTime;
    }
}
