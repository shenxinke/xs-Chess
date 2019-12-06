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
import com.xswq.chess.myapplication.activity.navigationentrance.friendlist.AddFriendsActivity;
import com.xswq.chess.myapplication.bean.UserListBean;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.Util;

import java.util.List;

public class UserListAdapter extends BaseAdapter {

    private Context context;
    private List<UserListBean.DataBean.ListBean> comUser;
    private String token;
    private String userId;

    public UserListAdapter(Context context, List<UserListBean.DataBean.ListBean> dataBeans, String token, String userId) {
        this.context = context;
        this.comUser = dataBeans;
        this.token = token;
        this.userId = userId;
    }

    public void upUserList(List<UserListBean.DataBean.ListBean> data) {
        this.comUser = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return comUser == null ? 0 : comUser.size();
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
        String sLevel = Util.getChessLevel(comUser.get(position).getLevel());
        String userName = comUser.get(position).getUserName();
        comHolder.userName.setText(Util.signString(userName));
        comHolder.userLevel.setText(sLevel);
        StringBuffer imageUri1 = new StringBuffer();
        imageUri1.append(ContactUrl.BASE_PATH).append("/gobangteach/gobangPc/").append(comUser.get(position).getHeadImg());
        Glide.with(context)
                .load(imageUri1.toString())
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(comHolder.headImage);
        comHolder.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddFriendsActivity.class);
                intent.putExtra("token", token);
                intent.putExtra("userId", userId);
                intent.putExtra("name", comUser.get(position).getUserName());
                intent.putExtra("level", comUser.get(position).getLevel());
                intent.putExtra("headImage", comUser.get(position).getHeadImg());
                intent.putExtra("friendId", comUser.get(position).getID());
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
}
