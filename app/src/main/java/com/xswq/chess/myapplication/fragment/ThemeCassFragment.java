package com.xswq.chess.myapplication.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.adapter.ThemeCassAdapter;
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
 * 主题课
 */
public class ThemeCassFragment extends BaseFragment implements HttpCallBackLisener {

    private View mView;
    private ListView listView;
    private String userId;
    private SmartRefreshLayout smartRefreshLayout;
    private PrepareLessonsApi prepareLessonsApi;
    private PrepareLessonsSub mGuideVideoSub;
    private String token;
    private int pageNum = 1;
    List<ThemeListBean.DataBean.ListBean> themeList = new ArrayList<>();
    private ThemeCassAdapter themeCassAdapter;
    private int userType;

    public static ThemeCassFragment getInstances( String userId, String token, int userType) {
        ThemeCassFragment themeCassFragment = new ThemeCassFragment();
        Bundle bundle = new Bundle();
        bundle.putString("userId", userId);
        bundle.putString("token", token);
        bundle.putInt("userType", userType);
        themeCassFragment.setArguments(bundle);
        return themeCassFragment;
    }


    @Override
    protected int setContentView() {
        Bundle bundle = getArguments();
        userId = bundle.getString("userId");
        token = bundle.getString("token");
        userType = bundle.getInt("userType");
        return getLayout();
    }

    @Override
    protected void startLoad() {
        StatisticsUtil.getStatistics(token,userId,11,getActivity());
        getRequstParamer();
        initListener();
    }

    @Override
    protected void initData() {
        if (mView == null) {
            mView = getContentView();
        }
        listView = mView.findViewById(R.id.theme_list);
        smartRefreshLayout = mView.findViewById(R.id.smartRefreshLayout);

    }

    @Override
    protected void stopLoad() {

    }

    private int getLayout() {
        return R.layout.theme_cass_fragment;
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

    @Override
    public void onNext(Object gsonString, String method) {
        try {
            String jsonString = String.valueOf(gsonString);
            Gson gson = new Gson();
            ThemeListBean themeListBean = gson.fromJson(jsonString, ThemeListBean.class);
            ThemeListBean.ErrorBean error = themeListBean.getError();
            String returnCode = error.getReturnCode();
            List<ThemeListBean.DataBean.ListBean> list = themeListBean.getData().getList();
            themeList.addAll(list);
            if ("0".equals(returnCode)) {
                if (themeList.size() > 0) {
                    getFragmentAssembly(themeList);
                }
            } else if (returnCode.equals("10048")){
                ((BaseCompatActivity) getActivity()).quiteApp(getActivity(), error.getReturnMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onError(Throwable e) {

    }

    private void getRequstParamer() {
        if (prepareLessonsApi == null) {
            prepareLessonsApi = new PrepareLessonsApi(this);
            prepareLessonsApi.setClassType("1");
            prepareLessonsApi.setToken(token);
            prepareLessonsApi.setUid(userId);
            prepareLessonsApi.setUserId(userId);
            mGuideVideoSub = new PrepareLessonsSub(prepareLessonsApi);
            RetrofitManager.getRetrofitInstance().handleHttp(mGuideVideoSub, prepareLessonsApi);
        }
    }

    private void getFragmentAssembly(List<ThemeListBean.DataBean.ListBean> list) {
        if (themeCassAdapter == null) {
            themeCassAdapter = new ThemeCassAdapter(getActivity(), list, userId, token,userType);
            listView.setAdapter(themeCassAdapter);
        } else {
            themeCassAdapter.upListDate(list);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        smartRefreshLayout.autoRefresh(100);
    }
}
