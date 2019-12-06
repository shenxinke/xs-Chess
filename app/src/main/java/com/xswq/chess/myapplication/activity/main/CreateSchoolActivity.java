package com.xswq.chess.myapplication.activity.main;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
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
import com.xswq.chess.myapplication.utils.SPUtil;
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

public class CreateSchoolActivity extends BaseCompatActivity implements View.OnClickListener {
    private String userId;
    private String token;
    private String id;
    private int pageNum = 1;
    private List<SchoolManagementBean.DataBeanX.DataBean.ListBean> dataBeanList = new ArrayList<>();
    private SchoolManagementAdapter schoolManagementAdapter;
    private ListView listView;
    private SmartRefreshLayout smartRefreshLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_create_school_layout;
    }

    @Override
    protected void initView() {
        userId = SPUtil.getString("uid", "");
        token = SPUtil.getString("token", "");
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        TextView login_titles = findViewById(R.id.login_titles);
        login_titles.setText("分校管理");
        listView = findViewById(R.id.listView);
        TextView login_back = findViewById(R.id.login_back);
        login_back.setVisibility(View.VISIBLE);
        login_back.setOnClickListener(this);
        smartRefreshLayout = findViewById(R.id.smartRefreshLayout);
        if (!TextUtils.isEmpty(id)) {
            getDate();
        }
        getWindow().getDecorView().setBackgroundResource(R.mipmap.president_bg);
    }

    @Override
    protected void loadData() {
        initListener();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_back:
                finish();
                break;
            default:
                break;
        }
    }

    //获取老师列表
    private void getDate() {
        try {
            OkHttpUtils.post().url(ContactUrl.GET_BRAN_LIST_PRIMARY_PATH)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .addParams("orgNo", id)
                    .addParams("pageNum", String.valueOf(pageNum))
                    .addParams("pageSize", Const.STR20)
                    .build().execute(new StringCallback() {

                @Override
                public void onError(Call call, Exception e, int id) {
                }

                @Override
                public void onResponse(String response, int id) {
                    SchoolManagementBean createSchoolTeacherBean = GsonUtil.gsonToBean(response, SchoolManagementBean.class, CreateSchoolActivity.this);
                    if (createSchoolTeacherBean != null) {
                        SchoolManagementBean.DataBeanX data = createSchoolTeacherBean.getData();
                        if (data != null) {
                            SchoolManagementBean.DataBeanX.DataBean data1 = data.getData();
                            if (data1 == null) return;
                            dataBeanList.addAll(data1.getList());

                            if (dataBeanList != null && dataBeanList.size() > 0) {
                                getAssembly();
                            }
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getAssembly() {
        if (schoolManagementAdapter == null) {
            schoolManagementAdapter = new SchoolManagementAdapter(CreateSchoolActivity.this, dataBeanList, 0);
            listView.setAdapter(schoolManagementAdapter);
        } else {
            schoolManagementAdapter.upListDate(dataBeanList);
        }
    }

    /**
     * 上拉加载下拉刷新
     */
    private void initListener() {
        smartRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(@NonNull RefreshLayout refreshLayout) {
                pageNum++;
                getDate();
                smartRefreshLayout.finishLoadmore(1000);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshlayout) {
                pageNum = 1;
                if (dataBeanList != null) {
                    dataBeanList.clear();
                }
                getDate();
                smartRefreshLayout.finishRefresh(500);
            }
        });
    }

}
