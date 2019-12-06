package com.xswq.chess.myapplication.activity.competition;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.adapter.PlayByPlayAdapter;
import com.xswq.chess.myapplication.base.BaseCompatActivity;
import com.xswq.chess.myapplication.bean.ConpetitionContentBean;
import com.xswq.chess.myapplication.bean.StudentPlayByPlayBean;
import com.xswq.chess.myapplication.config.Const;
import com.xswq.chess.myapplication.customview.HorizontalListView;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.DateUtil;
import com.xswq.chess.myapplication.utils.GsonUtil;
import com.xswq.chess.myapplication.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

/**
 * 比赛详情
 */
public class PlayByPlayActivity extends BaseCompatActivity implements View.OnClickListener {

    private StudentPlayByPlayBean.DataBean listBean;
    private String matchName;
    private String matchIntroduce;
    private int matchPattern;
    private String startTime;
    private String endTime;
    private String invitedSch;
    private String strId;
    private Button buttonResult;
    private Button buttonSave;
    private Button buttonCancel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_play_by_play_layout;
    }

    @Override
    protected void initView() {
        getWindow().getDecorView().setBackgroundResource(R.mipmap.wei_qi_story_list_bg);
        Intent intent = getIntent();
        listBean = (StudentPlayByPlayBean.DataBean) intent.getSerializableExtra("competitionManagementBean");
        boolean flag = intent.getBooleanExtra("flag", false);
        TextView loginTitles = findViewById(R.id.login_titles);
        loginTitles.setText("比赛详情");
        TextView loginBack = findViewById(R.id.login_back);
        loginBack.setVisibility(View.VISIBLE);
        loginBack.setOnClickListener(this);
        HorizontalListView horizontalListView = findViewById(R.id.list_view);
        TextView schoolName = findViewById(R.id.school_name);
        TextView matchName = findViewById(R.id.match_name);
        TextView matchTime = findViewById(R.id.match_time);
        TextView matchIntroduced = findViewById(R.id.match_introduced);
        buttonResult = findViewById(R.id.result);
        buttonSave = findViewById(R.id.button_save);
        buttonSave.setOnClickListener(this);
        buttonCancel = findViewById(R.id.button_cancel);
        buttonCancel.setOnClickListener(this);
        if (flag) {
            buttonSave.setVisibility(View.VISIBLE);
            buttonCancel.setVisibility(View.VISIBLE);
        } else {
            buttonSave.setVisibility(View.GONE);
            buttonCancel.setVisibility(View.GONE);
        }
        if (listBean != null) {
            String matchNameString = "比赛名称:  " + listBean.getMatchName();
            String matchIntroduce = "比赛介绍:  " + listBean.getMatchIntroduce();
            String invitedSchName = listBean.getInvitedSchName();
            int stage = listBean.getStage();
            long startTime = listBean.getStartTime();
            long endTime = listBean.getEndTime();
            String strStartTime = DateUtil.getDateToString(startTime, "yyyy-MM-dd HH:mm");
            String strEndTime = DateUtil.getDateToString(endTime, "yyyy-MM-dd HH:mm");
            String competitionTime = "报名时间:  " + strStartTime + " ~ " + strEndTime;
            if (!TextUtils.isEmpty(invitedSchName)) {
                String school = "对方学校:  " + invitedSchName;
                schoolName.setText(school);
                schoolName.setVisibility(View.VISIBLE);
            } else {
                schoolName.setVisibility(View.GONE);
            }
            matchName.setText(matchNameString);
            matchTime.setText(competitionTime);
            matchIntroduced.setText(matchIntroduce);
            List<StudentPlayByPlayBean.DataBean.GroupListBean> groupList = listBean.getGroupList();
            PlayByPlayAdapter playByPlayAdapter = new PlayByPlayAdapter(PlayByPlayActivity.this, groupList, token, userId, userType, stage);
            horizontalListView.setAdapter(playByPlayAdapter);
            if (stage == 1) {
                buttonResult.setVisibility(View.GONE);
                buttonSave.setVisibility(View.VISIBLE);
                buttonCancel.setVisibility(View.VISIBLE);
            } else if (stage == 2) {
                buttonResult.setText("已同意");
                buttonResult.setVisibility(View.VISIBLE);
                buttonSave.setVisibility(View.GONE);
                buttonCancel.setVisibility(View.GONE);
            } else if (stage == 3) {
                buttonResult.setText("已拒绝");
                buttonResult.setVisibility(View.VISIBLE);
                buttonSave.setVisibility(View.GONE);
                buttonCancel.setVisibility(View.GONE);
            }
        }
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //stage 同意2 拒绝3
            case R.id.button_cancel:
                if (listBean != null) {
                    matchName = listBean.getMatchName();
                    matchIntroduce = listBean.getMatchIntroduce();
                    matchPattern = listBean.getMatchPattern();
                    startTime = DateUtil.getDateToString(listBean.getStartTime(), "yyyy-MM-dd HH:mm");
                    endTime = DateUtil.getDateToString(listBean.getEndTime(), "yyyy-MM-dd HH:mm");
                    invitedSch = listBean.getInvitedSch();
                    strId = listBean.getId();
                    if (!TextUtils.isEmpty(matchName) && !TextUtils.isEmpty(matchIntroduce)) {
                        putCompetitionData(3);
                    }
                }
                break;
            case R.id.button_save:
                if (listBean != null) {
                    matchName = listBean.getMatchName();
                    matchIntroduce = listBean.getMatchIntroduce();
                    matchPattern = listBean.getMatchPattern();
                    startTime = DateUtil.getDateToString(listBean.getStartTime(), "yyyy-MM-dd HH:mm");
                    endTime = DateUtil.getDateToString(listBean.getEndTime(), "yyyy-MM-dd HH:mm");
                    invitedSch = listBean.getInvitedSch();
                    strId = listBean.getId();
                    if (!TextUtils.isEmpty(matchName) && !TextUtils.isEmpty(matchIntroduce)) {
                        putCompetitionData(2);
                    }
                }
                break;
            case R.id.login_back:
                finish();
                break;
            default:
                break;
        }
    }

    private void putCompetitionData(final int stage) {
        try {
            PostFormBuilder post = OkHttpUtils.post();
            post.url(ContactUrl.POST_CREATE_UPDATE_MATCH);
            post.addParams("token", token);
            post.addParams("uid", userId);
            post.addParams("matchName", matchName);
            post.addParams("matchIntroduce", matchIntroduce);
            post.addParams("matchPattern", String.valueOf(matchPattern));
            post.addParams("startTime", startTime);
            post.addParams("endTime", endTime);
            if (matchPattern == 3) {
                post.addParams("invitedSch", invitedSch);
            }
            post.addParams("id", strId);
            post.addParams("stage", String.valueOf(stage));

            post.build().execute(new StringCallback() {

                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    ConpetitionContentBean conpetitionContentBean = GsonUtil.gsonToBean(response, ConpetitionContentBean.class, PlayByPlayActivity.this);
                    if (conpetitionContentBean != null) {
                        buttonResult.setVisibility(View.VISIBLE);
                        buttonSave.setVisibility(View.GONE);
                        buttonCancel.setVisibility(View.GONE);
                        if (stage == 2) {
                            buttonResult.setText("已同意");
                        } else {
                            buttonResult.setText("已拒绝");
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
