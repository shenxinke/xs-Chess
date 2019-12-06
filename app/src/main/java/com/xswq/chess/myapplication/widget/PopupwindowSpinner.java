package com.xswq.chess.myapplication.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;

import java.util.List;

public class PopupwindowSpinner <T>extends PopupWindow {

        private LayoutInflater inflater;
        private ListView mListView;
        private List<String> list;
        private MyAdapter  mAdapter;

        public PopupwindowSpinner(Context context, List<String> list, AdapterView.OnItemClickListener clickListener) {
            super(context);
            inflater= LayoutInflater.from(context);
            this.list=list;
            init(clickListener);
        }

        private void init(AdapterView.OnItemClickListener clickListener){
            View view = inflater.inflate(R.layout.wai_spiner_window_layout, null);
            setContentView(view);
            setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
            setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
            setFocusable(true);
            ColorDrawable dw = new ColorDrawable(0x00);
            setBackgroundDrawable(dw);
            mListView = view.findViewById(R.id.listview);
            mListView.setAdapter(mAdapter=new MyAdapter());
            mListView.setOnItemClickListener(clickListener);
        }

        private class MyAdapter extends BaseAdapter {
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
                ViewHolder holder=null;
                if(convertView==null){
                    holder=new ViewHolder();
                   convertView=inflater.inflate(R.layout.wai_spiner_item_layout, null);
                   holder.tvName= convertView.findViewById(R.id.tv_name);
                    convertView.setTag(holder);
                }else{
                    holder=(ViewHolder) convertView.getTag();
                }
                holder.tvName.setText(getItem(position).toString());
                return convertView;
            }
        }

        private class ViewHolder{
            private TextView tvName;
        }
}

