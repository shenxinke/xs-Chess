package com.xswq.chess.myapplication.activity.competition;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.adapter.NewGroupStartTimeAdapter;
import com.xswq.chess.myapplication.base.BaseBean;
import com.xswq.chess.myapplication.base.BaseCompatActivity;
import com.xswq.chess.myapplication.bean.CompetitionSetThrCategoryBean;
import com.xswq.chess.myapplication.bean.NewGroupStartTimeBean;
import com.xswq.chess.myapplication.config.Const;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.DateUtil;
import com.xswq.chess.myapplication.utils.GsonUtil;
import com.xswq.chess.myapplication.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.Call;

/**
 * 创建组别3
 */
public class NewGroupStartTimeActivity extends BaseCompatActivity implements View.OnClickListener {
    private String groupTextName;
    private String groupTextPattern;
    private String groupTextJudgment;
    private String groupTextLeve;
    private String popleNumber;
    private String gameTime;
    private String secondsSize;
    private String layTime;
    private ListView listView;
    private List<NewGroupStartTimeBean> stringList = new ArrayList<>();
    private int gameRoundsNumber;
    private NewGroupStartTimeAdapter groupListBeanBaseListAdapter;
    private CompetitionSetThrCategoryBean.DataBean.GroupListBean competitionSetBean;
    private String chessRule;
    private String chessNum;
    private String rounds;
    private String matchId;
    private String road;
    private String endTime;
    String pattern = "yyyy-MM-dd HH:mm";


    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_group_start_time_layout;
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

        //参赛人数
        popleNumber = intent.getStringExtra("PopleNumber");
        //比赛轮次
        String gameRounds = intent.getStringExtra("GameRounds");
        //对局时间
        gameTime = intent.getStringExtra("GameTime");
        //读秒次数
        secondsSize = intent.getStringExtra("secondsSize");
        //迟到时间
        layTime = intent.getStringExtra("LayTime");
        matchId = intent.getStringExtra("matchId");
        //比赛结束时间
        endTime = intent.getStringExtra("endTime");
        competitionSetBean = (CompetitionSetThrCategoryBean.DataBean.GroupListBean) intent.getSerializableExtra("competitionSetBean");

        TextView loginTitles = findViewById(R.id.login_titles);
        loginTitles.setText("新建组别");
        TextView loginBack = findViewById(R.id.login_back);
        loginBack.setVisibility(View.VISIBLE);
        loginBack.setOnClickListener(this);
        findViewById(R.id.button_next).setOnClickListener(this);
        listView = findViewById(R.id.list_view);
        if (!TextUtils.isEmpty(gameRounds)) {
            String substring = gameRounds.substring(0, gameRounds.length() - 1);
            gameRoundsNumber = Integer.valueOf(substring);
        }
    }

    @Override
    protected void loadData() {
        try {
            Date date = new Date();
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            String nowDate = sf.format(date);
            for (int i = 1; i <= gameRoundsNumber; i++) {
                NewGroupStartTimeBean newGroupStartTimeBean = new NewGroupStartTimeBean();
                Calendar cal = Calendar.getInstance();
                cal.setTime(sf.parse(nowDate));
                cal.add(Calendar.DAY_OF_YEAR, +i);
                newGroupStartTimeBean.setTime(sf.format(cal.getTime()) + " 18:00");
                stringList.add(newGroupStartTimeBean);
            }
            groupListBeanBaseListAdapter = new NewGroupStartTimeAdapter(stringList, NewGroupStartTimeActivity.this);
            listView.setAdapter(groupListBeanBaseListAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_back:
                showPopupWindow();
                break;
            case R.id.button_next:
                if (groupListBeanBaseListAdapter != null) {
                    StringBuilder stringBuffer = new StringBuilder();
                    List<NewGroupStartTimeBean> list = groupListBeanBaseListAdapter.getList();
                    for (NewGroupStartTimeBean str : list) {
                        stringBuffer.append(str.getTime()).append(":00,");
                    }
                    rounds = stringBuffer.substring(0, stringBuffer.length() - 1);
                    if (dataIsOk(list)) {
                        ToastUtils.show("第一轮开始时间与报名截止时间最少间隔20分钟");
                        return;
                    }
                    if (dataListIsOk(list)) {
                        ToastUtils.show("每轮开赛时间与上轮开赛时间至少间隔6小时");
                        return;
                    }
                    if (TextUtils.isEmpty(rounds)) {
                        ToastUtils.show("日期设置失败");
                        return;
                    }
                    if ("9路吃子赛".equals(groupTextPattern)) {
                        chessRule = Const.STR1;
                        chessNum = Const.STR3;
                        road = Const.STR9;
                        groupTextJudgment = Const.STR0;
                    } else {
                        if (TextUtils.isEmpty(groupTextJudgment)) {
                            groupTextJudgment = Const.STR0;
                        }
                        chessRule = Const.STR2;
                        chessNum = Const.STR375;
                        road = Const.STR19;
                    }
                    createUpdateGroup();
                }
                break;
            default:
                break;
        }
    }

    private boolean dataIsOk(List<NewGroupStartTimeBean> list) {
        long endTimeString = DateUtil.getStringToDate(endTime, pattern) + 1200000;
        long time = DateUtil.getStringToDate(list.get(0).getTime(), pattern);
        return time < endTimeString;
    }

    private boolean dataListIsOk(List<NewGroupStartTimeBean> list) {
        for (int i = 0; i < list.size(); i++) {
            int index = i + 1;
            long start;
            long lastStart = DateUtil.getStringToDate(list.get(i).getTime(), pattern);
            if (index >= list.size()) {
                return false;
            } else {
                start = DateUtil.getStringToDate(list.get(index).getTime(), pattern);
            }
//          21600000=6个小时
            long end = lastStart + 21600000;
            if (start < end) {
                return true;
            }
        }
        return false;
    }




    private void createUpdateGroup() {
        try {
            PostFormBuilder post = OkHttpUtils.post();
            post.url(ContactUrl.POST_CREATE_UPDATE_GROUP_PATH);

            post.addParams("token", token);
            post.addParams("uid", userId);
            post.addParams("matchId", matchId);
            post.addParams("groupName", groupTextName);
            post.addParams("road", road);
            post.addParams("matchRule", Const.STR2);
            post.addParams("judgeChess", groupTextJudgment);
            post.addParams("levelLimits", groupTextLeve);
            post.addParams("participantNum", popleNumber);
            post.addParams("baseTime", gameTime);
            post.addParams("countdown", Const.STR30);
            post.addParams("countdownNum", secondsSize);
            post.addParams("lateTime", layTime);
            post.addParams("chessRule", chessRule);
            post.addParams("chessNum", chessNum);
            post.addParams("rounds", rounds);
            if (competitionSetBean != null) {
                String id = competitionSetBean.getId();
                post.addParams("id", id);
            }
            post.build().execute(new StringCallback() {

                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    BaseBean baseBean = GsonUtil.gsonToBean(response, BaseBean.class, NewGroupStartTimeActivity.this);
                    if (baseBean != null) {
                        finish();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showPopupWindow() {
        View layout = LayoutInflater.from(NewGroupStartTimeActivity.this).inflate(R.layout.create_competition_pop_window_layout, null, false);
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
