package com.xswq.chess.myapplication.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.greendao.entity.Base;
import java.util.List;

public class SpinnerAdapter extends BaseAdapter {


    private List<Base> mList;
    private Context mContext;
    private int type;

    public SpinnerAdapter(Context pContext, List<Base> pList, int type) {
        this.mContext = pContext;
        this.mList = pList;
        this.type = type;
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    class ViewHolder{
        TextView perfect_teacher_name;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if(convertView==null){
            convertView = LayoutInflater.from(mContext)
                    .inflate( R.layout.spinner_item_layout, null, false);
            holder.perfect_teacher_name = convertView.findViewById(R.id.perfect_teacher_name);
            holder.perfect_teacher_name.setGravity(Gravity.CENTER);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        //设置holder 1老师 2班级
        if(type == 1){
            holder.perfect_teacher_name.setText(mList.get(position).getUsername());
        } else {
            holder.perfect_teacher_name.setText(mList.get(position).getClassName());
        }
        return convertView;
    }

}
