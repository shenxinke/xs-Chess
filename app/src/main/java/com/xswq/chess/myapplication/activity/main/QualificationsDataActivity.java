package com.xswq.chess.myapplication.activity.main;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.adapter.QualificationsDataAdapter;
import com.xswq.chess.myapplication.base.BaseCompatActivity;
import com.xswq.chess.myapplication.bean.AlwaysParkListBean;
import com.xswq.chess.myapplication.bean.BrachNameListBean;
import com.xswq.chess.myapplication.bean.PresidentListBean;
import com.xswq.chess.myapplication.config.Const;
import com.xswq.chess.myapplication.customview.MaterialSpinner;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.GsonUtil;
import com.xswq.chess.myapplication.utils.StatisticsUtil;
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
 * 师训数据
 */
public class QualificationsDataActivity extends BaseCompatActivity implements View.OnClickListener {
    private MaterialSpinner presidentText;
    private MaterialSpinner spinnerText;
    private TextView noDate;
    private String brachID;
    private ImageView user_search;
    private SmartRefreshLayout smartRefreshLayout;
    private ListView listView;
    private int pageNum = 1;
    private List<PresidentListBean.DataBean.ListBean> list = new ArrayList<>();
    private QualificationsDataAdapter qualificationsDataAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.qualifications_date_activity;
    }

    @Override
    protected void initView() {
        user_search = findViewById(R.id.user_search);
        user_search.setVisibility(View.VISIBLE);
        user_search.setOnClickListener(this);
        presidentText = findViewById(R.id.president_text);
        spinnerText = findViewById(R.id.president_text2);
        noDate = findViewById(R.id.no_date);
        smartRefreshLayout = findViewById(R.id.smartRefreshLayout);
        listView = findViewById(R.id.listView);
        TextView login_back = findViewById(R.id.login_back);
        login_back.setVisibility(View.VISIBLE);
        login_back.setOnClickListener(this);
        TextView login_titles = findViewById(R.id.login_titles);
        login_titles.setText("师训数据");
        getWindow().getDecorView().setBackgroundResource(R.mipmap.president_bg);
    }

    @Override
    protected void loadData() {
        getPrimaryName();
        initListener();
        StatisticsUtil.getStatistics(token, userId, 21, QualificationsDataActivity.this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_back:
                finish();
                break;
            case R.id.user_search:
                if (!TextUtils.isEmpty(brachID)) {
                    if (list != null && list.size() > 0) {
                        list.clear();
                        pageNum = 1;
                        getPresidentList();
                    }
                }
                break;
            default:
                break;
        }
    }

    //查询所有的主院
    private void getPrimaryName() {
        try {
            OkHttpUtils.post().url(ContactUrl.GET_PRIMARY_PATH)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    AlwaysParkListBean alwaysParkListBean = GsonUtil.gsonToBean(response, AlwaysParkListBean.class, QualificationsDataActivity.this);
                    if (alwaysParkListBean != null) {
                        AlwaysParkListBean.DataBean data = alwaysParkListBean.getData();
                        if (data != null) {
                            List<AlwaysParkListBean.DataBean.PrimaryBean> primary = data.getPrimary();
                            if (primary != null && primary.size() > 0) {
                                primaryInfor(primary);
                                getBrachName(primary.get(0).getID());
                            }
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void primaryInfor(final List<AlwaysParkListBean.DataBean.PrimaryBean> schoolManagementList) {
        for (int i = 0; i < schoolManagementList.size(); i++) {
            presidentText.setItem(schoolManagementList.get(i).getCustomerName());
        }

        presidentText.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                String primaryID = schoolManagementList.get(position).getID();
                if (!TextUtils.isEmpty(primaryID)) {
                    list.clear();
                    pageNum = 1;
                    getBrachName(primaryID);
                }
            }
        });
    }

    //查询所有的分院
    private void getBrachName(String orgNo) {
        try {
            OkHttpUtils.post().url(ContactUrl.GET_BRANCH_PATH)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .addParams("orgNo", orgNo)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    BrachNameListBean brachNameListBean = GsonUtil.gsonToBean(response, BrachNameListBean.class, QualificationsDataActivity.this);
                    if (brachNameListBean != null) {
                        List<BrachNameListBean.DataBean> data = brachNameListBean.getData();
                        if (data != null && data.size() > 0) {
                            brachNameInfor(data);
                            brachID = data.get(0).getID();
                            getPresidentList();
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void brachNameInfor(final List<BrachNameListBean.DataBean> schoolManagementList) {
        spinnerText.notifyData();
        for (int i = 0; i < schoolManagementList.size(); i++) {
            spinnerText.setItem(schoolManagementList.get(i).getCustomerName());
        }

        spinnerText.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                brachID = schoolManagementList.get(position).getID();
                if (!TextUtils.isEmpty(brachID)) {
                    list.clear();
                    pageNum = 1;
                    getPresidentList();
                }
            }
        });
    }

    //查询师训列表
    private void getPresidentList() {
        try {
            OkHttpUtils.post().url(ContactUrl.GET_PRESODENT_LIST_PATH)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .addParams("branSchoolId", brachID)
                    .addParams("pageNum", String.valueOf(pageNum))
                    .addParams("pageSize", Const.STR20)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    PresidentListBean presidentListBean = GsonUtil.gsonToBean(response, PresidentListBean.class, QualificationsDataActivity.this);
                    if (presidentListBean != null) {
                        PresidentListBean.DataBean data = presidentListBean.getData();
                        if (data != null) {
                            list.addAll(data.getList());
                            if (list != null && list.size() > 0) {
                                if (qualificationsDataAdapter == null) {
                                    qualificationsDataAdapter = new QualificationsDataAdapter(QualificationsDataActivity.this, QualificationsDataActivity.this.list);
                                    listView.setAdapter(qualificationsDataAdapter);
                                } else {
                                    qualificationsDataAdapter.upData(list);
                                }
                                listView.setVisibility(View.VISIBLE);
                                noDate.setVisibility(View.GONE);
                            } else {
                                noDate.setVisibility(View.VISIBLE);
                                listView.setVisibility(View.GONE);
                            }
                        } else {
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
                getPresidentList();
                smartRefreshLayout.finishLoadmore(1000);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageNum = 1;
                if (list != null) {
                    list.clear();
                }
                getPresidentList();
                smartRefreshLayout.finishRefresh(500);
            }
        });
    }
}
