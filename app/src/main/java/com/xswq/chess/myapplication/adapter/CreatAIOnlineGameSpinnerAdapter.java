package com.xswq.chess.myapplication.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.xswq.chess.myapplication.R;
import java.util.List;

public class CreatAIOnlineGameSpinnerAdapter extends BaseAdapter {

    private List<String> schoolManagementList;
    private Context mContext;

    public CreatAIOnlineGameSpinnerAdapter(Context pContext, List<String> pList ) {
        this.mContext = pContext;
        this.schoolManagementList = pList;
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


    class ViewHolder{
        TextView perfectPresidentName;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if(convertView==null){
            convertView = LayoutInflater.from(mContext)
                    .inflate( R.layout.spinner_item_layout, null, false);
            holder.perfectPresidentName = convertView.findViewById(R.id.perfect_teacher_name);
            holder.perfectPresidentName.setGravity(Gravity.CENTER);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.perfectPresidentName.setText(schoolManagementList.get(position));
        return convertView;
    }

}
