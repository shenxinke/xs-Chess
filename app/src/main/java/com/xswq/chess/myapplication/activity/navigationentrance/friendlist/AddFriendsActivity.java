package com.xswq.chess.myapplication.activity.navigationentrance.friendlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.base.BaseActivity;
import com.xswq.chess.myapplication.base.BaseBean;
import com.xswq.chess.myapplication.bean.AddFriendMessageBean;
import com.xswq.chess.myapplication.config.Const;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.GsonUtil;
import com.xswq.chess.myapplication.utils.ImageLoader;
import com.xswq.chess.myapplication.utils.SPUtil;
import com.xswq.chess.myapplication.utils.ToastUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class AddFriendsActivity extends BaseActivity {

    private String token;
    private String userId;
    private String friendId;
    private String level;
    private String name;
    private String headImage;
    private String userHeading;
    private String username;
    private int userLevel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_friends_activity);
        getWindow().getDecorView().setBackgroundResource(R.color.half);
        TextView textView = findViewById(R.id.name);
        ImageView imageView = findViewById(R.id.add_head_image);
        ImageView imageOut = findViewById(R.id.image_out);
        ImageView sendRequest = findViewById(R.id.send_request);
        final EditText requestText = findViewById(R.id.request_text);
        requestText.setFocusable(true);
        userHeading = SPUtil.getString("userHeading", "");
        username = SPUtil.getString("userName", "");
        userLevel = SPUtil.getInt("level", 0);

        Intent intent = this.getIntent();
        name = intent.getStringExtra("name");
        headImage = intent.getStringExtra("headImage");
        token = intent.getStringExtra("token");
        userId = intent.getStringExtra("userId");
        friendId = intent.getStringExtra("friendId");
        this.level = intent.getStringExtra("level");

        textView.setText(name);
        String imageUri1 = ContactUrl.BASE_PATH+"/gobangteach/gobangPc/"+headImage;
        ImageLoader.loadHeadImage(imageUri1,imageView);
        imageOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        sendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trim = requestText.getText().toString().trim();
                AddFriendMessageBean addFriendMessageBean = new AddFriendMessageBean();
                addFriendMessageBean.setBeInviteHeadImg(headImage);
                addFriendMessageBean.setBeInvitedName(name);
                addFriendMessageBean.setBeInvitedLevel(level);
                addFriendMessageBean.setBeInvitedUser(friendId);
                addFriendMessageBean.setMessageType(Const.STR1);
                addFriendMessageBean.setInviteHeadImg(userHeading);
                addFriendMessageBean.setInviteName(username);
                addFriendMessageBean.setInviteUser(userId);
                addFriendMessageBean.setInviteLevel(String.valueOf(userLevel));
                Gson gson = new Gson();
                String gsonString = gson.toJson(addFriendMessageBean);
                if (TextUtils.isEmpty(trim)) {
                    trim = "您好!我想添加您为好友!";
                }
                friendsSendInvite(trim, gsonString);
            }
        });
    }

    private void friendsSendInvite(String trim, String gsonString) {
        try {
            PostFormBuilder post = OkHttpUtils.post();
            post.url(ContactUrl.POST_FRIENDS_SEND_INVITE_PATH)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .addParams("inviteUser", userId)
                    .addParams("beInvitedUser", friendId)
                    .addParams("state", Const.STR1)
                    .addParams("message", gsonString)
                    .addParams("content", trim)
                    .build().execute(new StringCallback() {

                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    BaseBean baseBean = GsonUtil.gsonToBean(response, BaseBean.class, AddFriendsActivity.this);
                    if (baseBean != null) {
                        ToastUtils.show("请求已发送");
                        finish();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
