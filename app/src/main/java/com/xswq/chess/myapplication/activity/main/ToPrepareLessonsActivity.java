package com.xswq.chess.myapplication.activity.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.adapter.ToPreparelessonsAdapter;
import com.xswq.chess.myapplication.base.BaseCompatActivity;
import com.xswq.chess.myapplication.bean.LookPrepareLessonsBean;
import com.xswq.chess.myapplication.bean.PreparelessonBean;
import com.xswq.chess.myapplication.bean.ToPreparelessonBean;
import com.xswq.chess.myapplication.customview.MyListView;
import com.xswq.chess.myapplication.http.HttpCallBackLisener;
import com.xswq.chess.myapplication.http.httpparameterapi.PrepareLessonsByIdApi;
import com.xswq.chess.myapplication.http.retrofitHttp.RetrofitManager;
import com.xswq.chess.myapplication.http.subscriber.PrepareLessonsByIdSub;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.JumpIntent;
import com.xswq.chess.myapplication.utils.Util;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 我要备课
 */
public class ToPrepareLessonsActivity extends BaseCompatActivity implements View.OnClickListener, HttpCallBackLisener {

    private int headLine;
    private int prepareLessinStage;
    private TextView editText1;
    private TextView editText2;
    private TextView editText3;
    private TextView editText4;
    private TextView className;
    private ScrollView scrollView;
    private String itemId;
    private MyListView listView;
    private List<LookPrepareLessonsBean.DataBean> dataList;
    private List<ToPreparelessonBean> toPreparelessonBeans = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.to_preparelessons_activity;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        prepareLessinStage = intent.getIntExtra("prepareLessinStage", 1);
        headLine = intent.getIntExtra("headLine", 0);
        itemId = intent.getStringExtra("id");
        getWindow().getDecorView().setBackgroundResource(R.mipmap.prepare_lessons_backgroud);
        TextView login_back = findViewById(R.id.login_back);
        login_back.setVisibility(View.VISIBLE);
        login_back.setOnClickListener(ToPrepareLessonsActivity.this);
        TextView login_titles = findViewById(R.id.login_titles);
        login_titles.setText("我要备课");
        listView = findViewById(R.id.list_view2);
        className = findViewById(R.id.class_name);
        editText1 = findViewById(R.id.edit_text1);
        editText2 = findViewById(R.id.edit_text2);
        editText3 = findViewById(R.id.edit_text3);
        editText4 = findViewById(R.id.edit_text4);
        scrollView = findViewById(R.id.scrollView);
        scrollView.scrollTo(0, 20);
        listView.setFocusable(false);
    }

    @Override
    protected void loadData() {
        getStudengtGuidance();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login_back) {
            finish();
        }
    }

    public static void openActivity(Context context, int headLine, int prepareLessinStage, String id) {
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        bundle.putInt("headLine", headLine);
        bundle.putInt("prepareLessinStage", prepareLessinStage);
        JumpIntent.jump((Activity) context, ToPrepareLessonsActivity.class, bundle);
    }


    private void getStudengtGuidance() {
        try {
            OkHttpUtils.get().url(ContactUrl.GETPREPARELESSONSBYIDNEW)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .addParams("headLine", String.valueOf(headLine))
                    .addParams("prepareLessinStage", String.valueOf(prepareLessinStage))
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                }

                @Override
                public void onResponse(String response, int id) {
                    if (!TextUtils.isEmpty(response)) {
                        Gson gson = new Gson();
                        PreparelessonBean preparelessonBean = gson.fromJson(response, PreparelessonBean.class);
                        PreparelessonBean.ErrorBean error = preparelessonBean.getError();
                        String returnCode = error.getReturnCode();
                        if ("0".equals(returnCode)) {
                            PreparelessonBean.DataBean data = preparelessonBean.getData();
                            setView(data);
                            if (!TextUtils.isEmpty(itemId)) {
                                getData();
                            }
                        } else if ("10048".equals(returnCode)) {
                            quiteApp(ToPrepareLessonsActivity.this, preparelessonBean.getError().getReturnMessage());
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setView(PreparelessonBean.DataBean data) {
        try {
            PreparelessonBean.DataBean.Data1Bean data1 = data.getData1();
            List<PreparelessonBean.DataBean.Data2Bean> data2 = data.getData2();
            className.setText(Util.signString(data1.getParaKey()));
            editText1.setText(data2.get(0).getListArray().get(0).getText());
            editText2.setText(data2.get(1).getListArray().get(0).getText());
            editText3.setText(data2.get(2).getListArray().get(0).getText());
            editText4.setText(data2.get(3).getListArray().get(0).getText());

            for (int i = 5; i < data2.size(); i++) {
                ToPreparelessonBean toPreparelessonBean = new ToPreparelessonBean();
                toPreparelessonBean.setGrouptitle(data2.get(i).getGrouptitle());
                toPreparelessonBean.setListArray(data2.get(i).getListArray());
                toPreparelessonBeans.add(toPreparelessonBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setListViewHeightBasedOnChildren(ListView listView) {

        // 获取ListView对应的Adapter

        ListAdapter listAdapter = listView.getAdapter();

        if (listAdapter == null) {

            return;

        }

        int totalHeight = 0;

        for (int i = 0; i < listAdapter.getCount(); i++) { // listAdapter.getCount()返回数据项的数目

            View listItem = listAdapter.getView(i, null, listView);

            listItem.measure(0, 0); // 计算子项View 的宽高

            totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度

        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();

        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));

        // listView.getDividerHeight()获取子项间分隔符占用的高度

        // params.height最后得到整个ListView完整显示需要的高度

        listView.setLayoutParams(params);

    }

    @Override
    public void onNext(Object t, String method) {
        try {
            String toString = String.valueOf(t);
            Gson gson = new Gson();
            LookPrepareLessonsBean lookPrepareLessonsBean = gson.fromJson(toString, LookPrepareLessonsBean.class);
            String returnCode = lookPrepareLessonsBean.getError().getReturnCode();
            if ("0".equals(returnCode)) {
                dataList = lookPrepareLessonsBean.getData();
                if (toPreparelessonBeans.size() > 0) {
                    listView.setAdapter(new ToPreparelessonsAdapter(this, toPreparelessonBeans, dataList));
                    setListViewHeightBasedOnChildren(listView);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable e) {

    }

    private void getData() {
        PrepareLessonsByIdApi prepareLessonsApi = new PrepareLessonsByIdApi(this);
        prepareLessonsApi.setLessonsId(itemId);
        prepareLessonsApi.setToken(token);
        prepareLessonsApi.setUid(userId);
        PrepareLessonsByIdSub mGuideVideoSub = new PrepareLessonsByIdSub(prepareLessonsApi);
        RetrofitManager.getRetrofitInstance().handleHttp(mGuideVideoSub, prepareLessonsApi);
    }
}
