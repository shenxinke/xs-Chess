package com.xswq.chess.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.bean.HistoryTask;
import com.xswq.chess.myapplication.utils.DateUtil;

import java.text.DecimalFormat;
import java.util.List;

public class HistoryTaskAdapter extends BaseAdapter {
    private Context context;
    private List<HistoryTask.DataBean.ListBean> listTask;

    public HistoryTaskAdapter(Context context, List<HistoryTask.DataBean.ListBean> listTask) {
        this.context = context;
        this.listTask = listTask;
    }

    @Override
    public int getCount() {
        return listTask.size();
    }

    @Override
    public Object getItem(int i) {
        return listTask.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ComViewHolder comHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.history_task_item_layout, null);
            comHolder = new ComViewHolder();
            comHolder.task_time_id = convertView.findViewById(R.id.task_time_id);
            comHolder.task_gailv_id = convertView.findViewById(R.id.task_gailv_id);
            comHolder.task_total_id = convertView.findViewById(R.id.task_total_id);
            convertView.setTag(comHolder);
        } else {
            comHolder = (ComViewHolder) convertView.getTag();
        }
        long createtime = listTask.get(i).getCreatetime();
        String dateToString = DateUtil.getDateToString(createtime, "yyyy-MM-dd");
        comHolder.task_time_id.setText(dateToString);

        int complete = listTask.get(i).getComplete();
        int correct = listTask.get(i).getCorrect();
        int totalcount = listTask.get(i).getTotalcount();
        String rightProbability = complete + "/" + totalcount;
        comHolder.task_gailv_id.setText(rightProbability);

        String exercisesNUM;
        float v = (float) correct / (float) complete * 100;
        DecimalFormat df = new DecimalFormat("0.00");
        String format = df.format(v);
        if (format.equals("NaN")) {
            exercisesNUM = "- -";
        } else {
            exercisesNUM = format + "%";
        }
        comHolder.task_total_id.setText(exercisesNUM);
        return convertView;
    }

    class ComViewHolder {
        TextView task_time_id;
        TextView task_gailv_id;
        TextView task_total_id;
    }
}
