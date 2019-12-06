package com.xswq.chess.myapplication.activity.competition;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.base.BaseCompatActivity;
import com.xswq.chess.myapplication.bean.CompetitionSetThrCategoryBean;
import com.xswq.chess.myapplication.customview.MaterialSpinner;
import com.xswq.chess.myapplication.utils.ToastUtils;
import com.xswq.chess.myapplication.utils.Util;

/**
 * 创建组别2
 */

public class NewGroupCompetitionRulesActivity extends BaseCompatActivity implements View.OnClickListener {
    private MaterialSpinner textPopleNumber;
    private String[] popleNumber = new String[]{"4-8人", "8-16人", "16-32人", "32-64人", "64-128人", "128-256人"};
    private String[] gameRounds;
    private String[] gameTime = new String[]{"5分钟", "10分钟", "15分钟", "20分钟", "30分钟", "40分钟", "50分钟", "60分钟", "90分钟"};
    private String[] seconds = new String[]{"30秒/1次", "30秒/2次", "30秒/3次"};
    private String[] layTime = new String[]{"5分钟", "10分钟", "15分钟"};
    private MaterialSpinner textGameRounds;
    private MaterialSpinner textGameTime;
    private MaterialSpinner textSeconds;
    private MaterialSpinner textLayTime;
    private String groupTextName;
    private String groupTextPattern;
    private String groupTextJudgment;
    private String groupTextLeve;
    private String gameTimeString;
    private String secondsSize;
    private String popleNumberString;
    private String matchId;
    private String layTimeString;
    private String endTime;
    private CompetitionSetThrCategoryBean.DataBean.GroupListBean competitionSetBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_group_competition_rules_layout;
    }

    @Override
    protected void initView() {
        getWindow().getDecorView().setBackgroundResource(R.mipmap.wei_qi_story_list_bg);
        Intent intent = getIntent();
        //组别名称
        groupTextName = intent.getStringExtra("groupTextName");
        //比赛类型
        groupTextPattern = intent.getStringExtra("groupTextPattern");
        //裁判
        groupTextJudgment = intent.getStringExtra("groupTextJudgment");
        //参赛棋力
        groupTextLeve = intent.getStringExtra("groupTextLevel");
        matchId = intent.getStringExtra("matchId");
        endTime = intent.getStringExtra("endTime");
        competitionSetBean = (CompetitionSetThrCategoryBean.DataBean.GroupListBean) intent.getSerializableExtra("competitionSetBean");

        TextView loginTitles = findViewById(R.id.login_titles);
        loginTitles.setText("新建组别");
        TextView loginBack = findViewById(R.id.login_back);
        loginBack.setVisibility(View.VISIBLE);
        loginBack.setOnClickListener(this);
        findViewById(R.id.button_next).setOnClickListener(this);

        TextView textView = findViewById(R.id.text);
        if ("9路吃子赛".equals(groupTextPattern)) {
            textView.setText("吃三子胜");
        } else {
            textView.setText("分先  黑贴3又3/4子");
        }
        textPopleNumber = findViewById(R.id.edit_text_people_number);
        textPopleNumber.setPadding(Util.dip2px(this, 11), 0, Util.dip2px(this, 11), 0);
        textGameRounds = findViewById(R.id.edit_text_game_rounds);
        textGameRounds.setPadding(Util.dip2px(this, 11), 0, Util.dip2px(this, 11), 0);
        textGameTime = findViewById(R.id.edit_text_game_time);
        textGameTime.setPadding(Util.dip2px(this, 11), 0, Util.dip2px(this, 11), 0);
        textSeconds = findViewById(R.id.edit_text_seconds);
        textSeconds.setPadding(Util.dip2px(this, 11), 0, Util.dip2px(this, 11), 0);
        textLayTime = findViewById(R.id.edit_text_lay_time);
        textLayTime.setPadding(Util.dip2px(this, 11), 0, Util.dip2px(this, 11), 0);
    }

    @Override
    protected void loadData() {
        initPopleNumber();
        initGameTime();
        initSeconds();
        initLayTime();
        if (competitionSetBean != null) {
            String participantNum = competitionSetBean.getParticipantNum() + "人";
            String rounds = competitionSetBean.getRounds();
            String baseTime = competitionSetBean.getBaseTime() + "分钟";
            String countdownNum = "30秒/" + competitionSetBean.getCountdownNum() + "次";
            String lateTime = competitionSetBean.getLateTime() + "分钟";
            textPopleNumber.setText(Util.signString(participantNum));
            textGameRounds.setText(Util.signString(rounds));
            textGameTime.setText(Util.signString(baseTime));
            textSeconds.setText(Util.signString(countdownNum));
            textLayTime.setText(Util.signString(lateTime));
            secondsSize = competitionSetBean.getCountdownNum();
            gameRounds = getPopleNum(participantNum);
            initGameRounds();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_back:
                showPopupWindow();
                break;
            case R.id.button_next:
                String popleNumber = textPopleNumber.getText().toString();
                String gameRounds = textGameRounds.getText().toString();
                String gameTime = textGameTime.getText().toString();
                String seconds = textSeconds.getText().toString();
                String layTime = textLayTime.getText().toString();
                if ("请选择参赛人数".equals(popleNumber)) {
                    ToastUtils.show("请选择参赛人数");
                    return;
                }
                if ("请选择比赛轮次".equals(gameRounds)) {
                    ToastUtils.show("请选择比赛轮次");
                    return;
                }
                if ("请选择对局时间".equals(gameTime)) {
                    ToastUtils.show("请选择对局时间");
                    return;
                }
                if ("请选择读秒时间".equals(seconds)) {
                    ToastUtils.show("请选择读秒时间");
                    return;
                }
                if ("请选择迟到时间".equals(layTime)) {
                    ToastUtils.show("请选择迟到时间");
                    return;
                }


                if (!TextUtils.isEmpty(popleNumber)) {
                    popleNumberString = popleNumber.substring(0, popleNumber.length() - 1);
                }

                if (!TextUtils.isEmpty(gameTime)) {
                    gameTimeString = gameTime.substring(0, gameTime.length() - 2);
                }

                if (!TextUtils.isEmpty(layTime)) {
                    layTimeString = layTime.substring(0, layTime.length() - 2);
                }


                Intent intent = new Intent(NewGroupCompetitionRulesActivity.this, NewGroupStartTimeActivity.class);
                intent.putExtra("groupTextName", groupTextName);
                intent.putExtra("groupTextPattern", groupTextPattern);
                intent.putExtra("groupTextJudgment", groupTextJudgment);
                intent.putExtra("groupTextLevel", groupTextLeve);

                intent.putExtra("PopleNumber", popleNumberString);
                intent.putExtra("GameRounds", gameRounds);
                intent.putExtra("GameTime", gameTimeString);
                intent.putExtra("secondsSize", secondsSize);
                intent.putExtra("LayTime", layTimeString);
                intent.putExtra("matchId", matchId);
                intent.putExtra("endTime", endTime);
                intent.putExtra("competitionSetBean", competitionSetBean);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }

    private void initPopleNumber() {
        for (String strPople : popleNumber) {
            textPopleNumber.setItem(strPople);
        }
        textPopleNumber.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                String popleNumberText = String.valueOf(item);
                gameRounds = getPopleNum(popleNumberText);
                initGameRounds();
            }
        });
    }

    private void initGameRounds() {
        textGameRounds.notifyData();
        for (String strPople : gameRounds) {
            textGameRounds.setItem(strPople);
        }
        if (competitionSetBean != null) {
            String rounds = competitionSetBean.getRounds() + "轮";
            textGameRounds.setText(Util.signString(rounds));
        }
    }

    private void initGameTime() {
        for (String strPople : gameTime) {
            textGameTime.setItem(strPople);
        }
    }

    private void initSeconds() {
        for (String strPople : seconds) {
            textSeconds.setItem(strPople);
        }
        textSeconds.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                secondsSize = String.valueOf(position + 1);
            }
        });
    }

    private void initLayTime() {
        for (String strPople : layTime) {
            textLayTime.setItem(strPople);
        }
    }

    private String[] getPopleNum(String popleNumberText) {
        String[] strings;
        switch (popleNumberText) {
            case "4-8人":
                strings = new String[]{"3轮"};
                break;
            case "8-16人":
                strings = new String[]{"3轮", "5轮"};
                break;
            case "16-32人":
                strings = new String[]{"3轮", "5轮", "7轮"};
                break;
            case "32-64人":
                strings = new String[]{"3轮", "5轮", "7轮", "9轮"};
                break;
            case "64-128人":
                strings = new String[]{"3轮", "5轮", "7轮", "9轮", "11轮"};
                break;
            default:
                strings = new String[]{"3轮", "5轮", "7轮", "9轮", "11轮", "13轮"};
                break;
        }
        return strings;
    }

    private void showPopupWindow() {
        View layout = LayoutInflater.from(NewGroupCompetitionRulesActivity.this).inflate(R.layout.create_competition_pop_window_layout, null, false);
        View view = getWindow().getDecorView();
        final PopupWindow popupWindow = new PopupWindow(layout, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setClippingEnabled(false);
        ColorDrawable dw = new ColorDrawable(0xff);
        popupWindow.setBackgroundDrawable(dw);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        Button btnSava = layout.findViewById(R.id.button_save);
        Button btnCancel = layout.findViewById(R.id.button_cancel);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.button_save:
                        popupWindow.dismiss();
                        finish();
                        break;
                    case R.id.button_cancel:
                        popupWindow.dismiss();
                        break;
                    default:
                        break;
                }
            }
        };
        //设置popwindow布局控件的点击事件
        btnSava.setOnClickListener(listener);
        btnCancel.setOnClickListener(listener);
    }
}
