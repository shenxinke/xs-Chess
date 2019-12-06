package com.xswq.chess.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.bean.IntegralShoppingMallListBean;
import com.xswq.chess.myapplication.customview.RecyclerViewItemClickListener;
import com.xswq.chess.myapplication.utils.ContactUrl;
import java.util.List;

public class IntegralShoppingMallGridViewAdapter extends RecyclerView.Adapter<IntegralShoppingMallGridViewAdapter.ComViewHolder> {
    private Context context;
    private List<IntegralShoppingMallListBean.DataBean.ListBean> list;
    private LayoutInflater layoutInflater;
    private RecyclerViewItemClickListener itemClickListener;

    public IntegralShoppingMallGridViewAdapter(Context context, List<IntegralShoppingMallListBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ComViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.integral_shopping_mall_item_layout, parent, false);
        ComViewHolder viewHolder = new ComViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ComViewHolder holder, final int position) {
        if (itemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onItemClickListener(position);
                }
            });
        }
        IntegralShoppingMallListBean.DataBean.ListBean listBean = list.get(position);
        if (listBean.isCheck()) {
            holder.imageBackground.setBackgroundResource(R.drawable.shape_integral_shopping_item_true_bg);
        } else {
            holder.imageBackground.setBackgroundResource(R.drawable.shape_integral_shopping_item_bg);
        }
        if (list.get(position).getBelong() == 0) {
            holder.alreadyOwned.setVisibility(View.GONE);
        } else {
            holder.alreadyOwned.setVisibility(View.VISIBLE);
        }
        holder.textMoney.setText(String.valueOf(listBean.getProductValue()));
        String userHeading = ContactUrl.BASE_PATH + "/" + listBean.getProductImg();
        Glide.with(context).load(userHeading).dontAnimate().into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ComViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        ImageView alreadyOwned;
        TextView textMoney;
        ImageView imageBackground;

        public ComViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            textMoney = itemView.findViewById(R.id.text_money);
            imageBackground = itemView.findViewById(R.id.image_background);
            alreadyOwned = itemView.findViewById(R.id.alreadyOwned);
        }
    }

    /**
     * 更新页面数据
     */
    public void updateList(List<IntegralShoppingMallListBean.DataBean.ListBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    /**
     * 设置条目点击事件
     *
     * @param onItemClickListener
     */
    public void setOnItemClickListener(RecyclerViewItemClickListener onItemClickListener) {
        itemClickListener = onItemClickListener;
    }
}
