package com.xswq.chess.myapplication.activity.competition;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.adapter.CompetitionApplyManagementAdapter;
import com.xswq.chess.myapplication.base.BaseCompatActivity;
import com.xswq.chess.myapplication.bean.CompetitionApplyManagementBean;
import com.xswq.chess.myapplication.bean.CompetitionManagementBean;
import com.xswq.chess.myapplication.bean.UpdateEnrollBean;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.GsonUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;

/**
 * 比赛管理
 */
public class CompetitionApplyManagementActivity extends BaseCompatActivity implements View.OnClickListener {
    @BindView(R.id.no_data)
    TextView noData;
    @BindView(R.id.login_back)
    TextView login_back;
    @BindView(R.id.login_titles)
    TextView login_titles;
    @BindView(R.id.listView)
    ListView listView;
    private CompetitionApplyManagementAdapter competitionApplyManagementAdapter;
    private String matchId;
    private List<CompetitionApplyManagementBean.DataBean> data;
    private CompetitionManagementBean.DataBean.ListBean listBean;
    private List<UpdateEnrollBean> stringList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_competition_apply_management;
    }

    @Override
    protected void initView() {
        getWindow().getDecorView().setBackgroundResource(R.mipmap.wei_qi_story_list_bg);
        login_back.setVisibility(View.VISIBLE);
        login_back.setOnClickListener(this);
        login_titles.setText("报名管理");
    }

    @Override
    protected void loadData() {
        Intent intent = getIntent();
        matchId = intent.getStringExtra("matchId");
        listBean = (CompetitionManagementBean.DataBean.ListBean) intent.getSerializableExtra("competitionManagementBean");
        if (listBean != null) {
            List<CompetitionManagementBean.DataBean.ListBean.GroupListBean> groupList = listBean.getGroupList();
            if (groupList != null && groupList.size() > 0) {
                for (int i = 0; i < groupList.size(); i++) {
                    UpdateEnrollBean updateEnrollBean = new UpdateEnrollBean();
                    String id = String.valueOf(groupList.get(i).getId());
                    updateEnrollBean.setId(id);
                    updateEnrollBean.setName(groupList.get(i).getGroupName());
                    stringList.add(updateEnrollBean);
                }
            }
        }
        try {
            OkHttpUtils.post().url(ContactUrl.POST_ENROLL_MANEGER_LIST_PATH)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .addParams("matchId", matchId)
                    .build().execute(new StringCallback() {

                @Override
                public void onError(Call call, Exception e, int id) {
                }

                @Override
                public void onResponse(String response, int id) {
                    CompetitionApplyManagementBean competitionApplyManagementBean = GsonUtil.gsonToBean(response, CompetitionApplyManagementBean.class, CompetitionApplyManagementActivity.this);
                    if (competitionApplyManagementBean != null) {
                        data = competitionApplyManagementBean.getData();
                        if (data != null && data.size() > 0) {
                            getAssembly();
                            listView.setVisibility(View.VISIBLE);
                            noData.setVisibility(View.GONE);
                        } else {
                            noData.setVisibility(View.VISIBLE);
                            listView.setVisibility(View.GONE);
                        }
                    } else {
                        noData.setVisibility(View.VISIBLE);
                        listView.setVisibility(View.GONE);
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
            default:
                break;
        }
    }

    private void getAssembly() {
        if (competitionApplyManagementAdapter == null) {
            competitionApplyManagementAdapter = new CompetitionApplyManagementAdapter(CompetitionApplyManagementActivity.this, data, stringList, token, userId);
            listView.setAdapter(competitionApplyManagementAdapter);
        } else {
            competitionApplyManagementAdapter.upListDate(data);
        }
    }
}
