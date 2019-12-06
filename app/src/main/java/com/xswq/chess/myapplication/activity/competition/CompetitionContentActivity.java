package com.xswq.chess.myapplication.activity.competition;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.base.BaseCompatActivity;
import com.xswq.chess.myapplication.bean.CompetitionManagementBean;
import com.xswq.chess.myapplication.bean.ConpetitionContentBean;
import com.xswq.chess.myapplication.config.Const;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.GsonUtil;
import com.xswq.chess.myapplication.utils.ToastUtils;
import com.xswq.chess.myapplication.utils.Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import okhttp3.Call;

/**
 * 比赛介绍
 */
public class CompetitionContentActivity extends BaseCompatActivity implements View.OnClickListener {

    @BindView(R.id.edit_text)
    EditText editText;
    @BindView(R.id.text_number)
    TextView textView;
    @BindView(R.id.login_titles)
    TextView loginTitles;
    @BindView(R.id.login_back)
    TextView loginBack;
    @BindView(R.id.button_next)
    Button buttonNext;

    private String matchName;
    private int matchPattern;
    private String invitedSch;
    private String startTime;
    private String endTime;
    private String matchIntroduce;
    private CompetitionManagementBean.DataBean.ListBean listBean;
    private String strId;
    private int stage;
    private int matchType;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_competition_content_layout;
    }

    @Override
    protected void initView() {
        getWindow().getDecorView().setBackgroundResource(R.mipmap.wei_qi_story_list_bg);
        loginTitles.setText("创建比赛");
        loginBack.setVisibility(View.VISIBLE);
        loginBack.setOnClickListener(this);
        editText.addTextChangedListener(mTextWatcher);
        buttonNext.setOnClickListener(this);
    }

    @Override
    protected void loadData() {
        Intent intent = getIntent();
        matchName = intent.getStringExtra("matchName");
        matchPattern = intent.getIntExtra("matchPattern", 2);
        invitedSch = intent.getStringExtra("invitedSch");
        startTime = intent.getStringExtra("startTime") + ":00";
        endTime = intent.getStringExtra("endTime") + ":00";
        matchType = intent.getIntExtra("matchType", 0);
        listBean = (CompetitionManagementBean.DataBean.ListBean) intent.getSerializableExtra("competitionManagementBean");
        if (listBean != null) {
            strId = listBean.getId();
            stage = listBean.getStage();
            String matchIntroduce = listBean.getMatchIntroduce();
            editText.setText(Util.signString(matchIntroduce));
        }
    }

    TextWatcher mTextWatcher = new TextWatcher() {
        private CharSequence temp;
        private int editStart;
        private int editEnd;

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            temp = s;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            // TODO Auto-generated method stub
            editStart = editText.getSelectionStart();
            editEnd = editText.getSelectionEnd();
            String textNum = temp.length() + "/150";
            textView.setText(textNum);
            if (temp.length() > 150) {
                Toast.makeText(CompetitionContentActivity.this,
                        "你输入的字数已经超过了限制！", Toast.LENGTH_SHORT)
                        .show();
                s.delete(editStart - 1, editEnd);
                int tempSelection = editStart;
                editText.setText(s);
                editText.setSelection(tempSelection);
            }
        }
    };

    //提交信息
    private void putCompetitionData() {
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
            if (listBean != null) {
                post.addParams("id", strId);
            }
            if (listBean != null) {
                post.addParams("stage", String.valueOf(stage));
            } else {
                post.addParams("stage", Const.STR0);
            }

            post.build().execute(new StringCallback() {

                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    ConpetitionContentBean conpetitionContentBean = GsonUtil.gsonToBean(response, ConpetitionContentBean.class, CompetitionContentActivity.this);
                    if (conpetitionContentBean != null) {
                        ConpetitionContentBean.DataBean data = conpetitionContentBean.getData();
                        if (data != null) {
                            String matchId = data.getId();
                            Intent intent = new Intent(CompetitionContentActivity.this, CompetitionSetTheCategoryActivity.class);
                            if (matchType == 1) {
                                intent.putExtra("matchType", 1);
                            }
                            intent.putExtra("matchId", matchId);
                            startActivity(intent);
                            finish();
                        }
                    }
                }
            });
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
                matchIntroduce = editText.getText().toString().trim();
                if (TextUtils.isEmpty(matchIntroduce)) {
                    ToastUtils.show("请输入描述内容");
                } else {
                    putCompetitionData();
                }
                break;
            default:
                break;
        }
    }

    private void showPopupWindow() {
        View layout = LayoutInflater.from(CompetitionContentActivity.this).inflate(R.layout.create_competition_pop_window_layout, null, false);
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
