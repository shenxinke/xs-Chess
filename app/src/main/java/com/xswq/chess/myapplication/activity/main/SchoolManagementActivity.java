package com.xswq.chess.myapplication.activity.main;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.adapter.SchoolManagementAdapter;
import com.xswq.chess.myapplication.base.BaseCompatActivity;
import com.xswq.chess.myapplication.bean.SchoolManagementBean;
import com.xswq.chess.myapplication.config.Const;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.GsonUtil;
import com.xswq.chess.myapplication.utils.JumpIntent;
import com.xswq.chess.myapplication.utils.StatisticsUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 分校管理
 */
public class SchoolManagementActivity extends BaseCompatActivity implements View.OnClickListener {

    private ListView listView;
    private TextView noData;
    private int pageNum = 1;
    private SchoolManagementAdapter schoolManagementAdapter;
    private SmartRefreshLayout smartRefreshLayout;
    private List<SchoolManagementBean.DataBeanX.DataBean.ListBean> dataList = new ArrayList<>();
    private TextView textOperation;

    @Override
    protected int getLayoutId() {
        return R.layout.school_management_activity_layout;
    }

    @Override
    protected void initView() {
        noData = findViewById(R.id.no_data);
        TextView login_back = findViewById(R.id.login_back);
        login_back.setVisibility(View.VISIBLE);
        login_back.setOnClickListener(this);
        TextView login_titles = findViewById(R.id.login_titles);
        login_titles.setText("分校管理");
        listView = findViewById(R.id.listView);
        textOperation = findViewById(R.id.text_operation);
        smartRefreshLayout = findViewById(R.id.smartRefreshLayout);
        getWindow().getDecorView().setBackgroundResource(R.mipmap.president_bg);
    }

    @Override
    protected void loadData() {
        StatisticsUtil.getStatistics(token, userId, 24, SchoolManagementActivity.this);
        getDate();
        initListener();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_back:
                finish();
                break;
            case R.id.login_queding:
                JumpIntent.jump(SchoolManagementActivity.this, CreateSchoolActivity.class, token, userId);
                break;
            default:
                break;
        }
    }

    //获取分校管理列表
    private void getDate() {
        try {
            OkHttpUtils.post().url(ContactUrl.GET_BRAN_LIST_PATH)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .addParams("pageNum", String.valueOf(pageNum))
                    .addParams("pageSize", Const.STR20)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                }

                @Override
                public void onResponse(String response, int id) {
                    SchoolManagementBean schoolManagementBean = GsonUtil.gsonToBean(response, SchoolManagementBean.class, SchoolManagementActivity.this);
                    if (schoolManagementBean != null) {
                        SchoolManagementBean.DataBeanX data = schoolManagementBean.getData();
                        if (data != null) {
                            SchoolManagementBean.DataBeanX.DataBean data1 = data.getData();
                            if (data1 == null) return;
                            dataList.addAll(data1.getList());
                            int dealerRank = data.getNow().getDealerRank();
                            if (dealerRank == 1 || dealerRank == 2) {
                                textOperation.setVisibility(View.VISIBLE);
                            } else {
                                textOperation.setVisibility(View.GONE);
                            }
                            if (dataList != null && dataList.size() > 0) {
                                getAssembly(dealerRank);
                                listView.setVisibility(View.VISIBLE);
                                noData.setVisibility(View.GONE);
                            } else {
                                noData.setVisibility(View.VISIBLE);
                                listView.setVisibility(View.GONE);
                            }
                        }else {
                            noData.setVisibility(View.VISIBLE);
                            listView.setVisibility(View.GONE);
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getAssembly(int dealerRank) {
        if (schoolManagementAdapter == null) {
            schoolManagementAdapter = new SchoolManagementAdapter(SchoolManagementActivity.this, dataList, dealerRank);
            listView.setAdapter(schoolManagementAdapter);
        } else {
            schoolManagementAdapter.upListDate(dataList);
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
                getDate();
                smartRefreshLayout.finishLoadmore(1000);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageNum = 1;
                if (dataList != null) {
                    dataList.clear();
                }
                getDate();
                smartRefreshLayout.finishRefresh(500);
            }
        });
    }
}
