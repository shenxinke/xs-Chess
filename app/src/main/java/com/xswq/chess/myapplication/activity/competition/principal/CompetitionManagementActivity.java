package com.xswq.chess.myapplication.activity.competition.principal;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.activity.competition.CreateCompetitionActivity;
import com.xswq.chess.myapplication.adapter.CompetitionManagementAdapter;
import com.xswq.chess.myapplication.base.BaseCompatActivity;
import com.xswq.chess.myapplication.bean.CompetitionManagementBean;
import com.xswq.chess.myapplication.config.Const;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.GsonUtil;
import com.xswq.chess.myapplication.utils.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 比赛管理
 */

public class CompetitionManagementActivity extends BaseCompatActivity implements View.OnClickListener {

    private ListView listView;
    private int pageNum = 1;
    private SmartRefreshLayout smartRefreshLayout;
    private List<CompetitionManagementBean.DataBean.ListBean> dataList = new ArrayList<>();
    private TextView noDate;
    private CompetitionManagementAdapter competitionManagementAdapter;
    private ImageView imageNoData;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_competition_management_layout;
    }

    @Override
    protected void initView() {
        TextView loginTitles = findViewById(R.id.login_titles);
        loginTitles.setText("比赛管理");
        TextView loginQueDing = findViewById(R.id.login_queding);
        loginQueDing.setVisibility(View.VISIBLE);
        loginQueDing.setText("创建比赛");
        loginQueDing.setOnClickListener(this);
        TextView loginBack = findViewById(R.id.login_back);
        loginBack.setVisibility(View.VISIBLE);
        loginBack.setOnClickListener(this);
        smartRefreshLayout = findViewById(R.id.smartRefreshLayout);
        listView = findViewById(R.id.list_view);
        noDate = findViewById(R.id.no_data);
        imageNoData = findViewById(R.id.image_no_data);
        getWindow().getDecorView().setBackgroundResource(R.mipmap.wei_qi_story_list_bg);
    }

    @Override
    protected void loadData() {
        initListener();
    }

    private void getData() {
        try {
            OkHttpUtils.post().url(ContactUrl.GET_MATCH_PATH)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .addParams("type", Const.STR1)
                    .addParams("pageNum", String.valueOf(pageNum))
                    .addParams("pageSize", Const.STR20)
                    .build().execute(new StringCallback() {

                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    CompetitionManagementBean competitionManagementBean = GsonUtil.gsonToBean(response, CompetitionManagementBean.class, CompetitionManagementActivity.this);
                    if (competitionManagementBean != null) {
                        CompetitionManagementBean.DataBean data = competitionManagementBean.getData();
                        if (data != null) {
                            dataList.addAll(data.getList());
                            if (dataList != null && dataList.size() > 0) {
                                if (competitionManagementAdapter == null) {
                                    competitionManagementAdapter = new CompetitionManagementAdapter(CompetitionManagementActivity.this, dataList, token, userId,String.valueOf(userType),orgNo);
                                    listView.setAdapter(competitionManagementAdapter);
                                } else {
                                    competitionManagementAdapter.upData(dataList);
                                }
                                listView.setVisibility(View.VISIBLE);
                                noDate.setVisibility(View.GONE);
                                imageNoData.setVisibility(View.GONE);
                            } else {
                                imageNoData.setVisibility(View.VISIBLE);
                                noDate.setVisibility(View.VISIBLE);
                                listView.setVisibility(View.GONE);
                            }
                        } else {
                            imageNoData.setVisibility(View.VISIBLE);
                            noDate.setVisibility(View.VISIBLE);
                            listView.setVisibility(View.GONE);
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 上拉加载下拉刷新
     */
    private void initListener() {
        smartRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                pageNum++;
                getData();
                smartRefreshLayout.finishLoadmore(1000);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageNum = 1;
                if (dataList != null) {
                    dataList.clear();
                }
                getData();
                smartRefreshLayout.finishRefresh(500);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_queding:
                startActivity(new Intent(CompetitionManagementActivity.this, CreateCompetitionActivity.class));
                break;
            case R.id.login_back:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (dataList != null && dataList.size() > 0) {
            dataList.clear();
            pageNum = 1;
        }
        getData();
    }
}
