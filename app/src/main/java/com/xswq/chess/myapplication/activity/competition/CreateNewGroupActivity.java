package com.xswq.chess.myapplication.activity.competition;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.activity.start.LoginActivity;
import com.xswq.chess.myapplication.base.BaseCompatActivity;
import com.xswq.chess.myapplication.bean.AllUserOrgnoBean;
import com.xswq.chess.myapplication.bean.CompetitionSetThrCategoryBean;
import com.xswq.chess.myapplication.config.Const;
import com.xswq.chess.myapplication.customview.MaterialSpinner;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.ToastUtils;
import com.xswq.chess.myapplication.utils.Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 创建组别1
 */
public class CreateNewGroupActivity extends BaseCompatActivity implements View.OnClickListener {

    private MaterialSpinner groupPattern;
    private MaterialSpinner groupMove;
    private MaterialSpinner groupJudgment;
    private MaterialSpinner groupMixLeve;
    private MaterialSpinner groupMaxLeve;
    private EditText groupName;
    private String[] strPattern = new String[]{"9路吃子赛", "19路对局赛"};
    private String[] strMove = new String[]{"AI判棋", "裁判判棋"};
    private String[] strLevel = new String[]{"25k", "20k", "15k", "10k", "5k", "2k", "1k", "1D", "2D", "3D", "4D", "5D"};
    private TextView textJudgment;
    private String invitedSch;
    private String createOrg;
    private String teacherId;
    private String matchId;
    private String endTime;
    private CompetitionSetThrCategoryBean.DataBean.GroupListBean competitionSetBean;
    private PopupWindow popupWindow;
    private int matchType;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_create_new_group_layout;
    }

    @Override
    protected void initView() {
        getWindow().getDecorView().setBackgroundResource(R.mipmap.wei_qi_story_list_bg);
        Intent intent = getIntent();
        invitedSch = intent.getStringExtra("invitedSch");
        createOrg = intent.getStringExtra("createOrg");
        matchId = intent.getStringExtra("matchId");
        endTime = intent.getStringExtra("endTime");
        matchType = intent.getIntExtra("matchType", 0);
        competitionSetBean = (CompetitionSetThrCategoryBean.DataBean.GroupListBean) intent.getSerializableExtra("competitionSetBean");
        TextView loginTitles = findViewById(R.id.login_titles);
        loginTitles.setText("新建组别");
        TextView loginBack = findViewById(R.id.login_back);
        loginBack.setVisibility(View.VISIBLE);
        loginBack.setOnClickListener(this);
        findViewById(R.id.button_next).setOnClickListener(this);
        groupName = findViewById(R.id.edit_text_name);
        groupPattern = findViewById(R.id.edit_text_pattern);
        groupPattern.setPadding(Util.dip2px(this, 11), 0, Util.dip2px(this, 11), 0);
        groupMove = findViewById(R.id.edit_text_move);
        groupMove.setPadding(Util.dip2px(this, 11), 0, Util.dip2px(this, 11), 0);
        groupJudgment = findViewById(R.id.edit_text_judgment);
        textJudgment = findViewById(R.id.text_judgment);
        groupJudgment.setPadding(Util.dip2px(this, 11), 0, Util.dip2px(this, 11), 0);
        groupMixLeve = findViewById(R.id.edit_text_mix_leve);
        groupMixLeve.setPadding(Util.dip2px(this, 11), 0, Util.dip2px(this, 11), 0);
        groupMaxLeve = findViewById(R.id.edit_text_max_leve);
        groupMaxLeve.setPadding(Util.dip2px(this, 11), 0, Util.dip2px(this, 11), 0);
    }

    @Override
    protected void loadData() {
        initPattern();
        initJudgment();
        initMixLevel();
        initMaxLevel();
        if (competitionSetBean != null) {
            String matchName = competitionSetBean.getGroupName();
            int road = competitionSetBean.getRoad();
            groupName.setText(Util.signString(matchName));
            if (road == Const.INTEGER_9) {
                groupPattern.setText(Util.signString(strPattern[0]));
            } else {
                groupPattern.setText(Util.signString(strPattern[1]));
            }
            String levelLimits = competitionSetBean.getLevelLimits();
            if (!TextUtils.isEmpty(levelLimits)) {
                String subMixLevel = levelLimits.substring(0, levelLimits.indexOf("-"));
                groupMixLeve.setText(Util.signString(subMixLevel));
                String subMaxLevel = levelLimits.substring(levelLimits.indexOf("-") + 1);
                groupMaxLeve.setText(Util.signString(subMaxLevel));
            }
            int judgeChess = competitionSetBean.getJudgeChess();
            if (judgeChess == Const.INTEGER_0) {
                strMove = new String[]{"AI判棋"};
                groupJudgment.setVisibility(View.GONE);
                textJudgment.setVisibility(View.GONE);
            } else {
                strMove = new String[]{"AI判棋", "裁判判棋"};
                groupJudgment.setVisibility(View.VISIBLE);
                textJudgment.setVisibility(View.VISIBLE);
            }
        }
        initMove();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_back:
                showPopupWindow();
                break;
            case R.id.button_next:
                String groupTextName = groupName.getText().toString().trim();
                String groupTextPattern = groupPattern.getText().toString();
                String groupTextMove = groupMove.getText().toString();
                String groupTextJudgment = groupJudgment.getText().toString();
                String groupTextMixLeve = groupMixLeve.getText().toString();
                String groupTextMaxLeve = groupMaxLeve.getText().toString();

                if (TextUtils.isEmpty(groupTextName)) {
                    ToastUtils.show("请输入比赛名称");
                    return;
                }
                if (groupTextPattern.equals("请选择比赛类型")) {
                    ToastUtils.show("请选择比赛类型");
                    return;
                }
                if (groupTextMove.equals("请选择判棋方式")) {
                    ToastUtils.show("请选择判棋方式");
                    return;
                }
                if (groupTextMove.equals("裁判判棋") && groupTextJudgment.equals("请选择裁判")) {
                    ToastUtils.show("请选择裁判");
                    return;
                }
                if (groupTextMixLeve.equals("请选择参赛棋力")) {
                    ToastUtils.show("请选择参赛棋力");
                    return;
                }
                if (groupTextMaxLeve.equals("请选择参赛棋力")) {
                    ToastUtils.show("请选择参赛棋力");
                    return;
                }

                Intent intent = new Intent(CreateNewGroupActivity.this, NewGroupCompetitionRulesActivity.class);
                intent.putExtra("groupTextName", groupTextName);
                intent.putExtra("groupTextPattern", groupTextPattern);
                intent.putExtra("groupTextJudgment", teacherId);
                intent.putExtra("matchId", matchId);
                intent.putExtra("endTime", endTime);
                intent.putExtra("groupTextLevel", groupTextMixLeve + "-" + groupTextMaxLeve);
                intent.putExtra("competitionSetBean", competitionSetBean);
                startActivity(intent);
                finish();
                break;
            default:
                break;

        }
    }

    private void initPattern() {
        for (String str : strPattern) {
            groupPattern.setItem(str);
        }
        groupPattern.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                if (item.equals("9路吃子赛")) {
                    strMove = new String[]{"AI判棋"};
                } else {
                    strMove = new String[]{"AI判棋", "裁判判棋"};
                }
                initMove();
            }
        });
    }

    private void initMove() {
        groupMove.notifyData();
        for (String str : strMove) {
            groupMove.setItem(str);
        }
        if (competitionSetBean != null) {
            int judgeChess = competitionSetBean.getJudgeChess();
            if (judgeChess == Const.INTEGER_0) {
                groupMove.setText(Util.signString(strMove[0]));
            } else {
                if (strMove.length > 1) {
                    groupMove.setText(Util.signString(strMove[1]));
                }
            }
        }

        groupMove.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                if (item.equals("裁判判棋")) {
                    groupJudgment.setVisibility(View.VISIBLE);
                    textJudgment.setVisibility(View.VISIBLE);
                } else {
                    groupJudgment.setVisibility(View.GONE);
                    textJudgment.setVisibility(View.GONE);
                }
            }
        });
    }

    private void initJudgment() {
        if (!TextUtils.isEmpty(createOrg)) {
            if (matchType == 1) {
                groupJudgment.setItem(username);
                teacherId = userId;
            } else {
                getTeacherInfor();
            }
        } else {
            ToastUtils.show("暂无裁判信息");
        }
    }

    private void initMixLevel() {
        for (String str : strLevel) {
            groupMixLeve.setItem(str);
        }
    }

    private void initMaxLevel() {
        for (String str : strLevel) {
            groupMaxLeve.setItem(str);
        }
    }

    //获取从后台老师的信息
    private void getTeacherInfor() {
        String orgNoId;
        try {
            PostFormBuilder post = OkHttpUtils.post();
            post.url(ContactUrl.GET_ALL_USER_ORGNO);
            post.addParams("token", token);
            post.addParams("uid", userId);
            if (!TextUtils.isEmpty(invitedSch)) {
                orgNoId = createOrg + "," + invitedSch;
            } else {
                orgNoId = createOrg;
            }
            post.addParams("orgNo", orgNoId);
            post.build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    if (!TextUtils.isEmpty(response)) {
                        try {
                            JSONObject mjson = new JSONObject(response);
                            JSONObject error = mjson.getJSONObject("error");
                            if (error.getInt("returnCode") == 0) {
                                JSONArray data = mjson.getJSONArray("data");
                                List<AllUserOrgnoBean.DataBean> mListJson = new ArrayList<>();
                                for (int i = 0; i < data.length(); i++) {
                                    JSONObject temp = (JSONObject) data.get(i);
                                    String ID = temp.getString("ID");  //朋友id
                                    String UserName = temp.getString("UserName");  //朋友id
                                    AllUserOrgnoBean.DataBean allUserOrgnoBean = new AllUserOrgnoBean.DataBean();
                                    allUserOrgnoBean.setID(ID);
                                    allUserOrgnoBean.setUserName(UserName);
                                    mListJson.add(allUserOrgnoBean);
                                }
                                assignmentTeacherInfor(mListJson);
                            } else if (error.getInt("returnCode") == 10048) {
                                ToastUtils.show("用户过期，请重新登录!");
                                Intent intent = new Intent(CreateNewGroupActivity.this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void assignmentTeacherInfor(final List<AllUserOrgnoBean.DataBean> mListJson) {
        for (int i = 0; i < mListJson.size(); i++) {
            groupJudgment.setItem(mListJson.get(i).getUserName());
        }
        groupJudgment.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                teacherId = mListJson.get(position).getID();
            }
        });

        if (competitionSetBean != null) {
            int judgeChess = competitionSetBean.getJudgeChess();
            String judgeName = competitionSetBean.getJudgeName();
            if (judgeChess == Const.INTEGER_0) {
                groupJudgment.setVisibility(View.GONE);
                textJudgment.setVisibility(View.GONE);
            } else {
                groupJudgment.setVisibility(View.VISIBLE);
                textJudgment.setVisibility(View.VISIBLE);
                groupJudgment.setText(Util.signString(judgeName));
                teacherId = String.valueOf(competitionSetBean.getJudgeChess());
            }
        }
    }

    private void showPopupWindow() {
        View layout = LayoutInflater.from(CreateNewGroupActivity.this).inflate(R.layout.create_competition_pop_window_layout, null, false);
        View view = getWindow().getDecorView();
        popupWindow = new PopupWindow(layout, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
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
