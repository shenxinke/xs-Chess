package com.xswq.chess.myapplication.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.adapter.PracticalLessonsAdapter;
import com.xswq.chess.myapplication.base.BaseCompatActivity;
import com.xswq.chess.myapplication.base.BaseFragment;
import com.xswq.chess.myapplication.bean.ThemeListBean;
import com.xswq.chess.myapplication.http.HttpCallBackLisener;
import com.xswq.chess.myapplication.http.httpparameterapi.PrepareLessonsApi;
import com.xswq.chess.myapplication.http.retrofitHttp.RetrofitManager;
import com.xswq.chess.myapplication.http.subscriber.PrepareLessonsSub;
import com.xswq.chess.myapplication.utils.StatisticsUtil;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 实战课
 */
public class PracticalLessonsFragment extends BaseFragment implements HttpCallBackLisener {
    private Bundle bundle;
    private View mView;
    private ListView listView;
    private SmartRefreshLayout smartRefreshLayout;
    private String userId;
    private String token;
    private int pageNum = 1;
    private int userType;
    private PrepareLessonsApi prepareLessonsApi;
    private PrepareLessonsSub mGuideVideoSub;
    List<ThemeListBean.DataBean.ListBean> themeList = new ArrayList<>();
    private PracticalLessonsAdapter practicalLessonsAdapter;

    public static PracticalLessonsFragment getInstances(String userId, String token, int userType) {
        PracticalLessonsFragment practicalLessonsFragment = new PracticalLessonsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("userId", userId);
        bundle.putString("token", token);
        bundle.putInt("userType", userType);
        practicalLessonsFragment.setArguments(bundle);
        return practicalLessonsFragment;
    }

    @Override
    protected int setContentView() {
        bundle = getArguments();
        userId = bundle.getString("userId");
        token = bundle.getString("token");
        userType = bundle.getInt("userType", 0);
        return getLayout();
    }

    @Override
    protected void startLoad() {
        StatisticsUtil.getStatistics(token,userId,12,getActivity());
        getRequstParamer();
        initListener();
    }

    @Override
    protected void initData() {
        if (mView == null) {
            mView = getContentView();
        }
        listView = mView.findViewById(R.id.practical_list);
        smartRefreshLayout = mView.findViewById(R.id.smartRefreshLayout);
    }

    @Override
    protected void stopLoad() {

    }

    private int getLayout() {
        return R.layout.practical_lessons_fragment;
    }

    private void getRequstParamer() {
        if (prepareLessonsApi == null) {
            prepareLessonsApi = new PrepareLessonsApi(this);
            prepareLessonsApi.setToken(token);
            prepareLessonsApi.setUid(userId);
            prepareLessonsApi.setClassType("2");
            prepareLessonsApi.setUserId(userId);
            mGuideVideoSub = new PrepareLessonsSub(prepareLessonsApi);
            RetrofitManager.getRetrofitInstance().handleHttp(mGuideVideoSub, prepareLessonsApi);
        }
    }


    @Override
    public void onNext(Object gsonString, String method) {
        try {
            String jsonString = String.valueOf(gsonString);
            Gson gson = new Gson();
            ThemeListBean themeListBean = gson.fromJson(jsonString, ThemeListBean.class);
            ThemeListBean.ErrorBean error = themeListBean.getError();
            String returnCode = error.getReturnCode();
            if ("0".equals(returnCode)) {
                List<ThemeListBean.DataBean.ListBean> list = themeListBean.getData().getList();
                themeList.addAll(list);
                if (themeList.size() > 0) {
                    getFragmentAssembly(themeList);
                }
            } else {
                ((BaseCompatActivity) getActivity()).quiteApp(getActivity(), error.getReturnMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable e) {

    }

    /**
     * 上拉加载下拉刷新
     */
    private void initListener() {
        smartRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                pageNum++;
                prepareLessonsApi.setPageNum(String.valueOf(pageNum));
                RetrofitManager.getRetrofitInstance().handleHttp(mGuideVideoSub, prepareLessonsApi);
                smartRefreshLayout.finishLoadmore(1000);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageNum = 1;
                if (themeList != null) {
                    themeList.clear();
                }
                prepareLessonsApi.setPageNum(String.valueOf(pageNum));
                RetrofitManager.getRetrofitInstance().handleHttp(mGuideVideoSub, prepareLessonsApi);
                smartRefreshLayout.finishRefresh(500);
            }
        });
    }

    private void getFragmentAssembly(List<ThemeListBean.DataBean.ListBean> list) {
        if (practicalLessonsAdapter == null) {
            practicalLessonsAdapter = new PracticalLessonsAdapter(getActivity(), list, userId, token, userType);
            listView.setAdapter(practicalLessonsAdapter);
        } else {
            practicalLessonsAdapter.upListDate(list);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        smartRefreshLayout.autoRefresh(100);
    }
}
