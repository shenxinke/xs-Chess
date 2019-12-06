package com.xswq.chess.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.activity.onlinegame.SetUpRulesActivity;
import com.xswq.chess.myapplication.bean.ListInstitutionsBean;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.ImageLoader;
import com.xswq.chess.myapplication.utils.Util;

import java.util.List;

public class FriendsListAdapter extends BaseAdapter {
    private Context context;
    private List<ListInstitutionsBean.DataBean.ListBean> comUser;

    public FriendsListAdapter(Context context, List<ListInstitutionsBean.DataBean.ListBean> dataBeans) {
        this.context = context;
        this.comUser = dataBeans;
    }

    @Override
    public int getCount() {
        return comUser.size();
    }

    @Override
    public Object getItem(int position) {
        return comUser.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ComViewHolder comHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.user_list_item, null);
            comHolder = new ComViewHolder();
            comHolder.userName = convertView.findViewById(R.id.user_name_text);
            comHolder.addButton = convertView.findViewById(R.id.add_frends_btn);
            comHolder.headImage = convertView.findViewById(R.id.user_head_image);
            comHolder.userLevel = convertView.findViewById(R.id.user_class);

            convertView.setTag(comHolder);
        } else {
            comHolder = (ComViewHolder) convertView.getTag();
        }
        String chessLevel = Util.getChessLevel(comUser.get(position).getLevel());
        String userName = comUser.get(position).getUserName();
        comHolder.userName.setText(Util.signString(userName));
        comHolder.addButton.setText("邀请对弈");
        comHolder.userLevel.setText(chessLevel);
        String mainHeading = ContactUrl.BASE_PATH + "/gobangteach/gobangPc/" + comUser.get(position).getHeadImg();
        ImageLoader.loadHeadImage(mainHeading,comHolder.headImage);
        comHolder.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = comUser.get(position).getID();
                String userName = comUser.get(position).getUserName();
                int sLevel = comUser.get(position).getLevel();
                Intent intent = new Intent(context, SetUpRulesActivity.class);
                intent.putExtra("userHeading", comUser.get(position).getHeadImg());
                intent.putExtra("userName", userName);
                intent.putExtra("userLevel", sLevel);
                intent.putExtra("userId", String.valueOf(id));
                context.startActivity(intent);
            }
        });
        return convertView;
    }


    class ComViewHolder {
        ImageView headImage;
        TextView userName;
        TextView userLevel;
        Button addButton;
    }

    public void upFriendsList(List<ListInstitutionsBean.DataBean.ListBean> datas) {
        this.comUser = datas;
        notifyDataSetChanged();
    }
}
