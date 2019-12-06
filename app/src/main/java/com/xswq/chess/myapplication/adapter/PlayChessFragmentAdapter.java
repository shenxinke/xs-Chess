package com.xswq.chess.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.greendao.entity.PlayingChess;
import com.xswq.chess.myapplication.utils.ContactUrl;
import java.util.List;

public class PlayChessFragmentAdapter extends BaseAdapter {
    private Context context;
    private List<PlayingChess.DataBean.ListBean> listBeans;

    public PlayChessFragmentAdapter(Context context, List<PlayingChess.DataBean.ListBean> listBeans) {
        this.context = context;
        this.listBeans = listBeans;
    }

    @Override
    public int getCount() {
        return listBeans.size();
    }

    @Override
    public Object getItem(int i) {
        return listBeans.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ComViewHolder comHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.playchess_item_layout, null);
            comHolder = new ComViewHolder();
            comHolder.playchess_user1 = convertView.findViewById(R.id.playchess_user1);
            comHolder.playchess_user2 = convertView.findViewById(R.id.playchess_user2);
            comHolder.playchess_black_level = convertView.findViewById(R.id.playchess_black_levels);
            comHolder.playchess_white_level = convertView.findViewById(R.id.playchess_white_level);
            comHolder.playchess_head1 = convertView.findViewById(R.id.playchess_head1);
            comHolder.playchess_head2 = convertView.findViewById(R.id.playchess_head2);
            convertView.setTag(comHolder);
        } else {
            comHolder = (ComViewHolder) convertView.getTag();
        }
        comHolder.playchess_user1.setText(listBeans.get(i).getBlackUsername());
        StringBuffer imageUri1 = new StringBuffer();
        imageUri1.append(ContactUrl.BASE_PATH).append("/gobangteach/gobangPc/").append(listBeans.get(i).getBlackUserhead());
        Glide.with(context)
                .load(imageUri1.toString())
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(comHolder.playchess_head1);
        comHolder.playchess_user2.setText(listBeans.get(i).getWhiteUsername());
        StringBuffer imageUri2 = new StringBuffer();
        imageUri2.append(ContactUrl.BASE_PATH).append("/gobangteach/gobangPc/").append(listBeans.get(i).getBlackUserhead());
        Glide.with(context)
                .load(imageUri2.toString())
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(comHolder.playchess_head2);

        comHolder.playchess_black_level.setText(getStage(""+listBeans.get(i).getBlackUserlevel()));
        comHolder.playchess_white_level.setText(getStage(""+listBeans.get(i).getWhiteUserlevel()));
        return convertView;
    }

    class ComViewHolder {
        TextView playchess_user1;
        TextView playchess_user2;
        TextView playchess_black_level;
        TextView playchess_white_level;
        ImageView playchess_head1;
        ImageView playchess_head2;
    }

    private  String getStage(String level) {
        if("0".equals(level)) {
            level = "";
        }else if (!"".equals(level)){
            if(Integer.parseInt(level)<=25){
                level = 26-Integer.parseInt(level)+"K";
            }else{
                level = Integer.parseInt(level)-25+"D";
            }
        }
        return level;
    }
}
