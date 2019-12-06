package com.xswq.chess.myapplication.activity.navigationentrance.friendlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.activity.navigationentrance.MessagerListActivity;
import com.xswq.chess.myapplication.base.BaseActivity;
import com.xswq.chess.myapplication.bean.AddFriendMessageBean;
import com.xswq.chess.myapplication.config.Const;
import com.xswq.chess.myapplication.config.RxCode;
import com.google.gson.Gson;

import gorden.rxbus2.RxBus;
import gorden.rxbus2.ThreadMode;

/**
 * 添加好友
 */
public class AddFriendsScoketActivity extends BaseActivity {

    private String messageType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friends_scoket_layout);
        getWindow().getDecorView().setBackgroundResource(R.color.half);
        RxBus.get().register(this);
        TextView textMassage = findViewById(R.id.text_massage);
        Button butConfirm = findViewById(R.id.button_confirm);
        Button butCancel = findViewById(R.id.button_cancel);

        Intent intent = getIntent();
        String message = intent.getStringExtra("message");
        if (!TextUtils.isEmpty(message)) {
            Gson gson = new Gson();
            AddFriendMessageBean addFriendMessageBean = gson.fromJson(message, AddFriendMessageBean.class);
            if (addFriendMessageBean != null) {
                String inviteName = addFriendMessageBean.getInviteName();
                String beInvitedName = addFriendMessageBean.getBeInvitedName();
                messageType = addFriendMessageBean.getMessageType();
                String strMassage;
                if (Const.STR1.equals(messageType)) {
                    strMassage = "【" + inviteName + "】" + "请求加您为好友";
                } else if (Const.STR2.equals(messageType)) {
                    strMassage = "【" + beInvitedName + "】" + "拒绝了您的好友请求";
                } else {
                    strMassage = "【" + beInvitedName + "】" + "同意了您的好友请求";
                }
                textMassage.setText(strMassage);
            }
        }


        butCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        butConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Const.STR1.equals(messageType)) {
                    startActivity(new Intent(AddFriendsScoketActivity.this, MessagerListActivity.class));
                } else {
                    startActivity(new Intent(AddFriendsScoketActivity.this, FriendsListActivity.class));
                }
                finish();
            }
        });
    }

    @gorden.rxbus2.Subscribe(code = RxCode.CODE_FINISH_INFORM_MESSAGE, threadMode = ThreadMode.MAIN)
    public void upData() {
        finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBus.get().unRegister(this);
    }
}
