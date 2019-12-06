package com.xswq.chess.myapplication.activity.onlinegame;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
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
import com.xswq.chess.myapplication.base.BaseBean;
import com.xswq.chess.myapplication.base.BaseCompatActivity;
import com.xswq.chess.myapplication.bean.SetUpRulesBean;
import com.xswq.chess.myapplication.config.Const;
import com.xswq.chess.myapplication.customview.StringScrollPicker;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.GsonUtil;
import com.xswq.chess.myapplication.utils.ImageLoader;
import com.xswq.chess.myapplication.utils.LogUtil;
import com.xswq.chess.myapplication.utils.SPUtil;
import com.xswq.chess.myapplication.utils.ToastUtils;
import com.xswq.chess.myapplication.utils.Util;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;

public class SetUpRulesActivity extends BaseCompatActivity implements View.OnClickListener {
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

    private String opponentId;
    private int level;
    private String headImg;
    private String name;

    private int userLevel;
    private String userHeading;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setup_rules_layout;
    }

    @Override
    protected void initView() {
        userHeading = SPUtil.getString("userHeading", "");
        userLevel = SPUtil.getInt("level", 0);
        getWindow().getDecorView().setBackgroundResource(R.mipmap.aionline_gamebg);
        Intent intent = getIntent();
        headImg = intent.getStringExtra("userHeading");
        name = intent.getStringExtra("userName");
        level = intent.getIntExtra("userLevel",0);
        opponentId = intent.getStringExtra("userId");

        TextView login_back = findViewById(R.id.login_back);
        login_back.setVisibility(View.VISIBLE);
        login_back.setOnClickListener(this);
        TextView login_titles = findViewById(R.id.login_titles);
        login_titles.setText("设置规则");

        ImageView imageView = findViewById(R.id.image_head);
        TextView userNameView = findViewById(R.id.user_name);
        TextView textLevel = findViewById(R.id.text_level);
        textLevel.setText(Util.getChessLevel(level));
        userNameView.setText(Util.signString(name));
        String mainHeading = ContactUrl.BASE_PATH + "/gobangteach/gobangPc/" + headImg;
        ImageLoader.loadHeadImage(mainHeading,imageView);
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
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_back:
                finish();
                break;
            case R.id.president_text:
            case R.id.image:
                showPopupWindow(1, rangChild);
                break;
            case R.id.president_text2:
            case R.id.image2:
                showPopupWindow(2, time);
                break;
            case R.id.president_text3:
            case R.id.image3:
                showPopupWindow(3, second);
                break;
            case R.id.president_text4:
            case R.id.image4:
                showPopupWindow(4, numberTimeout);
                break;
            case R.id.president_text5:
            case R.id.image5:
                String string = presidentText.getText().toString();
                if (rangChild[0].equals(string)) {
                    showPopupWindow(5, way);
                }
                break;
            case R.id.button_yes:
                SetUpRulesBean setUpRulesBean = new SetUpRulesBean();
                setUpRulesBean.setBeInvitedLevel(String.valueOf(level));
                setUpRulesBean.setBeInvitedName(name);
                setUpRulesBean.setBeInvitedUser(opponentId);
                setUpRulesBean.setBeInviteHeadImg(headImg);
                setUpRulesBean.setMessageType(Const.STR1);
                setUpRulesBean.setInviteHeadImg(userHeading);
                setUpRulesBean.setInviteLevel(String.valueOf(userLevel));
                setUpRulesBean.setInviteName(username);
                setUpRulesBean.setInviteUser(userId);

                String president = presidentText.getText().toString();
                switch (president) {
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
                getInvitePlayChess(gsonString);
                break;
            default:
                break;
        }
    }

    private void showPopupWindow(final int type, final String[] stringList) {
        View layout = LayoutInflater.from(SetUpRulesActivity.this).inflate(R.layout.activity_management_group_list_layout, null, false);
        popupWindow = new PopupWindow(layout, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
        popupWindow.setTouchable(true);
        View view = getWindow().getDecorView();
        popupWindow.setClippingEnabled(false);
        ColorDrawable dw = new ColorDrawable(0xff);
        popupWindow.setBackgroundDrawable(dw);
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

    private void getInvitePlayChess(String message) {
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
                    BaseBean baseBean = GsonUtil.gsonToBean(response, BaseBean.class, SetUpRulesActivity.this);
                    if (baseBean != null) {
                        ToastUtils.show("邀请已发送");
                        finish();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
