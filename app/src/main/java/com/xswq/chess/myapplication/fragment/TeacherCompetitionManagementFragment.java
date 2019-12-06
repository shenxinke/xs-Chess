package com.xswq.chess.myapplication.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.adapter.CompetitionManagementAdapter;
import com.xswq.chess.myapplication.base.BaseFragment;
import com.xswq.chess.myapplication.bean.CompetitionManagementBean;
import com.xswq.chess.myapplication.config.Const;
import com.xswq.chess.myapplication.config.RxCode;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.GsonUtil;
import com.xswq.chess.myapplication.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import gorden.rxbus2.RxBus;
import gorden.rxbus2.ThreadMode;
import okhttp3.Call;

public class TeacherCompetitionManagementFragment extends BaseFragment {

    private View mView;
    private ListView listView;
    private TextView noDate;
    private ImageView imageNoData;
    private List<CompetitionManagementBean.DataBean.ListBean> dataList = new ArrayList<>();

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
        RxBus.get().register(this);
        listView = mView.findViewById(R.id.list_view);
        noDate = mView.findViewById(R.id.no_data);
        imageNoData = mView.findViewById(R.id.image_no_data);
        getData();
    }

    @Override
    protected void stopLoad() {

    }

    private int getLayout() {
        return R.layout.teacher_competition_management_layout;
    }

    @gorden.rxbus2.Subscribe(code = RxCode.CODE_COMPETITION_MESSAGE, threadMode = ThreadMode.MAIN)
    public void upData() {
        getData();
    }

    private void getData() {
        try {
            OkHttpUtils.post().url(ContactUrl.GET_MATCH_PATH)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .addParams("type", Const.STR2)
                    .addParams("pageNum", Const.STR1)
                    .addParams("pageSize", Const.STR100)
                    .build().execute(new StringCallback() {

                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    CompetitionManagementBean competitionManagementBean = GsonUtil.gsonToBean(response, CompetitionManagementBean.class, getActivity());
                    if (competitionManagementBean != null) {
                        CompetitionManagementBean.DataBean data = competitionManagementBean.getData();
                        if (data != null) {
                            dataList = data.getList();
                            if (dataList != null && dataList.size() > 0) {
                                listView.setVisibility(View.VISIBLE);
                                noDate.setVisibility(View.GONE);
                                imageNoData.setVisibility(View.GONE);
                                CompetitionManagementAdapter competitionManagementAdapter = new CompetitionManagementAdapter(getActivity(), dataList, token, userId, String.valueOf(userType), orgNo);
                                listView.setAdapter(competitionManagementAdapter);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBus.get().unRegister(this);
    }
}
