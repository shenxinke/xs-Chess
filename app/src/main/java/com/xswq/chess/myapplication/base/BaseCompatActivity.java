package com.xswq.chess.myapplication.base;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import com.xswq.chess.myapplication.activity.navigationentrance.friendlist.AddFriendsScoketActivity;
import com.xswq.chess.myapplication.activity.onlinegame.inform.EntryNoticeActivity;
import com.xswq.chess.myapplication.activity.onlinegame.inform.GetRulesActivity;
import com.xswq.chess.myapplication.activity.onlinegame.inform.MatchByeActivity;
import com.xswq.chess.myapplication.activity.onlinegame.inform.RefuseActivity;
import com.xswq.chess.myapplication.activity.onlinegame.inform.ResultTheMatchActivity;
import com.xswq.chess.myapplication.activity.onlinegame.inform.StudentsSubmitDecisionActivity;
import com.xswq.chess.myapplication.activity.onlinegame.inform.TenMinutesActivity;
import com.xswq.chess.myapplication.activity.start.LoginActivity;
import com.xswq.chess.myapplication.androidtojs.JsWebViewActivity;
import com.xswq.chess.myapplication.application.XSWQApplication;
import com.xswq.chess.myapplication.bean.AddFriendMessageBean;
import com.xswq.chess.myapplication.bean.ScoketMessageBean;
import com.xswq.chess.myapplication.config.Const;
import com.xswq.chess.myapplication.config.RxCode;
import com.xswq.chess.myapplication.dialog.CommonProgressDialog;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.LogUtil;
import com.xswq.chess.myapplication.utils.PreferencesUtils;
import com.xswq.chess.myapplication.utils.SPUtil;
import com.google.gson.Gson;
import com.gyf.immersionbar.ImmersionBar;
import com.zhangke.websocket.SimpleListener;
import com.zhangke.websocket.SocketListener;
import com.zhangke.websocket.response.ErrorResponse;
import org.json.JSONObject;
import java.nio.ByteBuffer;
import butterknife.ButterKnife;
import gorden.rxbus2.RxBus;

public abstract class BaseCompatActivity extends AppCompatActivity {
    public String userId;
    public String orgNo;
    public String token;
    public int userType;
    private CommonProgressDialog onLineDialog;//loading动画
    public String username;
    protected abstract int getLayoutId();
    protected abstract void initView();
    protected abstract void loadData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutId() > 0) {
            setContentView(getLayoutId());
        }
        ButterKnife.bind(this);
        userId = SPUtil.getString("uid", "");
        token = SPUtil.getString("token", "");
        userType = SPUtil.getInt("userType", 0);
        orgNo = SPUtil.getString("orgName", "");
        username = SPUtil.getString("userName", "");
        initView();
        loadData();
        ImmersionBar.with(this).fitsSystemWindows(true).addTag("BaseCompatActivity").init();
    }

    /**
     * 返回登录页面
     */
    public void quiteApp(Context activityContext, String content) {
        Toast.makeText(this, content, Toast.LENGTH_LONG).show();
        PreferencesUtils.putString(XSWQApplication.mContext, "uid", "");
        Intent intent = new Intent(activityContext,LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    //------------------------------------还原字体大小------------------------------
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.fontScale != 1) {
            getResources();
        }
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        //非默认值
        if (res.getConfiguration().fontScale != 1) {
            Configuration newConfig = new Configuration();
            newConfig.setToDefaults();//设置默认
            res.updateConfiguration(newConfig, res.getDisplayMetrics());
        }
        return res;
    }

    //------------------------------------Loading Dialog------------------------------
    public void showProgressDialog(boolean isCancelable) {
        try {
            if (null == onLineDialog) {
                onLineDialog = CommonProgressDialog.showDialog(this,isCancelable);
            }
            if (!this.isFinishing()) {
                try {
                    onLineDialog.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭进度条
     */
    public void dismissProgressDialog() {
        if (null != onLineDialog) {
            onLineDialog.dismiss();
        }
    }

    //------------------------------------socket 接受消息------------------------------

    public SocketListener socketListener = new SimpleListener() {
        @Override
        public void onConnected() {
            LogUtil.e("onConnected");
        }

        @Override
        public void onConnectFailed(Throwable e) {
        }

        @Override
        public void onDisconnect() {
            LogUtil.e("onDisconnect");
        }

        @Override
        public void onSendDataError(ErrorResponse errorResponse) {
            errorResponse.release();
        }

        @Override
        public <T> void onMessage(String message, T data) {
            appendMsgDisplay(message);
        }

        @Override
        public <T> void onMessage(ByteBuffer bytes, T data) {
        }
    };

    private void appendMsgDisplay(String msg) {
        LogUtil.e(msg);
        if (!TextUtils.isEmpty(msg)) {
            try {
                JSONObject mjson = new JSONObject(msg);
                int type = mjson.getInt("type");
                String message;
                Intent intent;
                //1学生提交判棋老师端弹框 2 老师提交判棋结果两个学生端查看判棋结果 3 比赛前十分钟4比赛开始5添加好友
                //6邀请对弈 修改规则 发起邀请  拒绝邀请  7邀请对弈同意 8比赛结束通知 9校间赛比赛通知
                switch (type) {
                    case 1:
                        startActivity(new Intent(this, StudentsSubmitDecisionActivity.class));
                        break;
                    case 2:
                        intent = new Intent(this, ResultTheMatchActivity.class);
                        int result = mjson.getInt("result");
                        intent.putExtra("result", result);
                        startActivity(intent);
                        break;
                    case 3:
                        Gson gson = new Gson();
                        ScoketMessageBean scoketMessageBean = gson.fromJson(msg, ScoketMessageBean.class);
                        if (scoketMessageBean == null) return;
                        String blackUserId = scoketMessageBean.getBlackUserId();
                        String whiteUserId = scoketMessageBean.getWhiteUserId();
                        long roundsStartTime = scoketMessageBean.getRoundsStartTime();
                        long nowTime = System.currentTimeMillis();
                        if (roundsStartTime > nowTime) {
                            if (Const.STR0.equals(blackUserId) || Const.STR0.equals(whiteUserId)) {//轮空
                                return;
                            } else {
                                intent = new Intent(this, TenMinutesActivity.class);
                                intent.putExtra("message", msg);
                                startActivity(intent);
                            }
                        } else {
                            intent = new Intent(this, JsWebViewActivity.class);
                            intent.putExtra("prefix", ContactUrl.MATCHPLAY);
                            startActivity(intent);
                        }
                        break;
                    case 5:
                        message = mjson.getString("message");
                        if (TextUtils.isEmpty(message)) return;
                        intent = new Intent(this, AddFriendsScoketActivity.class);
                        intent.putExtra("message", message);
                        startActivity(intent);
                        break;
                    case 6:
                        message = mjson.getString("message");
                        if (!TextUtils.isEmpty(message)) {
                            Gson newGson = new Gson();
                            AddFriendMessageBean addFriendMessageBean = newGson.fromJson(message, AddFriendMessageBean.class);
                            if (addFriendMessageBean != null) {
                                String messageType = addFriendMessageBean.getMessageType();
                                String name;
                                if (userId.equals(addFriendMessageBean.getBeInvitedUser())) {
                                    name = addFriendMessageBean.getInviteName();
                                } else {
                                    name = addFriendMessageBean.getBeInvitedName();
                                }
                                if (Const.STR4.equals(messageType)) {
                                    intent = new Intent(this, RefuseActivity.class);
                                    intent.putExtra("name", name);
                                } else {
                                    intent = new Intent(this, GetRulesActivity.class);
                                    intent.putExtra("message", message);
                                }
                                startActivity(intent);
                            }
                        }
                        break;
                    case 4:
                        String nullUserId = mjson.getString("nullUserId");
                        if (userId.equals(nullUserId)) {
                            //当前比赛轮空
                            startActivity(new Intent(this, MatchByeActivity.class));
                        } else {
                            intent = new Intent(this, JsWebViewActivity.class);
                            intent.putExtra("prefix", ContactUrl.MATCHPLAY);
                            startActivity(intent);
                        }
                        RxBus.get().send(RxCode.CODE_FINISH_INFORM_MESSAGE);
                        break;
                    case 7:
                        intent = new Intent(this, JsWebViewActivity.class);
                        intent.putExtra("prefix", ContactUrl.PLAY);
                        startActivity(intent);
                        RxBus.get().send(RxCode.CODE_FINISH_INFORM_MESSAGE);
                        break;
                    case 8:
                        String matchNames = mjson.getString("matchName");
                        intent = new Intent(this, EntryNoticeActivity.class);
                        intent.putExtra("matchName", matchNames);
                        startActivity(intent);
                        break;
                    case 9:
                        String schoolName = mjson.getString("schoolName");
                        int state = mjson.getInt("state");
                        intent = new Intent(this, EntryNoticeActivity.class);
                        intent.putExtra("schoolName", schoolName);
                        intent.putExtra("state", state);
                        if (state == 2 || state == 3) {
                            String matchName = mjson.getString("matchName");
                            intent.putExtra("matchName", matchName);
                        }
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}



