package com.xswq.chess.myapplication.activity.onlinegame.inform;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.androidtojs.JsWebViewActivity;
import com.xswq.chess.myapplication.base.BaseActivity;
import com.xswq.chess.myapplication.base.BaseBean;
import com.xswq.chess.myapplication.bean.SetUpRulesBean;
import com.xswq.chess.myapplication.config.Const;
import com.xswq.chess.myapplication.config.RxCode;
import com.xswq.chess.myapplication.customview.StringScrollPicker;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.GsonUtil;
import com.xswq.chess.myapplication.utils.ImageLoader;
import com.xswq.chess.myapplication.utils.LogUtil;
import com.xswq.chess.myapplication.utils.ToastUtils;
import com.xswq.chess.myapplication.utils.Util;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gorden.rxbus2.RxBus;
import gorden.rxbus2.ThreadMode;
import okhttp3.Call;

/***
 * 接收到邀请对弈通知
 */
public class GetRulesActivity extends BaseActivity implements View.OnClickListener {
    private PopupWindow popupWindow;
    private String[] rangChild = new String[]{"分先", "让4子", "让9子"};
    private String[] way = new String[]{"19 × 19", "9 × 9"};
    private String[] time = new String[]{"20 分钟"};
    private String[] second = new String[]{"15 秒"};
    private String[] numberTimeout = new String[]{"3 次"};
    private TextView presidentText;
    private TextView presidentText2;
    private TextView presidentText3;
    private TextView presidentText4;
    private TextView presidentText5;
    private String inviteUser;
    private String beInvitedUser;
    private String inviteLevel;
    private String beInvitedLevel;
    private String inviteName;
    private String beInvitedName;
    private String inviteHeadImg;
    private String beInviteHeadImg;
    private TextView textLevel;
    private TextView userName;
    private ImageView imageHead;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_rules_layout);
        RxBus.get().register(this);
        initView();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        String message = intent.getStringExtra("message");
        if (!TextUtils.isEmpty(message)) {
            Gson gson = new Gson();
            SetUpRulesBean setUpRulesBean = gson.fromJson(message, SetUpRulesBean.class);
            inviteUser = setUpRulesBean.getInviteUser();
            beInvitedUser = setUpRulesBean.getBeInvitedUser();
            inviteLevel = setUpRulesBean.getInviteLevel();
            beInvitedLevel = setUpRulesBean.getBeInvitedLevel();
            inviteName = setUpRulesBean.getInviteName();
            beInvitedName = setUpRulesBean.getBeInvitedName();
            inviteHeadImg = setUpRulesBean.getInviteHeadImg();
            beInviteHeadImg = setUpRulesBean.getBeInviteHeadImg();
            if (userId.equals(inviteUser)) {
                String headImage = ContactUrl.BASE_PATH + "/gobangteach/gobangPc/" + beInviteHeadImg;
                ImageLoader.loadHeadImage(headImage,imageHead);
                String level = Util.getChessLevel(Integer.parseInt(Util.zeroString(beInvitedLevel)));
                textLevel.setText(level);
                userName.setText(Util.signString(beInvitedName));
            } else {
                String headImage = ContactUrl.BASE_PATH + "/gobangteach/gobangPc/" + inviteHeadImg;
                ImageLoader.loadHeadImage(headImage,imageHead);
                String level = Util.getChessLevel(Integer.parseInt(Util.zeroString(inviteLevel)));
                textLevel.setText(level);
                userName.setText(Util.signString(inviteName));
            }
            String ruletype = setUpRulesBean.getRuletype();
            String size = setUpRulesBean.getSize();
            switch (ruletype) {
                case Const.STR1:
                    presidentText.setText("分先");
                    break;
                case Const.STR2:
                    presidentText.setText("让4子");
                    break;
                case Const.STR3:
                    presidentText.setText("让9子");
                    break;
                default:
                    break;
            }
            if (Const.STR19.equals(size)) {
                presidentText5.setText(way[0]);
            } else {
                presidentText5.setText(way[1]);
            }
        }
    }

    private void initView() {
        getWindow().getDecorView().setBackgroundResource(R.color.half);
        presidentText = findViewById(R.id.president_text);
        presidentText.setOnClickListener(this);
        findViewById(R.id.image).setOnClickListener(this);
        presidentText2 = findViewById(R.id.president_text2);
        presidentText2.setOnClickListener(this);
        findViewById(R.id.image2).setOnClickListener(this);
        presidentText3 = findViewById(R.id.president_text3);
        presidentText3.setOnClickListener(this);
        findViewById(R.id.image3).setOnClickListener(this);
        presidentText4 = findViewById(R.id.president_text4);
        presidentText4.setOnClickListener(this);
        findViewById(R.id.image4).setOnClickListener(this);
        presidentText5 = findViewById(R.id.president_text5);
        presidentText5.setOnClickListener(this);
        findViewById(R.id.image5).setOnClickListener(this);

        findViewById(R.id.button_yes).setOnClickListener(this);
        findViewById(R.id.button_setup_rules).setOnClickListener(this);
        findViewById(R.id.button_refuse).setOnClickListener(this);

        textLevel = findViewById(R.id.text_level);
        userName = findViewById(R.id.user_name);
        imageHead = findViewById(R.id.image_head);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_back:
                finish();
                break;
            case R.id.president_text:
            case R.id.image:
                if (Util.isClickable()) return;
                showPopupWindow(1, rangChild);
                break;
            case R.id.president_text2:
            case R.id.image2:
                if (Util.isClickable()) return;
                showPopupWindow(2, time);
                break;
            case R.id.president_text3:
            case R.id.image3:
                if (Util.isClickable()) return;
                showPopupWindow(3, second);
                break;
            case R.id.president_text4:
            case R.id.image4:
                if (Util.isClickable()) return;
                showPopupWindow(4, numberTimeout);
                break;
            case R.id.president_text5:
            case R.id.image5:
                if (Util.isClickable()) return;
                String texts = presidentText.getText().toString().trim();
                if ("分先".equals(texts)) {
                    showPopupWindow(5, way);
                }
                break;
            case R.id.button_yes:
                if (Util.isClickable()) return;
                String size;
                String ruletype;
                String blackuserid;
                String whiteuserid;
                String friendsId;
                String string = presidentText5.getText().toString();
                String president = presidentText.getText().toString();
                if (way[0].equals(string)) {
                    size = "19";
                } else {
                    size = "9";
                }
                if (userId.equals(inviteUser)) {
                    friendsId = beInvitedUser;
                } else {
                    friendsId = inviteUser;
                }

                if (president.equals(rangChild[0])) {
                    ruletype = Const.STR1;
                    whiteuserid = inviteUser;
                    blackuserid = beInvitedUser;
                } else {
                    if (president.equals(rangChild[1])) {
                        ruletype = Const.STR2;
                    } else {
                        ruletype = Const.STR3;
                    }
                    if (isPowerOfTwo((int) (1 + Math.random() * (10 - 1 + 1)))) {//随机数
                        whiteuserid = inviteUser;
                        blackuserid = beInvitedUser;
                    } else {
                        whiteuserid = beInvitedUser;
                        blackuserid = inviteUser;
                    }
                }
                creatInforChess(size, ruletype, blackuserid, whiteuserid, friendsId);
                break;
            case R.id.button_setup_rules:
                if (Util.isClickable()) return;
                SetUpRulesBean setUpRulesBean = new SetUpRulesBean();
                setUpRulesBean.setBeInvitedLevel(beInvitedLevel);
                setUpRulesBean.setBeInvitedName(beInvitedName);
                setUpRulesBean.setBeInvitedUser(beInvitedUser);
                setUpRulesBean.setBeInviteHeadImg(beInviteHeadImg);
                setUpRulesBean.setMessageType(Const.STR3);
                setUpRulesBean.setInviteHeadImg(inviteHeadImg);
                setUpRulesBean.setInviteLevel(inviteLevel);
                setUpRulesBean.setInviteName(inviteName);
                setUpRulesBean.setInviteUser(inviteUser);
                String text = presidentText.getText().toString();
                switch (text) {
                    case "分先":
                        setUpRulesBean.setRuletype(Const.STR1);
                        break;
                    case "让4子":
                        setUpRulesBean.setRuletype(Const.STR2);
                        break;
                    case "让9子":
                        setUpRulesBean.setRuletype(Const.STR3);
                        break;
                    default:
                        break;
                }
                String presidentSize = presidentText5.getText().toString();
                if (way[0].equals(presidentSize)) {
                    setUpRulesBean.setSize(Const.STR19);
                } else {
                    setUpRulesBean.setSize(Const.STR9);
                }
                Gson gson = new Gson();
                String gsonString = gson.toJson(setUpRulesBean);
                LogUtil.e(gsonString);
                if (userId.equals(beInvitedUser)) {
                    getInvitePlayChess(gsonString, inviteUser);
                } else {
                    getInvitePlayChess(gsonString, beInvitedUser);
                }
                break;
            case R.id.button_refuse:
                if (Util.isClickable()) return;
                SetUpRulesBean setUpRulesBean2 = new SetUpRulesBean();
                setUpRulesBean2.setMessageType(Const.STR4);
                setUpRulesBean2.setBeInvitedLevel(beInvitedLevel);
                setUpRulesBean2.setBeInvitedName(beInvitedName);
                setUpRulesBean2.setBeInvitedUser(beInvitedUser);
                setUpRulesBean2.setBeInviteHeadImg(beInviteHeadImg);
                setUpRulesBean2.setInviteHeadImg(inviteHeadImg);
                setUpRulesBean2.setInviteLevel(inviteLevel);
                setUpRulesBean2.setInviteName(inviteName);
                setUpRulesBean2.setInviteUser(inviteUser);
                String text2 = presidentText.getText().toString();
                switch (text2) {
                    case "分先":
                        setUpRulesBean2.setRuletype(Const.STR1);
                        break;
                    case "让4子":
                        setUpRulesBean2.setRuletype(Const.STR2);
                        break;
                    case "让9子":
                        setUpRulesBean2.setRuletype(Const.STR3);
                        break;
                    default:
                        break;
                }
                String presidentSize2 = presidentText5.getText().toString();
                if (way[0].equals(presidentSize2)) {
                    setUpRulesBean2.setSize(Const.STR19);
                } else {
                    setUpRulesBean2.setSize(Const.STR9);
                }
                Gson gsons = new Gson();
                String gsonStrings = gsons.toJson(setUpRulesBean2);
                LogUtil.e(gsonStrings);
                if (userId.equals(beInvitedUser)) {
                    getInvitePlayChess(gsonStrings, inviteUser);
                } else {
                    getInvitePlayChess(gsonStrings, beInvitedUser);
                }
                break;
            default:
                break;
        }
    }

    private void showPopupWindow(final int type, final String[] stringList) {
        View layout = LayoutInflater.from(GetRulesActivity.this).inflate(R.layout.activity_management_group_list_layout, null, false);
        popupWindow = new PopupWindow(layout, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
        popupWindow.setTouchable(true);
        View view = getWindow().getDecorView();
        // 设置好参数之后再show
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        final StringScrollPicker stringScrollPicker = layout.findViewById(R.id.string_scroll);
        Button buttonConfirm = layout.findViewById(R.id.button_confirm);
        Button buttonCancel = layout.findViewById(R.id.button_cancel);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.button_cancel:
                        popupWindow.dismiss();
                        break;
                    case R.id.button_confirm:
                        int selectedPosition = stringScrollPicker.getSelectedPosition();
                        String itemString = stringList[selectedPosition];
                        switch (type) {
                            case 1:
                                if (!"分先".equals(itemString)) {
                                    presidentText5.setText(way[0]);
                                }
                                presidentText.setText(itemString);
                                break;
                            case 2:
                                presidentText2.setText(itemString);
                                break;
                            case 3:
                                presidentText3.setText(itemString);
                                break;
                            case 4:
                                presidentText4.setText(itemString);
                                break;
                            case 5:
                                presidentText5.setText(itemString);
                                break;
                            default:
                                break;
                        }
                        popupWindow.dismiss();
                        break;
                    default:
                        break;
                }
            }
        };
        buttonCancel.setOnClickListener(listener);
        buttonConfirm.setOnClickListener(listener);
        List<String> strings = new ArrayList<>(Arrays.asList(stringList));
        if (strings.size() > 0) {
            stringScrollPicker.setData(strings);
        }
    }

    private void creatInforChess(String size, String ruletype, String blackuserid, String whiteuserid, String friendsId) {
        try {
            OkHttpUtils.post().url(ContactUrl.POST_CREAT_INFOR_CHESS_PATH)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .addParams("blackuserid", blackuserid)
                    .addParams("friendsId", friendsId)
                    .addParams("ruletype", ruletype)
                    .addParams("size", size)
                    .addParams("whiteuserid", whiteuserid)
                    .build().execute(new StringCallback() {

                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    BaseBean baseBean = GsonUtil.gsonToBean(response, BaseBean.class, GetRulesActivity.this);
                    if (baseBean != null) {
                        Intent intent = new Intent(GetRulesActivity.this, JsWebViewActivity.class);
                        intent.putExtra("prefix", ContactUrl.PLAY);
                        startActivity(intent);
                        finish();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    private void getInvitePlayChess(String message, String opponentId) {
        try {
            OkHttpUtils.post().url(ContactUrl.POST_INVITE_PLAY_CHESS_PATH)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .addParams("userId", opponentId)
                    .addParams("message", message)
                    .build().execute(new StringCallback() {

                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    BaseBean baseBean = GsonUtil.gsonToBean(response, BaseBean.class, GetRulesActivity.this);
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
