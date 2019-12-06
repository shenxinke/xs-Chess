package com.xswq.chess.myapplication.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.adapter.TeacherJudgeManagementAdapter;
import com.xswq.chess.myapplication.base.BaseFragment;
import com.xswq.chess.myapplication.bean.TeacherJudgeManagementBean;
import com.xswq.chess.myapplication.config.Const;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.GsonUtil;
import com.xswq.chess.myapplication.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

public class TeacherJudgeManagementFragment extends BaseFragment {
    private View mView;
    private ListView listView;
    private TextView noDate;
    private ImageView imageNoData;

    @Override
    protected int setContentView() {
        return getLayout();
    }

    @Override
    protected void startLoad() {

    }

    @Override
    protected void initData() {
        if (mView == null) {
            mView = getContentView();
        }
        listView = mView.findViewById(R.id.list_view);
        noDate = mView.findViewById(R.id.no_data);
        imageNoData = mView.findViewById(R.id.image_no_data);
    }

    @Override
    protected void stopLoad() {

    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    private int getLayout() {
        return R.layout.teacher_judge_management_layout;
    }

    private void getData() {
        try {
            OkHttpUtils.post().url(ContactUrl.POST_GETJUDELIST_PATH)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .build().execute(new StringCallback() {

                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    TeacherJudgeManagementBean teacherJudgeManagementBean = GsonUtil.gsonToBean(response, TeacherJudgeManagementBean.class, getActivity());
                    if (teacherJudgeManagementBean != null) {
                        List<TeacherJudgeManagementBean.DataBean> data = teacherJudgeManagementBean.getData();
                        if (data != null && data.size() > 0) {
                            noDate.setVisibility(View.GONE);
                            imageNoData.setVisibility(View.GONE);
                            listView.setVisibility(View.VISIBLE);
                            TeacherJudgeManagementAdapter teacherJudgeManagementAdapter = new TeacherJudgeManagementAdapter(getActivity(), data);
                            listView.setAdapter(teacherJudgeManagementAdapter);
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
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
