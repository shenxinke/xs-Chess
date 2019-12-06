package com.xswq.chess.myapplication.activity.mywork;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.adapter.HistoryTaskAdapter;
import com.xswq.chess.myapplication.androidtojs.JsWebViewActivity;
import com.xswq.chess.myapplication.base.BaseCompatActivity;
import com.xswq.chess.myapplication.bean.CurrentTastBean;
import com.xswq.chess.myapplication.bean.HistoryTask;
import com.xswq.chess.myapplication.config.Const;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.DateUtil;
import com.xswq.chess.myapplication.utils.GsonUtil;
import com.xswq.chess.myapplication.utils.ToastUtils;
import com.xswq.chess.myapplication.utils.Util;
import com.xswq.chess.myapplication.widget.CustomDatePicker;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.Call;

/**
 * 历史作业
 */
public class HistoryTaskActivity extends BaseCompatActivity implements View.OnClickListener {
    private TextView startSelectDate;
    private TextView endSelectDate;
    private ListView mListView;
    private SmartRefreshLayout smartRefreshLayout;
    private CustomDatePicker startCustomDatePicker;
    private CustomDatePicker endCustomDatePicker;
    private int pageNum = 1;
    private String startTime;
    private String endTime;
    private RelativeLayout relativeLayout;
    private List<HistoryTask.DataBean.ListBean> listBeans = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.history_task_layout;
    }

    @Override
    protected void initView() {
        init();
    }

    @Override
    protected void loadData() {
        getRequestParamer();
        initListener();
    }

    private void init() {
        getWindow().getDecorView().setBackgroundResource(R.mipmap.evaluation_bg);
        TextView login_back = findViewById(R.id.login_back);
        login_back.setVisibility(View.VISIBLE);
        login_back.setOnClickListener(this);
        TextView login_titles = findViewById(R.id.login_titles);
        smartRefreshLayout = findViewById(R.id.smartRefreshLayout);
        relativeLayout = findViewById(R.id.url_fragment_rl);
        mListView = findViewById(R.id.task_history_view);
        login_titles.setText("历史作业");
        startSelectDate = findViewById(R.id.task_start_etsearch);
        hidden(startSelectDate);
        startSelectDate.setOnClickListener(this);
        endSelectDate = findViewById(R.id.task_end_etsearch);
        hidden(endSelectDate);
        endSelectDate.setOnClickListener(this);
        ImageView taskDeleteText = findViewById(R.id.task_ivDeleteText);
        taskDeleteText.setOnClickListener(HistoryTaskActivity.this);
        initDatePicker();
    }

    private void getRequestParamer() {
        showProgressDialog(true);
        try {
            PostFormBuilder post = OkHttpUtils.post();
            post.url(ContactUrl.HISTORYTASK_PATH)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .addParams("pageNum", String.valueOf(pageNum))
                    .addParams("pageSize", Const.STR20);
            if (!TextUtils.isEmpty(startTime)) {
                post.addParams("startTime", startTime);
            }
            if (!TextUtils.isEmpty(endTime)) {
                post.addParams("endTime", endTime);
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
                    HistoryTask historyTask = GsonUtil.gsonToBean(response, HistoryTask.class, HistoryTaskActivity.this);
                    if (historyTask != null) {
                        HistoryTask.DataBean data = historyTask.getData();
                        if (data != null) {
                            listBeans.addAll(data.getList());
                            if (listBeans != null && !listBeans.isEmpty()) {
                                int visibility = mListView.getVisibility();
                                if (visibility != 0) {
                                    relativeLayout.setVisibility(View.GONE);
                                    mListView.setVisibility(View.VISIBLE);
                                }
                                HistoryTaskAdapter historyTaskAdapter = new HistoryTaskAdapter(HistoryTaskActivity.this, listBeans);
                                mListView.setAdapter(historyTaskAdapter);
                                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        getHistoryDetail(listBeans.get(i).getJobid());
                                    }
                                });
                            } else {
                                relativeLayout.setVisibility(View.VISIBLE);
                                mListView.setVisibility(View.GONE);
                            }
                        } else {
                            relativeLayout.setVisibility(View.VISIBLE);
                            mListView.setVisibility(View.GONE);
                        }
                    } else {
                        relativeLayout.setVisibility(View.VISIBLE);
                        mListView.setVisibility(View.GONE);
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
                finish();
                break;
            case R.id.task_ivDeleteText:
                if (Util.isClickable()) return;
                startTime = startSelectDate.getText().toString().trim();
                endTime = endSelectDate.getText().toString().trim();
                if (TextUtils.isEmpty(startTime) || TextUtils.isEmpty(endTime)) {
                    ToastUtils.show("查询时间不能为空");
                } else if (isEndNoStart()) {
                    ToastUtils.show("结束时间不能早于开始时间，请重新选择!");
                } else {
                    if (listBeans != null) {
                        listBeans.clear();
                    }
                    getRequestParamer();
                }
                break;
            case R.id.task_start_etsearch:
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");// HH:mm:ss
                // 获取当前时间
                Date date = new Date(System.currentTimeMillis());
                startCustomDatePicker.show(simpleDateFormat.format(date));
                break;

            case R.id.task_end_etsearch:
                endCustomDatePicker.show(startSelectDate.getText().toString());
                break;
        }
    }

    public void hidden(View mView) {
        if (mView != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(mView.getWindowToken(), 0);
        }
    }

    //日历控件调用
    private void initDatePicker() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String now = sdf.format(new Date());

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

    //验证日期规则
    private boolean isEndNoStart() {
        String pattern = "yyyy-MM-dd";
        return DateUtil.getStringToDate(startTime, pattern) > DateUtil.getStringToDate(endTime, pattern);
    }

    private void initListener() {
        smartRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                pageNum++;
                getRequestParamer();
                smartRefreshLayout.finishLoadmore(1000);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageNum = 1;
                if (listBeans != null) {
                    listBeans.clear();
                }
                getRequestParamer();
                smartRefreshLayout.finishRefresh(500);
            }
        });
    }

    private void getHistoryDetail(final String jobId) {
        try {
            OkHttpUtils.post().url(ContactUrl.HISTORY_DETAIL_PATH)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .addParams("pageSize", Const.STR10)
                    .addParams("pageNum", Const.STR1)
                    .addParams("jobId", jobId)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    CurrentTastBean currentTastBean = GsonUtil.gsonToBean(response, CurrentTastBean.class, HistoryTaskActivity.this);
                    if (currentTastBean != null) {
                        CurrentTastBean.DataBean data = currentTastBean.getData();
                        if (data == null) {
                            Intent mIntent = new Intent(HistoryTaskActivity.this, JsWebViewActivity.class);
                            mIntent.putExtra("prefix", ContactUrl.MYWORD);
                            mIntent.putExtra("title", "历史作业");
                            startActivity(mIntent);
                        } else {
                            int total = data.getTotal();
                            List<CurrentTastBean.DataBean.ListBean> list = data.getList();
                            if (list != null && !list.isEmpty()) {
                                String rightWrong = list.get(0).getRightWrong();
                                if (!Const.STR0.equals(rightWrong)) {
                                    Intent mIntent = new Intent(HistoryTaskActivity.this, JsWebViewActivity.class);
                                    mIntent.putExtra("jobId", jobId);
                                    mIntent.putExtra("prefix", ContactUrl.MYWORDHISTORY);
                                    mIntent.putExtra("title", "历史作业");
                                    startActivity(mIntent);
                                } else {
                                    Intent mIntent = new Intent(HistoryTaskActivity.this, JsWebViewActivity.class);
                                    mIntent.putExtra("prefix", ContactUrl.MYWORD);
                                    mIntent.putExtra("title", "历史作业");
                                    mIntent.putExtra("total", total);
                                    startActivity(mIntent);
                                }
                            } else {
                                Intent mIntent = new Intent(HistoryTaskActivity.this, JsWebViewActivity.class);
                                mIntent.putExtra("prefix", ContactUrl.MYWORD);
                                mIntent.putExtra("title", "历史作业");
                                mIntent.putExtra("total", total);
                                startActivity(mIntent);
                            }
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
