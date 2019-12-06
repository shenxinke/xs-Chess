package com.xswq.chess.myapplication.activity.competition.student;


import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.adapter.StudentCompetitionManagementAdapter;
import com.xswq.chess.myapplication.base.BaseCompatActivity;
import com.xswq.chess.myapplication.bean.StudentCompetitionManagementBean;
import com.xswq.chess.myapplication.config.Const;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.GsonUtil;
import com.xswq.chess.myapplication.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class StudentCompetitionManagementActivity extends BaseCompatActivity implements View.OnClickListener {

    private ListView listView;
    private List<StudentCompetitionManagementBean.DataBean> dataList = new ArrayList<>();
    private TextView noDate;
    private ImageView imageNoDate;
    private StudentCompetitionManagementAdapter competitionManagementAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_student_management_layout;
    }

    @Override
    protected void initView() {
        getWindow().getDecorView().setBackgroundResource(R.mipmap.wei_qi_story_list_bg);
        TextView loginTitles = findViewById(R.id.login_titles);
        loginTitles.setText("比赛");
        TextView loginBack = findViewById(R.id.login_back);
        loginBack.setVisibility(View.VISIBLE);
        loginBack.setOnClickListener(this);
        listView = findViewById(R.id.list_view);
        noDate = findViewById(R.id.no_data);
        imageNoDate = findViewById(R.id.image_no_data);
    }

    @Override
    protected void loadData() {
        try {
            OkHttpUtils.post().url(ContactUrl.POST_STU_MATCH_LIST_PATH)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .build().execute(new StringCallback() {

                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    StudentCompetitionManagementBean competitionManagementBean = GsonUtil.gsonToBean(response, StudentCompetitionManagementBean.class, StudentCompetitionManagementActivity.this);
                    if (competitionManagementBean != null) {
                        dataList.addAll(competitionManagementBean.getData());
                        if (dataList != null && dataList.size() > 0) {
                            if (competitionManagementAdapter == null) {
                                competitionManagementAdapter = new StudentCompetitionManagementAdapter(StudentCompetitionManagementActivity.this, dataList, token, userId);
                                listView.setAdapter(competitionManagementAdapter);
                            } else {
                                competitionManagementAdapter.upData(dataList);
                            }
                            listView.setVisibility(View.VISIBLE);
                            noDate.setVisibility(View.GONE);
                            imageNoDate.setVisibility(View.GONE);
                        } else {
                            noDate.setVisibility(View.VISIBLE);
                            listView.setVisibility(View.GONE);
                            imageNoDate.setVisibility(View.VISIBLE);
                        }

                    } else {
                        imageNoDate.setVisibility(View.VISIBLE);
                        noDate.setVisibility(View.VISIBLE);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (competitionManagementAdapter != null) {
            competitionManagementAdapter.cancelAllTimers();
        }
    }
}
