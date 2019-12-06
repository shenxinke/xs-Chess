package com.xswq.chess.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.base.BaseBean;
import com.xswq.chess.myapplication.bean.AddFriendMessageBean;
import com.xswq.chess.myapplication.bean.AddFriendsBean;
import com.xswq.chess.myapplication.config.Const;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.DateUtil;
import com.xswq.chess.myapplication.utils.GsonUtil;
import com.xswq.chess.myapplication.utils.ImageLoader;
import com.xswq.chess.myapplication.utils.SPUtil;
import com.xswq.chess.myapplication.utils.ToastUtils;
import com.xswq.chess.myapplication.utils.Util;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

public class AddFriendsAdapter extends BaseAdapter {
    private Context context;
    private List<AddFriendsBean.DataBean> data;
    private String token;
    private String userId;
    private ComViewHolder comHolder;

    public AddFriendsAdapter(Context context, List<AddFriendsBean.DataBean> data, String token, String userId) {
        this.context = context;
        this.data = data;
        this.token = token;
        this.userId = userId;
    }

    public void upDataList(List<AddFriendsBean.DataBean> dataList) {
        this.data = dataList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.add_friends_message_item, null);
            comHolder = new ComViewHolder();
            comHolder.name = convertView.findViewById(R.id.add_friend_name_text);
            comHolder.imageHead = convertView.findViewById(R.id.add_friend_head_image);
            comHolder.timer = convertView.findViewById(R.id.add_friend_time_text);
            comHolder.addMessageStr = convertView.findViewById(R.id.add_message);
            comHolder.butYes = convertView.findViewById(R.id.add_frends_yes);
            comHolder.butNoe = convertView.findViewById(R.id.add_frends_no);
            comHolder.butStatus = convertView.findViewById(R.id.add_frends_status);
            convertView.setTag(comHolder);
        } else {
            comHolder = (ComViewHolder) convertView.getTag();
        }
        final String userHeading = SPUtil.getString("userHeading", "");
        final String username = SPUtil.getString("userName", "");
        final int userLevel = SPUtil.getInt("level", 0);

        long createTime = data.get(position).getCreateTime();
        String dateToString = DateUtil.getDateToString(createTime, "MM/dd HH:mm:ss");
        comHolder.timer.setText(Util.signString(dateToString));
        int state = data.get(position).getState();
        //2拒绝3同意
        switch (state) {
            case 2:
                comHolder.butYes.setVisibility(View.GONE);
                comHolder.butNoe.setVisibility(View.GONE);
                comHolder.butStatus.setText("已拒绝");
                comHolder.butStatus.setVisibility(View.VISIBLE);
                break;
            case 3:
                comHolder.butYes.setVisibility(View.GONE);
                comHolder.butNoe.setVisibility(View.GONE);
                comHolder.butStatus.setText("已同意");
                comHolder.butStatus.setVisibility(View.VISIBLE);
                break;
            default:
                comHolder.butYes.setVisibility(View.VISIBLE);
                comHolder.butNoe.setVisibility(View.VISIBLE);
                comHolder.butStatus.setVisibility(View.GONE);
                break;
        }
        String message = data.get(position).getMessage();
        comHolder.addMessageStr.setText(Util.signString(message));
        final AddFriendMessageBean addFriendMessageBean = new AddFriendMessageBean();
        comHolder.butYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String invitedUser = data.get(position).getInviteUser();
                String inviteHeadImg = data.get(position).getBeInviteHeadImg();
                String invitedName = data.get(position).getBeInvitedName();
                addFriendMessageBean.setBeInviteHeadImg(userHeading);
                addFriendMessageBean.setBeInvitedName(username);
                addFriendMessageBean.setBeInvitedLevel(String.valueOf(userLevel));
                addFriendMessageBean.setBeInvitedUser(userId);
                addFriendMessageBean.setMessageType(Const.STR3);
                addFriendMessageBean.setInviteHeadImg(inviteHeadImg);
                addFriendMessageBean.setInviteName(invitedName);
                addFriendMessageBean.setInviteUser(invitedUser);
                addFriendMessageBean.setInviteLevel("");
                Gson gson = new Gson();
                String gsonString = gson.toJson(addFriendMessageBean);
                friendsSendInvite("3", invitedUser, gsonString);
                comHolder.butStatus.setText("已同意");
                comHolder.butYes.setVisibility(View.GONE);
                comHolder.butNoe.setVisibility(View.GONE);
                comHolder.butStatus.setVisibility(View.VISIBLE);
            }
        });
        comHolder.butNoe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFriendMessageBean.setMessageType(Const.STR2);
                String invitedUser = data.get(position).getInviteUser();
                String inviteHeadImg = data.get(position).getBeInviteHeadImg();
                String invitedName = data.get(position).getBeInvitedName();
                addFriendMessageBean.setBeInviteHeadImg(userHeading);
                addFriendMessageBean.setBeInvitedName(username);
                addFriendMessageBean.setBeInvitedLevel(String.valueOf(userLevel));
                addFriendMessageBean.setBeInvitedUser(userId);
                addFriendMessageBean.setInviteHeadImg(inviteHeadImg);
                addFriendMessageBean.setInviteName(invitedName);
                addFriendMessageBean.setInviteUser(invitedUser);
                addFriendMessageBean.setInviteLevel("");
                Gson gson = new Gson();
                String gsonString = gson.toJson(addFriendMessageBean);
                friendsSendInvite("2", invitedUser, gsonString);
                comHolder.butYes.setVisibility(View.GONE);
                comHolder.butNoe.setVisibility(View.GONE);
                comHolder.butStatus.setText("已拒绝");
                comHolder.butStatus.setVisibility(View.VISIBLE);
            }
        });

        String beInviteHeadImg = data.get(position).getInviteHeadImg();
        String imageUri1= ContactUrl.BASE_PATH +"/gobangteach/gobangPc/"+beInviteHeadImg;
        ImageLoader.loadHeadImage(imageUri1, comHolder.imageHead);
        comHolder.name.setText(Util.signString(data.get(position).getInviteName()));
        return convertView;
    }

    class ComViewHolder {
        TextView name;
        TextView timer;
        TextView addMessageStr;
        ImageView imageHead;
        Button butYes;
        Button butNoe;
        Button butStatus;
    }

    private void friendsSendInvite(final String state, String invitedUser, String gsonString) {
        try {
            PostFormBuilder post = OkHttpUtils.post();
            post.url(ContactUrl.POST_FRIENDS_SEND_INVITE_PATH)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .addParams("state", state)
                    .addParams("beInvitedUser", userId)
                    .addParams("inviteUser", invitedUser)
                    .addParams("message", gsonString)
                    .build().execute(new StringCallback() {

                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    GsonUtil.gsonToBean(response, BaseBean.class, context);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
