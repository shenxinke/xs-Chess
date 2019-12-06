package com.xswq.chess.myapplication.activity.personalCenter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.adapter.HistoryRecordAdapter;
import com.xswq.chess.myapplication.androidtojs.JsWebViewActivity;
import com.xswq.chess.myapplication.base.BaseCompatActivity;
import com.xswq.chess.myapplication.bean.HistoryMatchBean;
import com.xswq.chess.myapplication.config.Const;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.DateUtil;
import com.xswq.chess.myapplication.utils.GsonUtil;
import com.xswq.chess.myapplication.utils.ToastUtils;
import com.xswq.chess.myapplication.widget.CustomDatePicker;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.Call;

public class HistoryMatchActivity extends BaseCompatActivity implements View.OnClickListener {

    private EditText startSelectDate;
    private EditText endSelectDate;
    private CustomDatePicker startCustomDatePicker;
    private CustomDatePicker endCustomDatePicker;
    private String now;
    private TextView notData;
    private ListView mListView;
    private List<HistoryMatchBean.DataBean.ListBean> list = new ArrayList<>();
    private HistoryRecordAdapter mHistoryRecordAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.personal_historymatch_layout;
    }

    @Override
    protected void initView() {
        getWindow().getDecorView().setBackgroundResource(R.mipmap.personal_bg);
        TextView login_back = findViewById(R.id.login_back);
        login_back.setOnClickListener(this);
        login_back.setVisibility(View.VISIBLE);
        TextView login_titles = findViewById(R.id.login_titles);
        login_titles.setText("历史对局");
        notData = findViewById(R.id.not_data);
        startSelectDate = findViewById(R.id.start_etsearch);
        hidden(startSelectDate);
        startSelectDate.setOnClickListener(this);
        endSelectDate = findViewById(R.id.end_etsearch);
        hidden(endSelectDate);
        endSelectDate.setOnClickListener(this);
        ImageView ivDeleteText = findViewById(R.id.ivDeleteText);
        ivDeleteText.setOnClickListener(this);
        initDatePicker();
        mListView = findViewById(R.id.history_view);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent mIntent = new Intent(HistoryMatchActivity.this, JsWebViewActivity.class);
                mIntent.putExtra("webViewType", 3);
                mIntent.putExtra("historyGameId", list.get(i).getChessId());
                mIntent.putExtra("prefix", ContactUrl.ELECBOARD);
                startActivity(mIntent);
            }
        });
    }

    @Override
    protected void loadData() {
        getData();
    }

    private void getData() {
        showProgressDialog(true);
        try {
            PostFormBuilder post = OkHttpUtils.post();
            post.url(ContactUrl.PERSONAL_GETHISTORYRECORD_PATH)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .addParams("userId", userId)
                    .addParams("pageNum", Const.STR1)
                    .addParams("pageSize", Const.STR50);
            if (!TextUtils.isEmpty(startSelectDate.getText().toString().trim())) {
                post.addParams("BeginTime", startSelectDate.getText().toString().trim());
            }
            if (!TextUtils.isEmpty(endSelectDate.getText().toString().trim())) {
                post.addParams("EndTime", endSelectDate.getText().toString().trim());
            }
            post.build().execute(new StringCallback() {

                @Override
                public void onError(Call call, Exception e, int id) {
                    dismissProgressDialog();
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    dismissProgressDialog();
                    HistoryMatchBean historyMatchBean = GsonUtil.gsonToBean(response, HistoryMatchBean.class, HistoryMatchActivity.this);
                    if (historyMatchBean != null) {
                        HistoryMatchBean.DataBean data = historyMatchBean.getData();
                        if (data == null) {
                            notData.setVisibility(View.VISIBLE);
                            mListView.setVisibility(View.GONE);
                        } else {
                            list.addAll(data.getList());
                            if (list != null && !list.isEmpty()) {
                                notData.setVisibility(View.GONE);
                                mListView.setVisibility(View.VISIBLE);
                                if (mHistoryRecordAdapter == null) {
                                    mHistoryRecordAdapter = new HistoryRecordAdapter(HistoryMatchActivity.this, list,token,userId);
                                    mListView.setAdapter(mHistoryRecordAdapter);
                                } else {
                                    mHistoryRecordAdapter.upDataList(list);
                                }
                            }else {
                                notData.setVisibility(View.VISIBLE);
                                mListView.setVisibility(View.GONE);
                            }
                        }
                    } else {
                        notData.setVisibility(View.VISIBLE);
                        mListView.setVisibility(View.GONE);
                    }
                }

            });
        } catch (Exception e) {
            dismissProgressDialog();
            e.printStackTrace();
        }
    }

    //日历控件调用
    private void initDatePicker() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        now = sdf.format(new Date());
        startCustomDatePicker = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                startSelectDate.setText(time.split(" ")[0]);
            }
        }, "2010-01-01 00:00", now); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        startCustomDatePicker.showSpecificTime(false); // 不显示时和分
        startCustomDatePicker.setIsLoop(false); // 不允许循环滚动

        endCustomDatePicker = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                endSelectDate.setText(time.split(" ")[0]);
            }
        }, "2010-01-01 00:00", now); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        endCustomDatePicker.showSpecificTime(false); // 不显示时和分
        endCustomDatePicker.setIsLoop(false); // 不允许循环滚动
    }

    public void hidden(View mView) {
        if (mView != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(mView.getWindowToken(), 0);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_etsearch:
                startCustomDatePicker.show(now.split(" ")[0]);
                break;

            case R.id.end_etsearch:
                endCustomDatePicker.show(now.split(" ")[0]);
                break;

            case R.id.ivDeleteText:
                if (TextUtils.isEmpty(startSelectDate.getText().toString()) || TextUtils.isEmpty(endSelectDate.getText().toString())) {
                    ToastUtils.show("开始或结束时间不能为空！");
                } else {
                    if (isEndNoStart()) {
                        ToastUtils.show("结束时间不能早于开始时间，请重新选择!");
                    } else {
                        list.clear();
                        getData();
                    }
                }
                break;
            case R.id.login_back:
                finish();
                break;
        }

    }

    //验证日期规则
    private boolean isEndNoStart() {
        String pattern = "yyyy-MM-dd";
        return DateUtil.getStringToDate(startSelectDate.getText().toString().trim(), pattern) > DateUtil.getStringToDate(endSelectDate.getText().toString().trim(), pattern);
    }
}
