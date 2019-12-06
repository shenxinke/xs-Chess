package com.xswq.chess.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.config.Const;

public class SwitchMainListAdapter extends RecyclerView.Adapter<SwitchMainListAdapter.ViewHolder> {
    private Context context;
    private int[] mainImage;
    private String title;
    public OnRecyclerViewClickListener listener;
    public interface OnRecyclerViewClickListener {
        void onItemClickListener(View view);
    }
    public void setItemClickListener(OnRecyclerViewClickListener itemClickListener) {
        listener = itemClickListener;
    }


    public SwitchMainListAdapter(Context context, int[] mainImage,String title) {
        this.context = context;
        this.mainImage = mainImage;
        this.title = title;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (Const.STRING_MAIN_PAGE.equals(title)) {
            view= LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.item_switch_main_page_list, parent, false);
        }else {
             view = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.item_switch_main_find_list, parent, false);
        }
        if(listener != null){
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClickListener(v);
                }
            });
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(this.mainImage[position]);
    }

    @Override
    public int getItemCount() {
        return this.mainImage != null ? this.mainImage.length : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mItemImage;
        public ViewHolder(View itemView) {
            super(itemView);
            mItemImage = itemView.findViewById(R.id.item_image);
        }

        public void setData(int image) {
            this.mItemImage.setBackgroundResource(image);
        }
    }

}
