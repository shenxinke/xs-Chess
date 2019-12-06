package com.xswq.chess.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.bean.SetCourseIsSlectBean;

import java.util.List;

public class SetCourseSyllabusGridAdapter extends RecyclerView.Adapter<SetCourseSyllabusGridAdapter.GridViewHolder> {

    private Context mContext;
    //RecyclerView所需的类
    private List<SetCourseIsSlectBean> allCourseList;

    public SetCourseSyllabusGridAdapter(Context mContext, List<SetCourseIsSlectBean> allCourseList) {
        this.mContext = mContext;
        this.allCourseList = allCourseList;
    }

    //声明自定义的监听接口
    private OnRecyclerItemClickListener monItemClickListener;

    //提供set方法供Activity或Fragment调用
    public void setRecyclerItemClickListener(OnRecyclerItemClickListener listener) {
        monItemClickListener = listener;
    }

    public interface OnRecyclerItemClickListener {
        //RecyclerView的点击事件，将信息回调给view
        void onItemClick(int position, List<SetCourseIsSlectBean> allCourseList);
    }


    @Override
    public SetCourseSyllabusGridAdapter.GridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //转换一个ViewHolder对象，决定了item的样式，参数1.上下文 2.XML布局资源 3.null
        View itemView = View.inflate(mContext, R.layout.item_course_layout, null);
        //创建一个ViewHodler对象
        GridViewHolder gridViewHolder = new GridViewHolder(itemView);
        //把ViewHolder传出去
        return gridViewHolder;
    }

    @Override
    public void onBindViewHolder(SetCourseSyllabusGridAdapter.GridViewHolder holder, int position) {
        //从集合里拿对应的item的数据对象
        String name = allCourseList.get(position).getName();
        //给Holder里面的控件对象设置数据
        holder.setData(name, position);
    }

    @Override
    public int getItemCount() {
        return null != allCourseList ? allCourseList.size() : 0;
    }


    public class GridViewHolder extends RecyclerView.ViewHolder {
        private ImageView itemIcon;
        private TextView itemName;

        public GridViewHolder(View itemView) {
            super(itemView);
            itemIcon = itemView.findViewById(R.id.item_icon);
            itemName = itemView.findViewById(R.id.item_name);

            //将监听传递给自定义接口
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (monItemClickListener != null) {
                        monItemClickListener.onItemClick(getAdapterPosition(), allCourseList);
                    }
                }
            });

        }

        public void setData(String item, int position) {
            itemName.setText("第" + item + "天");
            boolean slect = allCourseList.get(position).isSlect();
            if (slect) {
                itemIcon.setBackgroundResource(R.mipmap.radio_button_checked);
            } else {
                itemIcon.setBackgroundResource(R.mipmap.radio_button_unchecked);
            }
        }
    }

}